package tzunq.poc.hft.services.usecases;

import tzunq.poc.hft.entities.model.Order;

import java.util.List;
import java.util.Optional;

public interface GetOrderQueryUseCase {
    List<Order> getAllOrder();
    Optional<Order> getOrderById(Long id);
}