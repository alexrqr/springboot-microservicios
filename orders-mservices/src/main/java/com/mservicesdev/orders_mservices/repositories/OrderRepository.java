package com.mservicesdev.orders_mservices.repositories;

import com.mservicesdev.orders_mservices.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {



}
