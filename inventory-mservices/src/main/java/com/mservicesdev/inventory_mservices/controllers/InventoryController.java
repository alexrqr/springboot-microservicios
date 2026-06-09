package com.mservicesdev.inventory_mservices.controllers;

import com.mservicesdev.inventory_mservices.model.dtos.BaseResponse;
import com.mservicesdev.inventory_mservices.model.dtos.OrderItemRequest;
import com.mservicesdev.inventory_mservices.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    /**
     * Validacion si hay en stock
     * @param sku
     * @return
     */
    @GetMapping("/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public boolean inStock(@PathVariable("sku") String sku) {
        return inventoryService.isInStock(sku);
    }

    @PostMapping("/in-stock")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse areInStock(@RequestBody List<OrderItemRequest> orderItems) {
        return inventoryService.areInStock(orderItems);
    }


}
