package tzunq.poc.hft.services.usecases;

import tzunq.poc.hft.services.dto.CreateOrderDto;

public interface CreateOrderUseCase {
    Long createOrder(CreateOrderDto command);
}