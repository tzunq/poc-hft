package tzunq.poc.hft.service.exception;

public class CreateOrderException extends RuntimeException {
    public CreateOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
