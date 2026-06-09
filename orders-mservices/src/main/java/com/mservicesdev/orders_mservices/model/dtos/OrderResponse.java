package com.mservicesdev.orders_mservices.model.dtos;

import java.util.List;

public record OrderResponse(Long id,
                            String orderNumber,
                            List<OrderItemResponse> orderItems
) {


}
