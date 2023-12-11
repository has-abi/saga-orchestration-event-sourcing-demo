package com.ab.saga.orderservice.application.service;

import com.ab.commonapi.commands.CreateOrderCommand;
import com.ab.commonapi.events.OrderCreatedEvent;
import com.ab.saga.orderservice.application.dto.OrderRequestDto;
import com.ab.saga.orderservice.application.dto.OrderResponseDto;
import com.ab.saga.orderservice.domain.entity.Order;
import com.ab.saga.orderservice.domain.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CommandGateway commandGateway;
    private final QueryUpdateEmitter queryUpdateEmitter;

    @EventHandler
    public OrderResponseDto onOrderCreatedEvent(OrderCreatedEvent orderCreatedEvent) {
        Order order = new Order();
        order.setUserId(orderCreatedEvent.getUserId());
        order.setAmount(orderCreatedEvent.getAmount());
        order.setOrderStatus(orderCreatedEvent.getOrderStatus());
        Order savedOrder = orderRepository.save(order);

        return OrderResponseDto.builder()
                .orderId(savedOrder.getId())
                .userId(savedOrder.getUserId())
                .orderStatus(savedOrder.getOrderStatus())
                .build();
    }

    @Override
    public CompletableFuture<OrderResponseDto> createOrder(OrderRequestDto orderRequestDto) {
        return commandGateway.send(new CreateOrderCommand(UUID.randomUUID().toString(),
                orderRequestDto.getUserId(), orderRequestDto.getAmount()));
    }

//    @Transactional
//    @Override
//    public void cancelOrder(PaymentProcessedEvent eventDto) {
//        if (eventDto.getPaymentStatus().equals(PaymentStatus.PAYMENT_FAILED)) {
//            var orderToCancel = orderRepository.findById(eventDto.getOrderId());
//
//            orderToCancel.ifPresent(order -> {
//                order.setOrderStatus(OrderStatus.ORDER_FAILED);
//                log.info("OrderService#cancelOrder: Order canceled orderId={}, userId={}",
//                        order.getId(), order.getUserId());
//            });
//
//        }
//    }
//
//    @Transactional
//    @Override
//    public void confirmOrder(ShipmentProcessedEvent eventDto) {
//        if (eventDto.getShipmentStatus().equals(ShipmentStatus.SHIPMENT_COMPLETED)) {
//            var orderToConfirm = orderRepository.findById(eventDto.getOrderId());
//
//            orderToConfirm.ifPresent(order -> {
//                order.setOrderStatus(OrderStatus.ORDER_COMPLETED);
//                log.info("OrderService#cancelOrder: Order completed orderId={}, userId={}",
//                        order.getId(), order.getUserId());
//            });
//        }
//    }
}
