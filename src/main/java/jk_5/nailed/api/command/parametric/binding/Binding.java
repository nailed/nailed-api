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

package jk_5.nailed.api.command.parametric.binding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.List;

import jk_5.nailed.api.command.CommandException;
import jk_5.nailed.api.command.parametric.ParameterData;
import jk_5.nailed.api.command.parametric.ParameterException;
import jk_5.nailed.api.command.parametric.ParametricBuilder;
import jk_5.nailed.api.command.parametric.argument.ArgumentStack;

/**
 * A binding injects instances of the parameters of methods (that are
 * commands) that were converted into commands using an instance of
 * {@link ParametricBuilder}.
 * 
 * <p>A binding can be used to handle several types at once. For a binding to be
 * called, it must be registered with a {@link ParametricBuilder} with
 * {@link ParametricBuilder#addBinding(jk_5.nailed.api.command.parametric.binding.Binding, java.lang.reflect.Type...)}.</p>
 * 
 * @see PrimitiveBindings an example of primitive bindings
 * @see StandardBindings standard bindings
 */
public interface Binding {
    
    /**
     * Get the types that this binding handles.
     * 
     * @return the types
     */
    Type[] getTypes();

    /**
     * Get how this binding consumes from a {@link ArgumentStack}.
     * 
     * @param parameter information about the parameter
     * @return the behavior
     */
    BindingBehavior getBehavior(ParameterData parameter);
    
    /**
     * Get the number of arguments that this binding will consume, if this
     * information is available.
     * 
     * <p>This method must return -1 for binding behavior types that are not
     * {@link BindingBehavior#CONSUMES}.</p>
     * 
     * @param parameter information about the parameter
     * @return the number of consumed arguments, or -1 if unknown or irrelevant
     */
    int getConsumedCount(ParameterData parameter);
    
    /**
     * Attempt to consume values (if required) from the given {@link ArgumentStack}
     * in order to instantiate an object for the given parameter.
     * 
     * @param parameter information about the parameter
     * @param scoped the arguments the user has input
     * @param onlyConsume true to only consume arguments
     * @return an object parsed for the given parameter
     * @throws ParameterException thrown if the parameter could not be formulated
     * @throws CommandException on a command exception
     */
    Object bind(ParameterData parameter, ArgumentStack scoped, boolean onlyConsume) throws ParameterException, CommandException, InvocationTargetException;

    /**
     * Get a list of suggestions for the given parameter and user arguments.
     * 
     * @param parameter information about the parameter
     * @param prefix what the user has typed so far (may be an empty string)
     * @return a list of suggestions
     */
    List<String> getSuggestions(ParameterData parameter, String prefix);

}
