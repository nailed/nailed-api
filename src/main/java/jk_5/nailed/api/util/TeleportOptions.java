package jk_5.nailed.api.util;

/**
 * No description given
 *
 * @author jk-5
 */
public final class TeleportOptions {

    private Location destination;
    private boolean clearInventory = false;

    public TeleportOptions(Location destination) {
        this(destination, false);
    }

    public TeleportOptions(Location destination, boolean clearInventory) {
        this.destination = destination;
        this.clearInventory = clearInventory;
    }

    public Location getDestination() {
        return destination;
    }

    public TeleportOptions setDestination(Location destination) {
        this.destination = destination;
        return this;
    }

    public boolean isClearInventory() {
        return clearInventory;
    }

    public TeleportOptions setClearInventory(boolean clearInventory) {
        this.clearInventory = clearInventory;
        return this;
    }

    public TeleportOptions copy(){
        return new TeleportOptions(destination, clearInventory);
    }
}
