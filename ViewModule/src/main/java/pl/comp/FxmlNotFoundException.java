package pl.comp;

import java.io.IOException;

public class FxmlNotFoundException extends IOException {
    public FxmlNotFoundException(final String message, Throwable cause) {
        super(message, cause);
    }
}
