package tzunq.poc.hft.services.exception;

public class CreateOrderException extends RuntimeException {
    public CreateOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
