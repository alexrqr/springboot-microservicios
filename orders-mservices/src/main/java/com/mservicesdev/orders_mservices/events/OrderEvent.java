package com.mservicesdev.orders_mservices.events;

import com.mservicesdev.orders_mservices.model.enums.OrderStatus;

public record OrderEvent (String orderNumber, int itemsCount, OrderStatus orderStatus) {
}
