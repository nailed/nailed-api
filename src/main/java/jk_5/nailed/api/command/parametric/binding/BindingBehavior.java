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

import jk_5.nailed.api.command.context.CommandLocals;
import jk_5.nailed.api.command.parametric.annotation.Switch;
import jk_5.nailed.api.command.parametric.argument.ArgumentStack;

/**
 * Determines the type of binding.
 */
public enum BindingBehavior {
    
    /**
     * Always consumes from a {@link ArgumentStack}.
     */
    CONSUMES,
    
    /**
     * Sometimes consumes from a {@link ArgumentStack}.
     * 
     * <p>Bindings that exhibit this behavior must be defined as a {@link Switch}
     * by commands utilizing the given binding.</p>
     */
    INDETERMINATE,
    
    /**
     * Never consumes from a {@link ArgumentStack}.
     * 
     * <p>Bindings that exhibit this behavior generate objects from other sources,
     * such as from a {@link CommandLocals}. These are "magic" bindings that inject
     * variables.</p>
     */
    PROVIDES

}
