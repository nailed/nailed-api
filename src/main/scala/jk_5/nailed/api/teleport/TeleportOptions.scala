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

package jk_5.nailed.api.teleport

/**
 * No description given
 *
 * @author jk-5
 */
case class TeleportOptions(private var destination: Location, private var clearInventory: Boolean = false) {

  def destination(destination: Location): TeleportOptions = {
    this.destination = destination
    this
  }

  def clearInventory(clearInventory: Boolean): TeleportOptions = {
    this.clearInventory = clearInventory
    this
  }

  def getDestination = this.destination
  def isClearInventory = this.clearInventory

  def copy = new TeleportOptions(destination, clearInventory)
}
