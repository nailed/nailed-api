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

import jk_5.nailed.api.command.CommandCallable;
import jk_5.nailed.api.command.Require;
import jk_5.nailed.api.command.SettableDescription;
import jk_5.nailed.api.command.parametric.ParameterData;
import jk_5.nailed.api.command.parametric.ParametricBuilder;

/**
 * Listens to events related to {@link ParametricBuilder}.
 */
public interface InvokeListener {
    
    /**
     * Create a new invocation handler.
     * 
     * <p>An example use of an {@link InvokeHandler} would be to verify permissions
     * added by the {@link Require} annotation.</p>
     * 
     * <p>For simple {@link InvokeHandler}, an object can implement both this
     * interface and {@link InvokeHandler}.</p>
     * 
     * @return a new invocation handler
     */
    InvokeHandler createInvokeHandler();

    /**
     * During creation of a {@link CommandCallable} by a {@link ParametricBuilder},
     * this will be called in case the description needs to be updated.
     * 
     * @param object the object
     * @param method the method
     * @param parameters a list of parameters
     * @param description the description to be updated
     */
    void updateDescription(Object object, Method method, ParameterData[] parameters, SettableDescription description);

}
