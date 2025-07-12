package tzunq.poc.hft.adapters.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tzunq.poc.hft.adapters.dto.Response;
import tzunq.poc.hft.adapters.dto.ResponseBuilder;
import tzunq.poc.hft.services.exception.OrderNotFoundException;
import tzunq.poc.hft.services.exception.OrderStatusNotValidException;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> validation = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            validation.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(
                ResponseBuilder.validationError("Invalid request", validation)
        );
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Response<Void>> handlerNotFound(OrderNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ResponseBuilder.error(ex.getMessage())
        );
    }

    @ExceptionHandler({OrderStatusNotValidException.class, Exception.class})
    public ResponseEntity<Response<Void>> handleErrors(Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntity.badRequest().body(
                ResponseBuilder.error(ex.getMessage())
        );
    }
}
