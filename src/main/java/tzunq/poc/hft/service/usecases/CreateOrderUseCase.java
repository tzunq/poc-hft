package tzunq.poc.hft.service.usecases;

import tzunq.poc.hft.service.dto.CreateOrderDto;

public interface CreateOrderUseCase {
    Long createOrder(CreateOrderDto command);
}