package tzunq.poc.hft.adapter.dto;

import java.util.Map;

public class ResponseBuilder {
    private ResponseBuilder() {}

    public static <T> Response<T> success(T data) {
        return new Response<T>()
                .setData(data)
                .setSuccess(true);
    }

    public static <T> Response<T> error(String message) {
        return new Response<T>()
                .setSuccess(false)
                .setMessage(message);
    }

    public static <T> Response<T> validationError(String message, Map<String, String> validation) {
        return new Response<T>()
                .setSuccess(false)
                .setMessage(message)
                .setValidation(validation);
    }

    public static <T> Response<T> unauthorized(String message) {
        return new Response<T>()
                .setSuccess(false)
                .setMessage(message);
    }
}
