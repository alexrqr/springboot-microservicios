package com.mservicesdev.orders_mservices.model.dtos;

public record OrderItemResponse (
        Long id, String sku, Double price, Long quantity
) {



}
