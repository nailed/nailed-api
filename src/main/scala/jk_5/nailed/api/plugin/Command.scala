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

package jk_5.nailed.api.plugin

/**
 * A command that can be executed by a {@link CommandSender}
 *
 * @author jk-5
 */
abstract class Command(private val name: String, private val aliases: String*) {
  Checks.notNull(name, "name")

  private var owner: Plugin = _

  private[plugin] def setOwner(plugin: Plugin) = this.owner = plugin
  def getOwner = this.owner
  def getName = this.name
  def getAliases = this.aliases
  def execute(ctx: CommandContext, args: Arguments)
}