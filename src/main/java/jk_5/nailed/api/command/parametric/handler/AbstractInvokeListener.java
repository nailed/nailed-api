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

import jk_5.nailed.api.command.SettableDescription;
import jk_5.nailed.api.command.parametric.ParameterData;

/**
 * An abstract implementation of {@link InvokeListener} with some
 * no-operation methods implemented to assist in creating subclasses.
 */
public abstract class AbstractInvokeListener implements InvokeListener {

    @Override
    public void updateDescription(Object object, Method method, ParameterData[] parameters, SettableDescription description) {
    }

}
