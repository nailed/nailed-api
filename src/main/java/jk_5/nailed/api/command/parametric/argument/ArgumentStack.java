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

package jk_5.nailed.api.command.parametric.argument;

import jk_5.nailed.api.command.context.CommandContext;
import jk_5.nailed.api.command.parametric.ParameterException;

public interface ArgumentStack {

    /**
     * Get the next string, which may come from the stack or a value flag.
     * 
     * @return the value
     * @throws ParameterException on a parameter error
     */
    String next() throws ParameterException;

    /**
     * Get the next integer, which may come from the stack or a value flag.
     * 
     * @return the value
     * @throws ParameterException on a parameter error
     */
    Integer nextInt() throws ParameterException;

    /**
     * Get the next double, which may come from the stack or a value flag.
     * 
     * @return the value
     * @throws ParameterException on a parameter error
     */
    Double nextDouble() throws ParameterException;

    /**
     * Get the next boolean, which may come from the stack or a value flag.
     * 
     * @return the value
     * @throws ParameterException on a parameter error
     */
    Boolean nextBoolean() throws ParameterException;

    /**
     * Get all remaining string values, which will consume the rest of the stack.
     * 
     * @return the value
     * @throws ParameterException on a parameter error
     */
    String remaining() throws ParameterException;

    /**
     * Set as completely consumed.
     */
    void markConsumed();

    /**
     * Get the underlying context.
     * 
     * @return the context
     */
    CommandContext getContext();

}
