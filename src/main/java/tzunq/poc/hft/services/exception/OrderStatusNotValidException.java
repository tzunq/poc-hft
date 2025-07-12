package tzunq.poc.hft.services.exception;

public class OrderStatusNotValidException extends RuntimeException {
    public OrderStatusNotValidException(String message) {
        super(message);
    }
}
