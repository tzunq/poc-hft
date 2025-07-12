package tzunq.poc.hft.services.usecases.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tzunq.poc.hft.services.dto.CreateOrderDto;
import tzunq.poc.hft.services.exception.CreateOrderException;
import tzunq.poc.hft.services.usecases.CreateOrderUseCase;
import tzunq.poc.hft.entities.model.Order;
import tzunq.poc.hft.entities.model.OrderStatus;
import tzunq.poc.hft.entities.repository.OrderRepository;

import java.time.Instant;

@Log4j2
@Service
public class CreateOrderInteractor implements CreateOrderUseCase {
    private final OrderRepository orderRepository;

    public CreateOrderInteractor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Long createOrder(CreateOrderDto command) {
        try {
            Order order = new Order()
                    .setSymbol(command.getSymbol())
                    .setPrice(command.getPrice())
                    .setQuantity(command.getQuantity())
                    .setSide(command.getSide())
                    .setStatus(OrderStatus.PENDING)
                    .setCreatedTime(Instant.now());

            orderRepository.save(order);
            log.info("Order {} {} created with id {}", order.getSide(), order.getSymbol(), order.getId());
            return order.getId();
        } catch (Exception e) {
            log.error("Error creating order {} {}", command.getSide(), command.getSymbol(), e);
            throw new CreateOrderException("Error creating order " + command.getSide() + " " + command.getSymbol(), e);
        }
    }
}