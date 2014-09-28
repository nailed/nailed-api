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

/**
 * A subject has authorization attached to it.
 */
public interface Subject {

    /**
     * Get a list of groups that this subject is a part of.
     *
     * @return an array containing a group name per entry
     */
    String[] getGroups();

    /**
     * Check whether this subject has been granted the given permission
     * and throw an exception on error.
     *
     * @param permission the permission
     * @throws AuthorizationException thrown if not permitted
     */
    void checkPermission(String permission) throws AuthorizationException;

    /**
     * Return whether this subject has the given permission.
     *
     * @param permission the permission
     * @return true if permission is granted
     */
    boolean hasPermission(String permission);

}
