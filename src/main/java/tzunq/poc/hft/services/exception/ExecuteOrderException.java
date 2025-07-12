package tzunq.poc.hft.services.exception;

public class ExecuteOrderException extends RuntimeException {
    public ExecuteOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
