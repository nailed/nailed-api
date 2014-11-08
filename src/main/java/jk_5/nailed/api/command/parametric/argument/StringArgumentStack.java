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
import jk_5.nailed.api.command.util.StringUtils;

/**
 * A virtual scope that does not actually read from the underlying 
 * {@link CommandContext}.
 */
public class StringArgumentStack implements ArgumentStack {
    
    private final boolean nonNullBoolean;
    private final CommandContext context;
    private final String[] arguments;
    private int index = 0;
    
    /**
     * Create a new instance using the given context.
     * 
     * @param context the context
     * @param arguments a list of arguments
     * @param nonNullBoolean true to have {@link #nextBoolean()} return false instead of null
     */
    public StringArgumentStack(
            CommandContext context, String[] arguments, boolean nonNullBoolean) {
        this.context = context;
        this.arguments = arguments;
        this.nonNullBoolean = nonNullBoolean;
    }
    
    /**
     * Create a new instance using the given context.
     * 
     * @param context the context
     * @param arguments an argument string to be parsed
     * @param nonNullBoolean true to have {@link #nextBoolean()} return false instead of null
     */
    public StringArgumentStack(
            CommandContext context, String arguments, boolean nonNullBoolean) {
        this.context = context;
        this.arguments = CommandContext.split(arguments);
        this.nonNullBoolean = nonNullBoolean;
    }

    @Override
    public String next() throws ParameterException {
        try {
            return arguments[index++];
        } catch (ArrayIndexOutOfBoundsException e) {
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
            if (nonNullBoolean) { // Special case
                return false;
            }
            
            throw new MissingParameterException();
        }
    }

    @Override
    public String remaining() throws ParameterException {
        try {
            String value = StringUtils.joinString(arguments, " ", index);
            markConsumed();
            return value;
        } catch (IndexOutOfBoundsException e) {
            throw new MissingParameterException();
        }
    }

    @Override
    public void markConsumed() {
        index = arguments.length;
    }

    @Override
    public CommandContext getContext() {
        return context;
    }

}
