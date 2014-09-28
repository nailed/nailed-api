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

package jk_5.nailed.api.command.parametric.handler;

import java.lang.reflect.Method;

import jk_5.nailed.api.command.CommandException;
import jk_5.nailed.api.command.context.CommandContext;
import jk_5.nailed.api.command.context.CommandLocals;
import jk_5.nailed.api.command.parametric.ParameterData;
import jk_5.nailed.api.command.parametric.ParameterException;
import jk_5.nailed.api.command.parametric.ParametricBuilder;

/**
 * Called before and after a command is invoked for commands executed by a
 * command created with {@link ParametricBuilder}.
 * 
 * <p>Invocation handlers are created by {@link InvokeListener}s. Multiple
 * listeners and handlers can be registered, and all be run. However, if one handler
 * throws an exception, future handlers will not execute and the command will
 * not execute (if thrown in 
 * {@link #preInvoke(Object, java.lang.reflect.Method, ParameterData[], Object[], CommandContext, CommandLocals)}).</p>
 * 
 * @see InvokeListener the factory
 */
public interface InvokeHandler {

    /**
     * Called before parameters are processed.
     * 
     * @param object the object
     * @param method the method
     * @param parameters the list of parameters
     * @param context the context
     * @param locals the locals object
     * @throws CommandException can be thrown for an error, which will stop invocation
     * @throws ParameterException on parameter error
     */
    boolean preProcess(Object object, Method method, ParameterData[] parameters,
                       CommandContext context, CommandLocals locals) throws CommandException, ParameterException;

    /**
     * Called before the parameter is invoked.
     * 
     * @param object the object
     * @param method the method
     * @param parameters the list of parameters
     * @param args the arguments to be given to the method
     * @param context the context
     * @param locals the locals object
     * @return true to permit invocation of command
     * @throws CommandException can be thrown for an error, which will stop invocation
     * @throws ParameterException on parameter error
     */
    boolean preInvoke(Object object, Method method, ParameterData[] parameters,
                      Object[] args, CommandContext context, CommandLocals locals) throws CommandException, ParameterException;

    /**
     * Called after the parameter is invoked.
     * 
     * @param object the object
     * @param method the method
     * @param parameters the list of parameters
     * @param args the arguments to be given to the method
     * @param context the context
     * @param locals the locals object
     * @throws CommandException can be thrown for an error
     * @throws ParameterException on parameter error
     */
    void postInvoke(Object object, Method method, ParameterData[] parameters,
                    Object[] args, CommandContext context, CommandLocals locals) throws CommandException, ParameterException;

}
