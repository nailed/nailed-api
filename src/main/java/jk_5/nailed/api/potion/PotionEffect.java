package jk_5.nailed.api.potion;

import jk_5.nailed.api.util.Checks;

/**
 * No description given
 *
 * @author jk-5
 */
public class PotionEffect {

    private final Potion potion;
    private int duration;
    private int level;
    private boolean ambient;
    private boolean showParticles;

    private PotionEffect(Potion potion, int duration, int level, boolean ambient, boolean showParticles) {
        Checks.notNull(potion, "potion");

        this.potion = potion;
        this.duration = duration;
        this.level = level;
        this.ambient = ambient;
        this.showParticles = showParticles;
    }

    public static Builder builder(){
        return new Builder();
    }

    public Potion getPotion() {
        return potion;
    }

    public int getDuration() {
        return duration;
    }

    public int getLevel() {
        return level;
    }

    public boolean isAmbient() {
        return ambient;
    }

    public boolean isShowParticles() {
        return showParticles;
    }

    public static final class Builder {

        private Potion potion;
        private int duration = 0;
        private int level = 1;
        private boolean ambient = false;
        private boolean showParticles = false;

        private Builder() {
        }

        public Potion getPotion() {
            return potion;
        }

        public Builder setPotion(Potion potion) {
            Checks.notNull(potion, "potion");
            this.potion = potion;
            return this;
        }

        public int getDuration() {
            return duration;
        }

        public Builder setDuration(int duration) {
            Checks.positiveOrZero(duration, "duration");
            Checks.check(duration <= 1000000, "duration should be <= 1000000 (was " + duration + ")");
            this.duration = duration;
            return this;
        }

        public int getLevel() {
            return level;
        }

        public Builder setLevel(int level) {
            Checks.positive(level, "level");
            this.level = level;
            return this;
        }

        public boolean isAmbient() {
            return ambient;
        }

        public Builder setAmbient(boolean ambient) {
            this.ambient = ambient;
            return this;
        }

        public boolean isShowParticles() {
            return showParticles;
        }

        public Builder setShowParticles(boolean showParticles) {
            this.showParticles = showParticles;
            return this;
        }

        public Builder setInfinite(){
            this.duration = 1000000;
            return this;
        }

        public PotionEffect build(){
            return new PotionEffect(potion, duration, level, ambient, showParticles);
        }
    }
}
