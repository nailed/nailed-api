package jk_5.nailed.api.entity;

import java.util.Collection;
import javax.annotation.Nonnull;

import jk_5.nailed.api.potion.Potion;
import jk_5.nailed.api.potion.PotionEffect;

/**
 * No description given
 *
 * @author jk-5
 */
public interface PotionAffectable {

    void clearAllEffects();

    void addPotionEffect(@Nonnull PotionEffect effect);

    void removePotionEffect(@Nonnull Potion potion);

    @Nonnull Collection<PotionEffect> getActiveEffects();

}
