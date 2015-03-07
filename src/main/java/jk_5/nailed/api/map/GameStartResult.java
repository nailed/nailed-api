package jk_5.nailed.api.map;

public class GameStartResult {

    private final boolean success;
    private final String error;
    private final Throwable cause;

    public GameStartResult(boolean success, String error) {
        this(success, error, null);
    }

    public GameStartResult(boolean success, String error, Throwable cause) {
        this.success = success;
        this.error = error;
        this.cause = cause;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }

    public Throwable getCause() {
        return cause;
    }
}
