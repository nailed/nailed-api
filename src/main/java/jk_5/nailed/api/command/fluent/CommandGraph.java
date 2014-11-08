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

package jk_5.nailed.api.command.fluent;

import jk_5.nailed.api.command.dispatcher.Dispatcher;
import jk_5.nailed.api.command.dispatcher.SimpleDispatcher;
import jk_5.nailed.api.command.parametric.ParametricBuilder;

/**
 * A fluent interface to creating a command graph.
 * 
 * <p>A command graph may have multiple commands, and multiple sub-commands below that,
 * and possibly below that.</p>
 */
public class CommandGraph {

    private final DispatcherNode rootDispatcher;
    private ParametricBuilder builder;

    /**
     * Create a new command graph.
     */
    public CommandGraph() {
        SimpleDispatcher dispatcher = new SimpleDispatcher();
        rootDispatcher = new DispatcherNode(this, null, dispatcher);
    }
    
    /**
     * Get the root dispatcher node.
     * 
     * @return the root dispatcher node
     */
    public DispatcherNode commands() {
        return rootDispatcher;
    }

    /**
     * Get the {@link ParametricBuilder}.
     * 
     * @return the builder, or null.
     */
    public ParametricBuilder getBuilder() {
        return builder;
    }

    /**
     * Set the {@link ParametricBuilder} used for calls to 
     * {@link DispatcherNode#registerMethods(Object)}.
     * 
     * @param builder the builder, or null
     * @return this object
     */
    public CommandGraph builder(ParametricBuilder builder) {
        this.builder = builder;
        return this;
    }

    /**
     * Get the root dispatcher.
     * 
     * @return the root dispatcher
     */
    public Dispatcher getDispatcher() {
        return rootDispatcher.getDispatcher();
    }

}
