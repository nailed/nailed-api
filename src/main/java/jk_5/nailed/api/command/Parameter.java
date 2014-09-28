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

/**
 * Describes a parameter utilized by a command.
 * 
 * @see Description
 */
public interface Parameter {

    /**
     * The name of the parameter.
     * 
     * @return the name
     */
    String getName();

    /**
     * Get the flag associated with this parameter.
     * 
     * @return the flag, or null if there is no flag associated
     * @see #isValueFlag()
     */
    Character getFlag();
    
    /**
     * Return whether the flag is a value flag.
     * 
     * @return true if the flag is a value flag
     * @see #getFlag()
     */
    boolean isValueFlag();
    
    /**
     * Get whether this parameter is optional.
     * 
     * @return true if the parameter does not have to be specified
     */
    boolean isOptional();
    
    /**
     * Get the default value as a string to be parsed by the binding.
     * 
     * @return a default value, or null if none is set
     */
    public String[] getDefaultValue();

}
