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

import jk_5.nailed.api.command.CommandException;
import jk_5.nailed.api.command.InvocationCommandException;

/**
 * This class converts input {@link Throwable}s into an appropriate
 * {@link CommandException}, throwing {@link InvocationCommandException} if
 * the exception cannot be converted into a friendlier exception.
 *
 * <p>The purpose of this class is to allow commands to throw
 * domain-specific exceptions without having to worry with printing
 * helpful error messages to the user as a registered instance of this class
 * will perform that job.</p>
 */
public interface ExceptionConverter {
    
    /**
     * Attempt to convert the given throwable into a friendly
     * {@link CommandException}.
     * 
     * <p>If the exception is not recognized, then
     * {@link InvocationCommandException} should be thrown to wrap the exception.</p>
     * 
     * @param t the throwable
     * @throws CommandException a command exception
     */
    void convert(Throwable t) throws CommandException;

}
