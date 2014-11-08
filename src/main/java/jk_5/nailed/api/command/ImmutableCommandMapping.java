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

import java.util.Arrays;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An immutable command mapping instance.
 */
public class ImmutableCommandMapping implements CommandMapping {
    
    private final String[] aliases;
    private final CommandCallable callable;
    
    /**
     * Create a new instance.
     * 
     * @param callable the command callable
     * @param alias a list of all aliases, where the first one is the primary one
     */
    public ImmutableCommandMapping(CommandCallable callable, String... alias) {
        checkNotNull(callable);
        checkNotNull(alias);
        this.aliases = Arrays.copyOf(alias, alias.length);
        this.callable = callable;
    }

    @Override
    public String getPrimaryAlias() {
        return aliases[0];
    }
    
    @Override
    public String[] getAllAliases() {
        return aliases;
    }
    
    @Override
    public CommandCallable getCallable() {
        return callable;
    }

    @Override
    public Description getDescription() {
        return getCallable().getDescription();
    }

    @Override
    public String toString() {
        return "CommandMapping{" +
                "aliases=" + Arrays.toString(aliases) +
                ", callable=" + callable +
                '}';
    }

}
