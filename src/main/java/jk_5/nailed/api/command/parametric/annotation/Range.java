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

package jk_5.nailed.api.command.parametric.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jk_5.nailed.api.command.parametric.binding.PrimitiveBindings;

/**
 * Specifies a range of values for numbers.
 * 
 * @see PrimitiveBindings a user of this annotation as a modifier
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Range {
    
    /**
     * The minimum value that the number can be at, inclusive.
     * 
     * @return the minimum value
     */
    double min() default Double.MIN_VALUE;

    /**
     * The maximum value that the number can be at, inclusive.
     * 
     * @return the maximum value
     */
    double max() default Double.MAX_VALUE;

}
