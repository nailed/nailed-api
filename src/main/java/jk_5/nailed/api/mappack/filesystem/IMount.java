package jk_5.nailed.api.mappack.filesystem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.annotation.Nonnull;

/**
 * No description given
 *
 * @author jk-5
 */
public interface IMount {

    boolean exists(@Nonnull String path) throws IOException;
    boolean isDirectory(@Nonnull String path) throws IOException;
    void list(@Nonnull String path, @Nonnull List<String> contents) throws IOException;
    long getSize(@Nonnull String path) throws IOException;
    @Nonnull InputStream openForRead(@Nonnull String path) throws IOException;
}
