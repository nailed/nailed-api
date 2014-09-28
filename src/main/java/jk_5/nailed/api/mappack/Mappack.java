package jk_5.nailed.api.mappack;

import java.io.File;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.netty.util.concurrent.Promise;

import jk_5.nailed.api.mappack.filesystem.IMount;
import jk_5.nailed.api.mappack.metadata.MappackMetadata;

/**
 * No description given
 *
 * @author jk-5
 */
public interface Mappack {

    @Nonnull
    String getId();

    @Nonnull
    MappackMetadata getMetadata();

    /**
     * This method should prepare the game world at the given location asynchronously, and finish the promise
     *
     * Important: You HAVE to call .setSuccess(null) or .setFailure(throwable) on the promise, or the loading will hang
     *
     * @param destinationDirectory The location where the game world should be prepared
     * @param promise              The callback to call when the map is set up.
     */
    void prepareWorld(@Nonnull File destinationDirectory, @Nonnull Promise<Void> promise);

    @Nullable
    IMount getMappackMount();
}
