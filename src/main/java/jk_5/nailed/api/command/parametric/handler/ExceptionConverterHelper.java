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

package jk_5.nailed.api.command.parametric.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jk_5.nailed.api.command.CommandException;
import jk_5.nailed.api.command.InvocationCommandException;

/**
 * An implementation of an {@link ExceptionConverter} that calls methods
 * defined in subclasses that have been annotated with
 * {@link ExceptionMatch}.
 * 
 * <p>Only public methods will be used. Methods will be called in order of decreasing
 * levels of inheritance (between classes where one inherits the other). For two
 * different inheritance branches, the order between them is undefined.</p>
 */
public abstract class ExceptionConverterHelper implements ExceptionConverter {
    
    private final List<ExceptionHandler> handlers;

    @SuppressWarnings("unchecked")
    public ExceptionConverterHelper() {
        List<ExceptionHandler> handlers = new ArrayList<ExceptionHandler>();
        
        for (Method method : this.getClass().getMethods()) {
            if (method.getAnnotation(ExceptionMatch.class) == null) {
                continue;
            }
            
            Class<?>[] parameters = method.getParameterTypes();
            if (parameters.length == 1) {
                Class<?> cls = parameters[0];
                if (Throwable.class.isAssignableFrom(cls)) {
                    handlers.add(new ExceptionHandler(
                            (Class<? extends Throwable>) cls, method));
                }
            }
        }
        
        Collections.sort(handlers);
        
        this.handlers = handlers;
    }

    @Override
    public void convert(Throwable t) throws CommandException {
        Class<?> throwableClass = t.getClass();
        for (ExceptionHandler handler : handlers) {
            if (handler.cls.isAssignableFrom(throwableClass)) {
                try {
                    handler.method.invoke(this, t);
                } catch (InvocationTargetException e) {
                    if (e.getCause() instanceof CommandException) {
                        throw (CommandException) e.getCause();
                    }
                    throw new InvocationCommandException(e);
                } catch (IllegalArgumentException e) {
                    throw new InvocationCommandException(e);
                } catch (IllegalAccessException e) {
                    throw new InvocationCommandException(e);
                }
            }
        }
    }
    
    private static class ExceptionHandler implements Comparable<ExceptionHandler> {
        final Class<? extends Throwable> cls;
        final Method method;
        
        private ExceptionHandler(Class<? extends Throwable> cls, Method method) {
            this.cls = cls;
            this.method = method;
        }

        @Override
        public int compareTo(ExceptionHandler o) {
            if (cls.equals(o.cls)) {
                return 0;
            } else if (cls.isAssignableFrom(o.cls)) {
                return 1;
            } else {
                return -1;
            }
        }
    }

}
