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

package jk_5.nailed.api.command.completion;

import java.util.List;

import jk_5.nailed.api.command.CommandException;
import jk_5.nailed.api.command.context.CommandLocals;

/**
 * Provides a method that can provide tab completion for commands.
 */
public interface CommandCompleter {

    /**
     * Get a list of suggestions based on input.
     *
     * @param arguments the arguments entered up to this point
     * @param locals the locals
     * @return a list of suggestions
     * @throws CommandException thrown if there was a parsing error
     */
    List<String> getSuggestions(String arguments, CommandLocals locals) throws CommandException;

}
