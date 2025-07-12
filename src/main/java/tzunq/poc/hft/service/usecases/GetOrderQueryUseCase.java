package tzunq.poc.hft.service.usecases;

import tzunq.poc.hft.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface GetOrderQueryUseCase {
    List<Order> getAllOrder();
    Optional<Order> getOrderById(Long id);
}