package tzunq.poc.hft.adapter.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class OrderDto {
    private Long id;
    private String symbol;
    private BigDecimal price;
    private Integer quantity;
    private String side;
    private String status;
    private String createdTime;
}
