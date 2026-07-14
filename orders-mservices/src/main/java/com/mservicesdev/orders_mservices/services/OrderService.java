package com.mservicesdev.orders_mservices.services;

import com.mservicesdev.orders_mservices.events.OrderEvent;
import com.mservicesdev.orders_mservices.model.dtos.*;
import com.mservicesdev.orders_mservices.model.entities.Order;
import com.mservicesdev.orders_mservices.model.entities.OrderItems;
import com.mservicesdev.orders_mservices.model.enums.OrderStatus;
import com.mservicesdev.orders_mservices.repositories.OrderRepository;
import com.mservicesdev.orders_mservices.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    private final KafkaTemplate<String, String> fakfaTemplate;

    // @Value("${inventory.service.url:http://localhost:9090}")
    @Value("lb://inventory-mservices")
    private String inventoryServiceUrl;

    public OrderResponse placeOrder(OrderRequest orderRequest) {

        // Validación de stock en microservicio de inventario:
        BaseResponse result = this.webClientBuilder.build()
                .post()
                .uri(inventoryServiceUrl + "/api/inventory/in-stock")
                .bodyValue(orderRequest.getOrderItems())
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();

        if(result != null && !result.hasError()) { // Si no hay errores continua el proceso de registro de orden
            // Registro de ordenes:
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setOrderItems(
                    orderRequest.getOrderItems().stream()
                            .map(orderItemRequest -> mapOderItemRequestToOrderItem(orderItemRequest, order))
                            .toList()
            );

            var saveOrder = this.orderRepository.save((order));

            // Envío de notificacion kafka:
            this.fakfaTemplate.send(
                    "orders-topic",
                    JsonUtils.toJson(
                            new OrderEvent(saveOrder.getOrderNumber(), saveOrder.getOrderItems().size(), OrderStatus.PLACED)
                    )
            );

            return mapToOrderResponse(saveOrder);
        } else {
            throw new IllegalArgumentException("Los productos no se cuentan con stock");
        }
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = this.orderRepository.findAll();

        return orders.stream().map(this::mapToOrderResponse).toList();
    }

    private OrderResponse mapToOrderResponse(Order order) {
        return new OrderResponse(order.getId(), order.getOrderNumber(),
                order.getOrderItems().stream().map(this::mapToOrderItemRequest).toList());
    }

    private OrderItemResponse mapToOrderItemRequest(OrderItems orderItems) {
        return new OrderItemResponse(orderItems.getId(), orderItems.getSku(), orderItems.getPrice(), orderItems.getQuantity());
    }


    private OrderItems mapOderItemRequestToOrderItem(OrderItemRequest orderItemRequest, Order order) {
        return OrderItems.builder()
                .id(orderItemRequest.getId())
                .sku(orderItemRequest.getSku())
                .price(orderItemRequest.getPrice())
                .quantity(orderItemRequest.getQuantity())
                .order(order)
                .build();
    }


}
