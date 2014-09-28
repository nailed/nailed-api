package jk_5.nailed.api.event;

/**
 * The result of an action such as an event
 *
 * @author jk-5
 */
public enum Result {

    /**
     * The result of a request such as an event has been denied continuation
     */
    DENY,
    /**
     * The result of a request such as an event has not been modified, and will
     * progress based on the default expectation
     */
    DEFAULT,
    /**
     * The result of a request such as an event has been allowed continuation
     */
    ALLOW,
    /**
     * There is no result from a request such as an event, or a result is not applicable
     */
    NO_RESULT

}
