package tzunq.poc.hft.entities.repository.persistence;

import org.springframework.stereotype.Repository;
import tzunq.poc.hft.entities.model.Order;
import tzunq.poc.hft.entities.model.OrderStatus;
import tzunq.poc.hft.entities.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository("InMemoryOrderRepository")
public class InMemoryOrderRepository implements OrderRepository {
    private Long sequence = 1L;
    private final Map<Long, Order> orders;

    public InMemoryOrderRepository() {
        this.orders = new ConcurrentHashMap<>();
    }

    @Override
    public void save(Order order) {
        if (order.getId() == null) {
            order.setId(sequence++);
        }

        orders.put(order.getId(), order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public List<Order> findAllPendingOrder() {
        List<Order> pendingOrders = new ArrayList<>();
        for (Order order : orders.values()) {
            if (order.getStatus() == OrderStatus.PENDING) {
                pendingOrders.add(order);
            }
        }

        return pendingOrders;
    }
}
