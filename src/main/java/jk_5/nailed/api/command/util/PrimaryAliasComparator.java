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

package jk_5.nailed.api.command.util;

import java.util.Comparator;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

import jk_5.nailed.api.command.CommandMapping;

/**
 * Compares the primary aliases of two {@link CommandMapping} using
 * {@link String#compareTo(String)}.
 */
public final class PrimaryAliasComparator implements Comparator<CommandMapping> {

    /**
     * An instance of this class.
     */
    public static final PrimaryAliasComparator INSTANCE = new PrimaryAliasComparator(null);
    private final @Nullable Pattern removalPattern;

    /**
     * Create a new instance.
     *
     * @param removalPattern a regex to remove unwanted characters from the compared aliases
     */
    public PrimaryAliasComparator(@Nullable Pattern removalPattern) {
        this.removalPattern = removalPattern;
    }

    private String clean(String alias) {
        if (removalPattern != null) {
            return removalPattern.matcher(alias).replaceAll("");
        }
        return alias;
    }

    @Override
    public int compare(CommandMapping o1, CommandMapping o2) {
        return clean(o1.getPrimaryAlias()).compareTo(clean(o2.getPrimaryAlias()));
    }

}
