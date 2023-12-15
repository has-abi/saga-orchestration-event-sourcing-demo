package com.ab.saga.orderservice.query.repository;

import com.ab.saga.orderservice.query.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
