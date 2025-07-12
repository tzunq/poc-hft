package tzunq.poc.hft.adapters.mapper;

import tzunq.poc.hft.adapters.dto.OrderDto;
import tzunq.poc.hft.entities.model.Order;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class OrderMapper {
    public static OrderDto toOrderDto(Order order) {
        return new OrderDto()
                .setId(order.getId())
                .setSymbol(order.getSymbol())
                .setPrice(order.getPrice())
                .setQuantity(order.getQuantity())
                .setSide(order.getSide().toString())
                .setStatus(order.getStatus().toString())
                .setCreatedTime(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
                        .withZone(ZoneId.systemDefault())
                        .format(order.getCreatedTime()));
    }
}
