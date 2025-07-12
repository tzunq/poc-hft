package tzunq.poc.hft.services.usecases.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tzunq.poc.hft.services.exception.ExecuteOrderException;
import tzunq.poc.hft.services.exception.OrderStatusNotValidException;
import tzunq.poc.hft.services.usecases.MatchingOrderUseCase;
import tzunq.poc.hft.entities.model.Order;
import tzunq.poc.hft.entities.model.OrderStatus;
import tzunq.poc.hft.entities.repository.OrderRepository;

import java.util.List;
import java.util.Random;

@Log4j2
@Service
public class MatchingOrderInteractor implements MatchingOrderUseCase {
    private final OrderRepository orderRepository;
    private final Random random;

    public MatchingOrderInteractor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.random = new Random();
    }

    public void simulateExecution() {
        List<Order> orders = orderRepository.findAllPendingOrder();
        for (Order order : orders) {
            OrderStatus status = random.nextBoolean() ? OrderStatus.EXECUTED : null;
            if (status != null) {
                executeOrder(order, status);
            }
        }
    }

    private void executeOrder(Order order, OrderStatus status) {
        try {
            if (order.getStatus() != OrderStatus.PENDING) {
                throw new OrderStatusNotValidException("Only PENDING orders can be update status");
            }

            order.setStatus(status);
            orderRepository.save(order);
            log.info("Order {} {} with id {} update status to {}", order.getSide(), order.getSymbol(), order.getId(), order.getStatus());
        } catch (OrderStatusNotValidException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error execute order {} {} with id {}", order.getSide(), order.getSymbol(), order.getId(), e);
            throw new ExecuteOrderException("Error execute order with id " + order.getId(), e);
        }
    }
}