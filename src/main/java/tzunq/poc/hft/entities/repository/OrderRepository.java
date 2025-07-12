package tzunq.poc.hft.entities.repository;

import tzunq.poc.hft.entities.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    void save(Order order);
    Optional<Order> findById(Long id);
    List<Order> findAll();
    List<Order> findAllPendingOrder();
}
