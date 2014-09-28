package jk_5.nailed.api.world;

import javax.annotation.Nonnull;

/**
 * No description given
 *
 * @author jk-5
 */
public interface WorldProvider {

    int getId();
    void setId(int id);

    /**
     * What kind of dimension is this world?
     * Possible options:
     * <ul>
     *     <li>NETHER</li>
     *     <li>OVERWORLD</li>
     *     <li>END</li>
     * </ul>
     *
     * Defaults to OVERWORLD
     *
     * @return the world dimension
     */
    @Nonnull
    Dimension getDimension();

    //TODO: this is temporary
    @Nonnull String getType();
    @Nonnull String getOptions();
}
