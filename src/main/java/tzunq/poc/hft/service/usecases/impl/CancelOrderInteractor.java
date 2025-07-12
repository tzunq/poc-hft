package tzunq.poc.hft.service.usecases.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tzunq.poc.hft.service.exception.CancelOrderException;
import tzunq.poc.hft.service.exception.OrderNotFoundException;
import tzunq.poc.hft.service.exception.OrderStatusNotValidException;
import tzunq.poc.hft.service.usecases.CancelOrderUseCase;
import tzunq.poc.hft.domain.model.Order;
import tzunq.poc.hft.domain.model.OrderStatus;
import tzunq.poc.hft.domain.repository.OrderRepository;

@Log4j2
@Service
public class CancelOrderInteractor implements CancelOrderUseCase {
    private final OrderRepository orderRepository;

    public CancelOrderInteractor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void cancelOrder(Long id) {
        try {
            Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id.toString()));
            if (order.getStatus() != OrderStatus.PENDING) {
                throw new OrderStatusNotValidException("Only cancel PENDING orders");
            }

            order.setStatus(OrderStatus.CANCELLED);
            orderRepository.save(order);
            log.info("Order {} {} cancelled with id {}", order.getSide(), order.getSymbol(), order.getId());
        } catch (OrderStatusNotValidException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error canceling order {}", id, e);
            throw new CancelOrderException("Error canceling order with id " + id, e);
        }
    }
}
