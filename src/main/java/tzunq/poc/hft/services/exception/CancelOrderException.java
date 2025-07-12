package tzunq.poc.hft.services.exception;

public class CancelOrderException extends RuntimeException {
    public CancelOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
