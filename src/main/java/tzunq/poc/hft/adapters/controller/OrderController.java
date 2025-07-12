package tzunq.poc.hft.adapters.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tzunq.poc.hft.adapters.dto.CreateOrderRequest;
import tzunq.poc.hft.adapters.dto.OrderDto;
import tzunq.poc.hft.adapters.dto.Response;
import tzunq.poc.hft.adapters.dto.ResponseBuilder;
import tzunq.poc.hft.adapters.mapper.OrderMapper;
import tzunq.poc.hft.entities.model.Order;
import tzunq.poc.hft.services.usecases.CancelOrderUseCase;
import tzunq.poc.hft.services.usecases.CreateOrderUseCase;
import tzunq.poc.hft.services.dto.CreateOrderDto;
import tzunq.poc.hft.services.usecases.GetOrderQueryUseCase;
import tzunq.poc.hft.services.usecases.MatchingOrderUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SecurityRequirement(name = "basicAuth")
@RestController
@RequestMapping("/orders")
@Tag(name = "Orders", description = "Endpoints orders")
public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final GetOrderQueryUseCase getOrderQueryUseCase;
    private final MatchingOrderUseCase matchingOrderUseCase;

    public OrderController(
            CreateOrderUseCase createOrderUseCase,
            CancelOrderUseCase cancelOrderUseCase,
            GetOrderQueryUseCase getOrderQueryUseCase,
            MatchingOrderUseCase matchingOrderUseCase
    ) {
        this.createOrderUseCase = createOrderUseCase;
        this.cancelOrderUseCase = cancelOrderUseCase;
        this.getOrderQueryUseCase = getOrderQueryUseCase;
        this.matchingOrderUseCase = matchingOrderUseCase;
    }

    @Operation(
            summary = "Create a new order",
            description = "Creates a new order with the provided details",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order created successfully")
            }
    )
    @PostMapping
    public ResponseEntity<Response<Long>> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        CreateOrderDto cmd = new CreateOrderDto(request.getSymbol(), request.getPrice(), request.getQuantity(), request.getSide());
        Long id = createOrderUseCase.createOrder(cmd);
        return ResponseEntity.ok(ResponseBuilder.success(id));
    }

    @Operation(
            summary = "Get all orders",
            description = "Returns a list of all existing orders",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Orders retrieved successfully")
            }
    )
    @GetMapping
    public ResponseEntity<Response<List<OrderDto>>> getOrders() {
        List<OrderDto> orders = new ArrayList<>();
        for (var o : getOrderQueryUseCase.getAllOrder()) {
            orders.add(OrderMapper.toOrderDto(o));
        }

        return ResponseEntity.ok(
                ResponseBuilder.success(orders)
        );
    }

    @Operation(
            summary = "Find order by id",
            description = "Find order by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order detail")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Response<OrderDto>> getOrder(@PathVariable Long id) {
        Optional<Order> order = getOrderQueryUseCase.getOrderById(id);
        if (!order.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ResponseBuilder.error("Order not found")
            );
        }

        OrderDto orderDto = OrderMapper.toOrderDto(order.get());
        return ResponseEntity.ok(ResponseBuilder.success(orderDto));
    }

    @Operation(
            summary = "Cancel an order",
            description = "Cancels a pending order by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order cancelled successfully")
            }
    )
    @PostMapping("/{id}/cancel")
    public ResponseEntity<Response<String>> cancelOrder(@PathVariable Long id) {
        cancelOrderUseCase.cancelOrder(id);
        return ResponseEntity.ok(ResponseBuilder.success("success"));
    }

    @Operation(
            summary = "Simulate order matching",
            description = "Execute order matching",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Executed success")
            }
    )
    @PostMapping("/simulate-execution")
    public ResponseEntity<Response<String>> simulateExecution() {
        matchingOrderUseCase.simulateExecution();
        return ResponseEntity.ok(ResponseBuilder.success("success"));
    }
}
