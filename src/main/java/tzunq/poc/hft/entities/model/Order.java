package tzunq.poc.hft.entities.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Accessors(chain = true)
public class Order {
    private Long id;
    private String symbol;
    private BigDecimal price;
    private Integer quantity;
    private OrderSide side;
    private OrderStatus status;
    private Instant createdTime;
}
