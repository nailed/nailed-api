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

package jk_5.nailed.api.command.parametric.argument;

import jk_5.nailed.api.command.context.CommandContext;
import jk_5.nailed.api.command.parametric.MissingParameterException;
import jk_5.nailed.api.command.parametric.ParameterException;
import jk_5.nailed.api.command.parametric.ParametricBuilder;

/**
 * Makes an instance of a {@link CommandContext} into a stack of arguments
 * that can be consumed.
 * 
 * @see ParametricBuilder a user of this class
 */
public class ContextArgumentStack implements ArgumentStack {
    
    private final CommandContext context;
    private int index = 0;
    private int markedIndex = 0;
    
    /**
     * Create a new instance using the given context.
     * 
     * @param context the context
     */
    public ContextArgumentStack(CommandContext context) {
        this.context = context;
    }
    
    @Override
    public String next() throws ParameterException {
        try {
            return context.getString(index++);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingParameterException();
        }
    }

    @Override
    public Integer nextInt() throws ParameterException {
        try {
            return Integer.parseInt(next());
        } catch (NumberFormatException e) {
            throw new ParameterException(
                    "Expected a number, got '" + context.getString(index - 1) + "'");
        }
    }

    @Override
    public Double nextDouble() throws ParameterException {
        try {
            return Double.parseDouble(next());
        } catch (NumberFormatException e) {
            throw new ParameterException(
                    "Expected a number, got '" + context.getString(index - 1) + "'");
        }
    }

    @Override
    public Boolean nextBoolean() throws ParameterException {
        try {
            return next().equalsIgnoreCase("true");
        } catch (IndexOutOfBoundsException e) {
            throw new MissingParameterException();
        }
    }

    @Override
    public String remaining() throws ParameterException {
        try {
            String value = context.getJoinedStrings(index);
            index = context.argsLength();
            return value;
        } catch (IndexOutOfBoundsException e) {
            throw new MissingParameterException();
        }
    }

    /**
     * Get the unconsumed arguments left over, without touching the stack.
     * 
     * @return the unconsumed arguments
     */
    public String getUnconsumed() {
        if (index >= context.argsLength()) {
            return null;
        }
        
        return context.getJoinedStrings(index);
    }

    @Override
    public void markConsumed() {
        index = context.argsLength();
    }

    /**
     * Return the current position.
     * 
     * @return the position
     */
    public int position() {
        return index;
    }
    
    /**
     * Mark the current position of the stack.
     * 
     * <p>The marked position initially starts at 0.</p>
     */
    public void mark() {
        markedIndex = index;
    }
    
    /**
     * Reset to the previously {@link #mark()}ed position of the stack, and return
     * the arguments that were consumed between this point and that previous point.
     * 
     * <p>The marked position initially starts at 0.</p>
     * 
     * @return the consumed arguments
     */
    public String reset() {
        String value = context.getString(markedIndex, index);
        index = markedIndex;
        return value;
    }
    
    /**
     * Return whether any arguments were consumed between the marked position
     * and the current position.
     * 
     * <p>The marked position initially starts at 0.</p>
     * 
     * @return true if values were consumed.
     */
    public boolean wasConsumed() {
        return markedIndex != index;
    }

    /**
     * Return the arguments that were consumed between this point and that marked point.
     * 
     * <p>The marked position initially starts at 0.</p>
     * 
     * @return the consumed arguments
     */
    public String getConsumed() {
        return context.getString(markedIndex, index);
    }
    
    /**
     * Get the underlying context.
     * 
     * @return the context
     */
    @Override
    public CommandContext getContext() {
        return context;
    }

}
