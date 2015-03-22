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

package jk_5.nailed.api.command;

import jk_5.nailed.api.command.completion.CommandCompleter;
import jk_5.nailed.api.command.context.CommandLocals;
import jk_5.nailed.api.command.util.auth.AuthorizationException;

/**
 * A command that can be executed.
 */
public interface CommandCallable extends CommandCompleter {

    /**
     * Execute the correct command based on the input.
     *
     * <p>The implementing class must perform the necessary permission
     * checks.</p>
     *
     * @param arguments the arguments
     * @param locals the locals
     * @param parentCommands a list of parent commands, with the first most entry being the top-level command
     * @return the called command, or null if there was no command found
     * @throws CommandException thrown on a command error
     * @throws AuthorizationException when the user does not have the required permissions
     */
    boolean call(String arguments, CommandLocals locals, String[] parentCommands) throws CommandException, AuthorizationException;
    
    /**
     * Get an object describing this command.
     * 
     * @return the command description
     */
    Description getDescription();

    /**
     * Test whether this command can be executed with the given context.
     *
     * @param locals the locals
     * @return true if execution is permitted
     */
    boolean testPermission(CommandLocals locals);

}
