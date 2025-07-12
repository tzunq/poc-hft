package tzunq.poc.hft.service.dto;

import lombok.Data;
import tzunq.poc.hft.domain.model.OrderSide;

import java.math.BigDecimal;

@Data
public class CreateOrderDto {
    private String symbol;
    private BigDecimal price;
    private Integer quantity;
    private OrderSide side;

    public CreateOrderDto(String symbol, BigDecimal price, Integer quantity, String side) {
        this.symbol = symbol;
        this.price = price;
        this.quantity = quantity;
        this.side = OrderSide.valueOf(side.toUpperCase());
    }
}