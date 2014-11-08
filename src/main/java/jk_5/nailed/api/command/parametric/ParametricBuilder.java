/*
 * Nailed, a Minecraft PvP server framework
 * Copyright (C) jk-5 <http://github.com/jk-5/>
 * Copyright (C) Nailed team and contributors <http://github.com/nailed/>
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the MIT License.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the MIT License along with
 * this program. If not, see <http://opensource.org/licenses/MIT/>.
 */

package jk_5.nailed.api.command.parametric;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

import jk_5.nailed.api.command.Command;
import jk_5.nailed.api.command.CommandCallable;
import jk_5.nailed.api.command.CommandException;
import jk_5.nailed.api.command.Require;
import jk_5.nailed.api.command.completion.CommandCompleter;
import jk_5.nailed.api.command.completion.NullCompleter;
import jk_5.nailed.api.command.context.CommandContext;
import jk_5.nailed.api.command.dispatcher.Dispatcher;
import jk_5.nailed.api.command.parametric.annotation.Switch;
import jk_5.nailed.api.command.parametric.binding.Binding;
import jk_5.nailed.api.command.parametric.binding.PrimitiveBindings;
import jk_5.nailed.api.command.parametric.binding.StandardBindings;
import jk_5.nailed.api.command.parametric.handler.ExceptionConverter;
import jk_5.nailed.api.command.parametric.handler.InvokeHandler;
import jk_5.nailed.api.command.parametric.handler.InvokeListener;
import jk_5.nailed.api.command.util.auth.Authorizer;
import jk_5.nailed.api.command.util.auth.NullAuthorizer;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Creates {@link CommandCallable}s from methods in an object that have been
 * annotated with {@link Command}. The types of the method's parameters
 * are read to determine the arguments that will be passed when the method
 * is invoked. The types of arguments that are supported (String, int,
 * domain-specific objects, etc.) depends on the {@link Binding}s that
 * are registered on this class.
 * 
 * @see Command defines a command
 * @see Switch defines a flag
 */
public class ParametricBuilder {

    private final Map<Type, Binding> bindings = new HashMap<Type, Binding>();
    private final List<InvokeListener> invokeListeners = new ArrayList<InvokeListener>();
    private final List<ExceptionConverter> exceptionConverters = new ArrayList<ExceptionConverter>();
    private Authorizer authorizer = new NullAuthorizer();
    private CommandCompleter defaultCompleter = new NullCompleter();
    
    /**
     * Create a new builder.
     * 
     * <p>This method will install {@link PrimitiveBindings} and 
     * {@link StandardBindings} and default bindings.</p>
     */
    public ParametricBuilder() {
        addBinding(new PrimitiveBindings());
        addBinding(new StandardBindings());
    }

    /**
     * Add a binding for a given type or classifier (annotation).
     * 
     * <p>Whenever a method parameter is encountered, a binding must be found for it
     * so that it can be called later to consume the stack of arguments provided by
     * the user and return an object that is later passed to 
     * {@link java.lang.reflect.Method#invoke(Object, Object...)}.</p>
     * 
     * <p>Normally, a {@link java.lang.reflect.Type} is used to discern between different bindings, but
     * if this is not specific enough, an annotation can be defined and used. This
     * makes it a "classifier" and it will take precedence over the base type. For
     * example, even if there is a binding that handles {@link String} parameters,
     * a special {@code @MyArg} annotation can be assigned to a {@link String}
     * parameter, which will cause the {@link com.google.common.collect.ImmutableBiMap.Builder} to consult the {@link Binding}
     * associated with {@code @MyArg} rather than with the binding for
     * the {@link String} type.</p>
     * 
     * @param binding the binding
     * @param type a list of types (if specified) to override the binding's types
     */
    public void addBinding(Binding binding, @Nullable Type... type) {
        checkNotNull(binding);

        if (type == null || type.length == 0) {
            type = binding.getTypes();
        }
        
        for (Type t : type) {
            bindings.put(t, binding);
        }
    }
    
