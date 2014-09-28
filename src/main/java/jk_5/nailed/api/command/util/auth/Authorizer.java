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

package jk_5.nailed.api.command.util.auth;

import jk_5.nailed.api.command.context.CommandLocals;

/**
 * Tests whether permission is granted.
 */
public interface Authorizer {

    /**
     * Tests whether permission is granted for the given context.
     *
     * @param locals locals
     * @param permission the permission string
     * @return true if permitted
     */
    boolean testPermission(CommandLocals locals, String permission);

}
