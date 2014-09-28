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

package jk_5.nailed.api.command.dispatcher;

import java.util.Collection;
import java.util.Set;
import javax.annotation.Nullable;

import jk_5.nailed.api.command.CommandCallable;
import jk_5.nailed.api.command.CommandMapping;

/**
 * Executes a command based on user input.
 */
public interface Dispatcher extends CommandCallable {

    /**
     * Register a command with this dispatcher.
     * 
     * @param callable the command executor
     * @param alias a list of aliases, where the first alias is the primary name
     */
    void registerCommand(CommandCallable callable, String... alias);
    
    /**
     * Get a list of commands. Each command, regardless of how many aliases
     * it may have, will only appear once in the returned set.
     * 
     * <p>The returned collection cannot be modified.</p>
     * 
     * @return a list of registrations
     */
    Set<CommandMapping> getCommands();

    /**
     * Get a list of primary aliases.
     * 
     * <p>The returned collection cannot be modified.</p>
     * 
     * @return a list of aliases
     */
    Collection<String> getPrimaryAliases();

    /**
     * Get a list of all the command aliases, which includes the primary alias.
     * 
     * <p>A command may have more than one alias assigned to it. The returned 
     * collection cannot be modified.</p>
     * 
     * @return a list of aliases
     */
    Collection<String> getAliases();

    /**
     * Get the {@link CommandCallable} associated with an alias. Returns
     * null if no command is named by the given alias.
     * 
     * @param alias the alias
     * @return the command mapping (null if not found)
     */
    @Nullable CommandMapping get(String alias);

    /**
     * Returns whether the dispatcher contains a registered command for the given alias.
     * 
     * @param alias the alias
     * @return true if a registered command exists
     */
    boolean contains(String alias);

}
