package jk_5.nailed.api.event;

import javax.annotation.Nonnull;

/**
 * An event that occurs in Nailed
 *
 * @author jk-5
 */
public interface Event {

    /**
     * Gets if the event can be cancelled.
     *
     * @return Whether the event can be cancelled
     */
    boolean isCancellable();

    /**
     * Gets the {@link Result} of the {@link Event}.
     *
     * @return The result of this event
     */
    @Nonnull
    Result getResult();

    /**
     * Sets the {@link Result} of the {@link Event}.
     *
     * @param result The result
     */
    void setResult(@Nonnull Result result);
}
