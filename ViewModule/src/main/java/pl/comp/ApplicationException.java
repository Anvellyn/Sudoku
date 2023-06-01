package pl.comp;


public class ApplicationException extends Exception {
    public ApplicationException(final String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(final String message) {
        super(message);
    }

    public ApplicationException(final Throwable throwable) {
        super(throwable);
    }
}
