package tzunq.poc.hft.domain.repository;

import tzunq.poc.hft.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    void save(Order order);
    Optional<Order> findById(Long id);
    List<Order> findAll();
    List<Order> findAllPendingOrder();
}
