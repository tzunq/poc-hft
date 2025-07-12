package tzunq.poc.hft;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import tzunq.poc.hft.service.exception.OrderStatusNotValidException;
import tzunq.poc.hft.domain.model.Order;
import tzunq.poc.hft.domain.model.OrderSide;
import tzunq.poc.hft.domain.model.OrderStatus;
import tzunq.poc.hft.domain.repository.OrderRepository;
import tzunq.poc.hft.service.usecases.CancelOrderUseCase;
import tzunq.poc.hft.service.usecases.impl.CancelOrderInteractor;
import tzunq.poc.hft.service.usecases.CreateOrderUseCase;
import tzunq.poc.hft.service.dto.CreateOrderDto;
import tzunq.poc.hft.service.usecases.impl.CreateOrderInteractor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.*;

@SpringBootTest
class HftApplicationTests {

    @Test
    void createOrder() {
        OrderRepository orderRepository = mock(OrderRepository.class);

        CreateOrderUseCase createOrderUseCase = new CreateOrderInteractor(orderRepository);
        CreateOrderDto command = new CreateOrderDto("BTC", BigDecimal.valueOf(13422.12), 12, "buy");

        createOrderUseCase.createOrder(command);

        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository, times(1)).save(captor.capture());
        Order savedOrder = captor.getValue();

        assertEquals("BTC", savedOrder.getSymbol());
        assertEquals(BigDecimal.valueOf(13422.12), savedOrder.getPrice());
        assertEquals(12, savedOrder.getQuantity());
        assertEquals(OrderStatus.PENDING, savedOrder.getStatus());
        assertEquals(OrderSide.BUY, savedOrder.getSide());
    }

    @Test
    void cancelOrder() {
        OrderRepository orderRepository = mock(OrderRepository.class);

        CancelOrderUseCase cancelOrderUseCase = new CancelOrderInteractor(orderRepository);
        Order order = new Order()
                .setId(1L)
                .setSymbol("BTC")
                .setPrice(BigDecimal.valueOf(13422.12))
                .setQuantity(12)
                .setSide(OrderSide.BUY)
                .setStatus(OrderStatus.PENDING)
                .setCreatedTime(Instant.now());

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        cancelOrderUseCase.cancelOrder(order.getId());
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
        verify(orderRepository).save(order);
    }

    @Test
    void cancelOrderNotValidStatus() {
        OrderRepository orderRepository = mock(OrderRepository.class);

        CancelOrderUseCase cancelOrderUseCase = new CancelOrderInteractor(orderRepository);
        Order order = new Order()
                .setId(1L)
                .setSymbol("BTC")
                .setPrice(BigDecimal.valueOf(13422.12))
                .setQuantity(12)
                .setSide(OrderSide.BUY)
                .setStatus(OrderStatus.EXECUTED)
                .setCreatedTime(Instant.now());

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        assertThrowsExactly(OrderStatusNotValidException.class, () -> cancelOrderUseCase.cancelOrder(order.getId()));
    }
}
