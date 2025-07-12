package tzunq.poc.hft.service.exception;

public class OrderStatusNotValidException extends RuntimeException {
    public OrderStatusNotValidException(String message) {
        super(message);
    }
}
