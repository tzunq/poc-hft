package tzunq.poc.hft.service.usecases.impl;

import org.springframework.stereotype.Service;
import tzunq.poc.hft.service.usecases.GetOrderQueryUseCase;
import tzunq.poc.hft.domain.model.Order;
import tzunq.poc.hft.domain.repository.OrderRepository;

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