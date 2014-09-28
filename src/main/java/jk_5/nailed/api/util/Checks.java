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

package jk_5.nailed.api.util;

import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * No description given
 *
 * @author jk-5
 */
public final class Checks {

    public Checks() {
    }

    public static <V> V notNull(@Nullable V value, @Nonnull String name){
        if(value == null) throw new IllegalArgumentException(name + " is null");
        return value;
    }

    public static <V> V[] noNullElements(V[] values, @Nonnull String name){
        Checks.notNull(name, "name");
        Checks.notNull(values, name);
        for(int i = 0; i < values.length; i++){
            V v = values[i];
            if(v == null){
                throw new IllegalArgumentException(name + " contains a null element (index " + i + ")");
            }
        }
        return values;
    }

    public static void noNullElements(@Nonnull Iterable<?> values, @Nonnull String name){
        Checks.notNull(name, "name");
        Checks.notNull(values, name);
        Iterator<?> it = values.iterator();
        int i = 0;
        while(it.hasNext()){
            if(it.next() == null) throw new IllegalArgumentException(name + " contains a null element (index " + i + ")");
            i++;
        }
    }

    public static void check(boolean valid, @Nonnull String message){
        Checks.notNull(message, "message");
        if(!valid) throw new IllegalArgumentException(message);
    }

    public static void positiveOrZero(int input, @Nonnull String name){
        Checks.notNull(name, "name");
        if(input < 0) throw new IllegalArgumentException(name + " should be >= 0 (was " + input + ")");
    }

    public static void positive(int input, @Nonnull String name){
        Checks.notNull(name, "name");
        if(input <= 0) throw new IllegalArgumentException(name + " should be > 0 (was " + input + ")");
    }

    public static void positiveOrZero(double input, @Nonnull String name){
        Checks.notNull(name, "name");
        if(input < 0) throw new IllegalArgumentException(name + " should be >= 0 (was " + input + ")");
    }

    public static void positive(double input, @Nonnull String name){
        Checks.notNull(name, "name");
        if(input <= 0) throw new IllegalArgumentException(name + " should be > 0 (was " + input + ")");
    }

    public static void smallerThan(double input, double compare, @Nonnull String name){
        Checks.notNull(name, "name");
        if(input >= compare) throw new IllegalArgumentException(name + " should be < " + compare + " (was " + input + ")");
    }

    public static void smallerThan(int input, int compare, @Nonnull String name){
        Checks.notNull(name, "name");
        if(input >= compare) throw new IllegalArgumentException(name + " should be < " + compare + " (was " + input + ")");
    }

    public static void smallerThanOrEqual(double input, double compare, @Nonnull String name){
        Checks.notNull(name, "name");
        if(input > compare) throw new IllegalArgumentException(name + " should be <= " + compare + " (was " + input + ")");
    }

    public static void smallerThanOrEqual(int input, int compare, @Nonnull String name){
        Checks.notNull(name, "name");
        if(input > compare) throw new IllegalArgumentException(name + " should be <= " + compare + " (was " + input + ")");
    }
}
