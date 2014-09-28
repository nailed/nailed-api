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

import jk_5.nailed.api.command.Command;
import jk_5.nailed.api.command.Require;
import jk_5.nailed.api.command.parametric.annotation.Text;

public class TestCommands {

    @Command(aliases = {"msg", "tell"}, desc = "Send a message to a player")
    @Require("nailed.command.tell")
    public void sendMessage(Sender sender, User target, @Text String message){
        System.out.println(sender.getName() + " sent " + message + " to " + target.getName());
    }
}
