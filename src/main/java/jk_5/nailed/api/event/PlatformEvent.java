package jk_5.nailed.api.event;

import jk_5.eventbus.Event;
import jk_5.nailed.api.Platform;

/**
 * No description given
 *
 * @author jk-5
 */
public class PlatformEvent extends Event {

    private Platform platform;

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        if(this.platform != null) throw new RuntimeException("Platform is already set");
        this.platform = platform;
    }
}
