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

import java.util.List;
import javax.annotation.Nullable;

/**
 * A description of a command, providing information on the command's
 * parameters, a short description, a help text, and usage information.
 * However, it is up for implementations to provide the information &mdash;
 * some implementations may provide very little information.
 *
 * <p>This class does not define a way to execute the command. See
 * {@link CommandCallable}, which has a {@code getDescription()} method,
 * for an interface that does define how a command is executed.</p>
 */
public interface Description {

    /**
     * Get the list of parameters for this command.
     * 
     * @return a list of parameters
     */
    List<Parameter> getParameters();

    /**
     * Get a short one-line description of this command.
     * 
     * @return a description, or null if no description is available
     */
    @Nullable
    String getShortDescription();

    /**
     * Get a longer help text about this command.
     * 
     * @return a help text, or null if no help is available
     */
    @Nullable
    String getHelp();

    /**
     * Get the usage string of this command.
     * 
     * <p>A usage string may look like 
     * {@code [-w &lt;world&gt;] &lt;var1&gt; &lt;var2&gt;}.</p>
     * 
     * @return a usage string
     */
    String getUsage();
    
    /**
     * Get a list of permissions that the player may have to have permission.
     * 
     * <p>Permission data may or may not be available. This is only useful as a
     * potential hint.</p>
     * 
     * @return the list of permissions
     */
    List<String> getPermissions();

}
