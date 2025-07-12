package tzunq.poc.hft.services.usecases.impl;

import org.springframework.stereotype.Service;
import tzunq.poc.hft.services.usecases.GetOrderQueryUseCase;
import tzunq.poc.hft.entities.model.Order;
import tzunq.poc.hft.entities.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GetOrderQueryInteractor implements GetOrderQueryUseCase {
    private final OrderRepository orderRepository;

    public GetOrderQueryInteractor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
}