    /**
     * Attach an invocation listener.
     * 
     * <p>Invocation handlers are called in order that their listeners are
     * registered with a {@link jk_5.nailed.api.command.parametric.ParametricBuilder}. It is not guaranteed that
     * a listener may be called, in the case of a {@link CommandException} being
     * thrown at any time before the appropriate listener or handler is called.
     * It is possible for a 
     * {@link InvokeHandler#preInvoke(Object, java.lang.reflect.Method, ParameterData[], Object[], CommandContext)} to
     * be called for a invocation handler, but not the associated
     * {@link InvokeHandler#postInvoke(Object, java.lang.reflect.Method, ParameterData[], Object[], CommandContext)}.</p>
     * 
     * <p>An example of an invocation listener is one to handle
     * {@link Require}, by first checking to see if permission is available
     * in a {@link InvokeHandler#preInvoke(Object, java.lang.reflect.Method, ParameterData[], Object[], CommandContext)}
     * call. If permission is not found, then an appropriate {@link CommandException}
     * can be thrown to cease invocation.</p>
     * 
     * @param listener the listener
     * @see InvokeHandler the handler
     */
    public void addInvokeListener(InvokeListener listener) {
        checkNotNull(listener);
        invokeListeners.add(listener);
    }
    
    /**
     * Attach an exception converter to this builder in order to wrap unknown
     * {@link Throwable}s into known {@link CommandException}s.
     * 
     * <p>Exception converters are called in order that they are registered.</p>
     * 
     * @param converter the converter
     * @see ExceptionConverter for an explanation
     */
    public void addExceptionConverter(ExceptionConverter converter) {
        checkNotNull(converter);
        exceptionConverters.add(converter);
    }

    /**
     * Build a list of commands from methods specially annotated with {@link Command}
     * (and other relevant annotations) and register them all with the given
     * {@link Dispatcher}.
     * 
     * @param dispatcher the dispatcher to register commands with
     * @param object the object contain the methods
     * @throws ParametricException thrown if the commands cannot be registered
     */
    public void registerMethodsAsCommands(Dispatcher dispatcher, Object object) throws ParametricException {
        checkNotNull(dispatcher);
        checkNotNull(object);

        for (Method method : object.getClass().getDeclaredMethods()) {
            Command definition = method.getAnnotation(Command.class);
            if (definition != null) {
                CommandCallable callable = build(object, method, definition);
                dispatcher.registerCommand(callable, definition.aliases());
            }
        }
    }

    /**
     * Build a {@link CommandCallable} for the given method.
     * 
     * @param object the object to be invoked on
     * @param method the method to invoke
     * @param definition the command definition annotation
     * @return the command executor
     * @throws ParametricException thrown on an error
     */
    private CommandCallable build(Object object, Method method, Command definition) throws ParametricException {
        checkNotNull(object);
        checkNotNull(method);
        return new ParametricCallable(this, object, method, definition);
    }
    
    /**
     * Get the map of bindings.
     * 
     * @return the map of bindings
     */
    Map<Type, Binding> getBindings() {
        return bindings;
    }

    /**
     * Get a list of invocation listeners.
     * 
     * @return a list of invocation listeners
     */
    List<InvokeListener> getInvokeListeners() {
        return invokeListeners;
    }

    /**
     * Get the list of exception converters.
     * 
     * @return a list of exception converters
     */
    List<ExceptionConverter> getExceptionConverters() {
        return exceptionConverters;
    }

    /**
     * Get the authorizer.
     *
     * @return the authorizer
     */
    public Authorizer getAuthorizer() {
        return authorizer;
    }

    /**
     * Set the authorizer.
     *
     * @param authorizer the authorizer
     */
    public void setAuthorizer(Authorizer authorizer) {
        checkNotNull(authorizer);
        this.authorizer = authorizer;
    }

    /**
     * Get the default command suggestions provider that will be used if
     * no suggestions are available.
     *
     * @return the default command completer
     */
    public CommandCompleter getDefaultCompleter() {
        return defaultCompleter;
    }

    /**
     * Set the default command suggestions provider that will be used if
     * no suggestions are available.
     *
     * @param defaultCompleter the default command completer
     */
    public void setDefaultCompleter(CommandCompleter defaultCompleter) {
        checkNotNull(defaultCompleter);
        this.defaultCompleter = defaultCompleter;
    }

}
