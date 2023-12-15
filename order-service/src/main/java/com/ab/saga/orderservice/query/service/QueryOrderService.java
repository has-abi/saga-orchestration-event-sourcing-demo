package com.ab.saga.orderservice.query.service;

import com.ab.commonapi.events.OrderCreatedEvent;
import com.ab.commonapi.events.OrderStatusUpdatedEvent;
import com.ab.saga.orderservice.query.dto.GetAllOrdersQueryDto;
import com.ab.saga.orderservice.query.dto.GetOrderRequestDto;
import com.ab.saga.orderservice.query.dto.OrderQueryResponseDto;
import com.ab.saga.orderservice.query.entity.Order;
import com.ab.saga.orderservice.query.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class QueryOrderService {

    private final OrderRepository orderRepository;

    @Transactional
    @EventHandler
    public void onOrderCreatedEvent(OrderCreatedEvent orderCreatedEvent) {
        Order order = new Order();

        order.setId(orderCreatedEvent.orderId());
        order.setUserId(orderCreatedEvent.userId());
        order.setAmount(orderCreatedEvent.amount());
        order.setOrderStatus(orderCreatedEvent.orderStatus());

        orderRepository.save(order);
    }

    @Transactional
    @EventHandler
    public void onOrderStatusUpdatedEvent(OrderStatusUpdatedEvent orderStatusUpdatedEvent) {
        orderRepository.findById(orderStatusUpdatedEvent.orderId())
                .ifPresent(order -> order.setOrderStatus(orderStatusUpdatedEvent.orderStatus()));
    }

    @QueryHandler
    public List<OrderQueryResponseDto> getAllOrders(GetAllOrdersQueryDto queryDto) {
        return orderRepository.findAll().stream().map(this::orderToOrderQueryResponseDto)
                .collect(Collectors.toList());
    }

    @QueryHandler
    public OrderQueryResponseDto findById(GetOrderRequestDto requestDto) {
        var foundOrder = orderRepository.findById(requestDto.getOrderId()).
                orElseThrow(() -> new RuntimeException("Resource Not Found"));

        return orderToOrderQueryResponseDto(foundOrder);
    }

    private OrderQueryResponseDto orderToOrderQueryResponseDto(Order order) {
        var responseDto = new OrderQueryResponseDto();
        responseDto.setId(order.getId());
        responseDto.setUserId(order.getUserId());
        responseDto.setAmount(order.getAmount());
        responseDto.setOrderStatus(order.getOrderStatus());
        return responseDto;
    }

}
