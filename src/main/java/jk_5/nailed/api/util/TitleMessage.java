package jk_5.nailed.api.util;

import jk_5.nailed.api.chat.BaseComponent;

/**
 * No description given
 *
 * @author jk-5
 */
public final class TitleMessage {

    private final BaseComponent[] title;
    private final BaseComponent[] subtitle;
    private final int fadeInTime;
    private final int displayTime;
    private final int fadeOutTime;

    private TitleMessage(BaseComponent[] title, BaseComponent[] subtitle, int fadeInTime, int displayTime, int fadeOutTime) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeInTime = fadeInTime;
        this.displayTime = displayTime;
        this.fadeOutTime = fadeOutTime;
    }

    public BaseComponent[] getTitle() {
        return title;
    }

    public BaseComponent[] getSubtitle() {
        return subtitle;
    }

    public int getFadeInTime() {
        return fadeInTime;
    }

    public int getDisplayTime() {
        return displayTime;
    }

    public int getFadeOutTime() {
        return fadeOutTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Builder() {
        }

        private BaseComponent[] title;
        private BaseComponent[] subtitle;
        private int fadeInTime = 20;
        private int displayTime = 60;
        private int fadeOutTime = 20;

        public BaseComponent[] getTitle() {
            return title;
        }

        public BaseComponent[] getSubtitle() {
            return subtitle;
        }

        public int getFadeInTime() {
            return fadeInTime;
        }

        public int getDisplayTime() {
            return displayTime;
        }

        public int getFadeOutTime() {
            return fadeOutTime;
        }

        public Builder setTitle(BaseComponent... title) {
            this.title = title;
            return this;
        }

        public Builder setSubtitle(BaseComponent... subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public Builder setFadeInTime(int fadeInTime) {
            this.fadeInTime = fadeInTime;
            return this;
        }

        public Builder setDisplayTime(int displayTime) {
            this.displayTime = displayTime;
            return this;
        }

        public Builder setFadeOutTime(int fadeOutTime) {
            this.fadeOutTime = fadeOutTime;
            return this;
        }

        public TitleMessage build(){
            return new TitleMessage(title, subtitle, fadeInTime, displayTime, fadeOutTime);
        }
    }
}
