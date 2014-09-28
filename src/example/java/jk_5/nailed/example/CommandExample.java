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

package jk_5.nailed.example;

import jk_5.nailed.api.command.CommandException;
import jk_5.nailed.api.command.context.CommandLocals;
import jk_5.nailed.api.command.dispatcher.Dispatcher;
import jk_5.nailed.api.command.fluent.CommandGraph;
import jk_5.nailed.api.command.parametric.ParametricBuilder;
import jk_5.nailed.api.command.util.auth.AuthorizationException;
import jk_5.nailed.api.command.util.auth.Authorizer;

/**
 * No description given
 *
 * @author jk-5
 */
public class CommandExample {

    public static void main(String[] args) throws CommandException, AuthorizationException {
        ParametricBuilder builder = new ParametricBuilder();
        builder.setAuthorizer(new Authorizer() {
            @Override
            public boolean testPermission(CommandLocals locals, String permission) {
                System.out.println("Permission check for " + permission);
                return true;
            }
        });
        builder.addBinding(new UserBinding());
        builder.addBinding(new SenderBinding());

        Dispatcher dispatcher = new CommandGraph()
                .builder(builder)
                    .commands()
                        .registerMethods(new TestCommands())
                        .graph()
                .getDispatcher();

        CommandLocals locals = new CommandLocals();
        locals.put(Sender.class, new SimpleSender("SENDER"));
        dispatcher.call("msg jk-5 hi", locals, new String[0]);
    }
}
