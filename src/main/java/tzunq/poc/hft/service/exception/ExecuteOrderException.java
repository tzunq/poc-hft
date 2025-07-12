package tzunq.poc.hft.service.exception;

public class ExecuteOrderException extends RuntimeException {
    public ExecuteOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
