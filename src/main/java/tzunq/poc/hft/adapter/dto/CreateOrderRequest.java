package tzunq.poc.hft.adapter.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tzunq.poc.hft.infrastructure.annotation.EnumValue;
import tzunq.poc.hft.domain.model.OrderSide;

import java.math.BigDecimal;

@Data
public class CreateOrderRequest {
    @NotBlank(message = "symbol is required")
    private String symbol;

    @NotNull(message = "price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "quantity is required")
    @Min(value = 1, message = "quantity must be greater than 0")
    private Integer quantity;

    @EnumValue(enumClass = OrderSide.class, message = "side must be BUY or SELL")
    private String side;
}
