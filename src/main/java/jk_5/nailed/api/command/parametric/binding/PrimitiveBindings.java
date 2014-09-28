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

package jk_5.nailed.api.command.parametric.binding;

import java.lang.annotation.Annotation;
import javax.annotation.Nullable;

import jk_5.nailed.api.command.parametric.ParameterException;
import jk_5.nailed.api.command.parametric.annotation.Range;
import jk_5.nailed.api.command.parametric.annotation.Text;
import jk_5.nailed.api.command.parametric.annotation.Validate;
import jk_5.nailed.api.command.parametric.argument.ArgumentStack;

/**
 * Handles basic Java types such as {@link String}s, {@link Byte}s, etc.
 * 
 * <p>Handles both the object and primitive types.</p>
 */
public final class PrimitiveBindings extends BindingHelper {

    /**
     * Gets a type from a {@link ArgumentStack}.
     * 
     * @param context the context
     * @param text the text annotation
     * @param modifiers a list of modifiers
     * @return the requested type
     * @throws ParameterException on error
     */
    @BindingMatch(classifier = Text.class,
                  type = String.class,
                  behavior = BindingBehavior.CONSUMES,
                  consumedCount = -1,
                  provideModifiers = true)
    public String getText(ArgumentStack context, Text text, Annotation[] modifiers)
            throws ParameterException {
        String v = context.remaining();
        validate(v, modifiers);
        return v;
    }

    /**
     * Gets a type from a {@link ArgumentStack}.
     * 
     * @param context the context
     * @param modifiers a list of modifiers
     * @return the requested type
     * @throws ParameterException on error
     */
    @BindingMatch(type = String.class,
                  behavior = BindingBehavior.CONSUMES,
                  consumedCount = 1,
                  provideModifiers = true)
    public String getString(ArgumentStack context, Annotation[] modifiers) 
            throws ParameterException {
        String v = context.next();
        validate(v, modifiers);
        return v;
    }

    /**
     * Gets a type from a {@link ArgumentStack}.
     * 
     * @param context the context
     * @return the requested type
     * @throws ParameterException on error
     */
    @BindingMatch(type = { Boolean.class, boolean.class },
                  behavior = BindingBehavior.CONSUMES,
                  consumedCount = 1)
    public Boolean getBoolean(ArgumentStack context) throws ParameterException {
        return context.nextBoolean();
    }

    /**
     * Try to parse numeric input as either a number or a mathematical expression.
     *
     * @param input input
     * @return a number
     * @throws ParameterException thrown on parse error
     */
    private @Nullable Double parseNumericInput(@Nullable String input) throws ParameterException {
        if (input == null) {
            return null;
        }

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e1) {
            throw new ParameterException(String.format("Expected '%s' to be a number", input));
        }
    }

    /**
     * Gets a type from a {@link ArgumentStack}.
     * 
     * @param context the context
     * @param modifiers a list of modifiers
     * @return the requested type
     * @throws ParameterException on error
     */
    @BindingMatch(type = { Integer.class, int.class },
                  behavior = BindingBehavior.CONSUMES,
                  consumedCount = 1,
                  provideModifiers = true)
    public Integer getInteger(ArgumentStack context, Annotation[] modifiers) throws ParameterException {
        Double v = parseNumericInput(context.next());
        if (v != null) {
            int intValue = v.intValue();
            validate(intValue, modifiers);
            return intValue;
        } else {
            return null;
        }
    }

    /**
     * Gets a type from a {@link ArgumentStack}.
     * 
     * @param context the context
     * @param modifiers a list of modifiers
     * @return the requested type
     * @throws ParameterException on error
     */
    @BindingMatch(type = { Short.class, short.class },
                  behavior = BindingBehavior.CONSUMES,
                  consumedCount = 1,
                  provideModifiers = true)
    public Short getShort(ArgumentStack context, Annotation[] modifiers) throws ParameterException {
        Integer v = getInteger(context, modifiers);
        if (v != null) {
            return v.shortValue();
        }
        return null;
    }

    /**
     * Gets a type from a {@link ArgumentStack}.
     * 
     * @param context the context
     * @param modifiers a list of modifiers
     * @return the requested type
     * @throws ParameterException on error
     */
    @BindingMatch(type = { Double.class, double.class },
                  behavior = BindingBehavior.CONSUMES,
                  consumedCount = 1,
                  provideModifiers = true)
    public Double getDouble(ArgumentStack context, Annotation[] modifiers) throws ParameterException {
        Double v = parseNumericInput(context.next());
        if (v != null) {
            validate(v, modifiers);
            return v;
        } else {
            return null;
        }
    }

    /**
     * Gets a type from a {@link ArgumentStack}.
     * 
     * @param context the context
     * @param modifiers a list of modifiers
     * @return the requested type
     * @throws ParameterException on error
     */
    @BindingMatch(type = { Float.class, float.class },
                  behavior = BindingBehavior.CONSUMES,
                  consumedCount = 1,
                  provideModifiers = true)
    public Float getFloat(ArgumentStack context, Annotation[] modifiers) throws ParameterException {
        Double v = getDouble(context, modifiers);
        if (v != null) {
            return v.floatValue();
        }
        return null;
    }
    
    /**
     * Validate a number value using relevant modifiers.
     * 
     * @param number the number
     * @param modifiers the list of modifiers to scan
     * @throws ParameterException on a validation error
     */
    private static void validate(double number, Annotation[] modifiers) 
            throws ParameterException {
        for (Annotation modifier : modifiers) {
            if (modifier instanceof Range) {
                Range range = (Range) modifier;
                if (number < range.min()) {
                    throw new ParameterException(
                            String.format(
                                    "A valid value is greater than or equal to %s " +
                                    "(you entered %s)", range.min(), number));
                } else if (number > range.max()) {
                    throw new ParameterException(
                            String.format(
                                    "A valid value is less than or equal to %s " +
                                    "(you entered %s)", range.max(), number));
                }
            }
        }
    }
    
    /**
     * Validate a number value using relevant modifiers.
     * 
     * @param number the number
     * @param modifiers the list of modifiers to scan
     * @throws ParameterException on a validation error
     */
    private static void validate(int number, Annotation[] modifiers) 
            throws ParameterException {
        for (Annotation modifier : modifiers) {
            if (modifier instanceof Range) {
                Range range = (Range) modifier;
                if (number < range.min()) {
                    throw new ParameterException(
                            String.format(
                                    "A valid value is greater than or equal to %s " +
                                    "(you entered %s)", range.min(), number));
                } else if (number > range.max()) {
                    throw new ParameterException(
                            String.format(
                                    "A valid value is less than or equal to %s " +
                                    "(you entered %s)", range.max(), number));
                }
            }
        }
    }

    /**
     * Validate a string value using relevant modifiers.
     * 
     * @param string the string
     * @param modifiers the list of modifiers to scan
     * @throws ParameterException on a validation error
     */
    private static void validate(String string, Annotation[] modifiers) 
            throws ParameterException {
        if (string == null) {
            return;
        }
        
        for (Annotation modifier : modifiers) {
            if (modifier instanceof Validate) {
                Validate validate = (Validate) modifier;
                
                if (!validate.regex().isEmpty()) {
                    if (!string.matches(validate.regex())) {
                        throw new ParameterException(
                                String.format(
                                        "The given text doesn't match the right " +
                                        "format (technically speaking, the 'format' is %s)", 
                                        validate.regex()));
                    }
                }
            }
        }
    }
    
}
