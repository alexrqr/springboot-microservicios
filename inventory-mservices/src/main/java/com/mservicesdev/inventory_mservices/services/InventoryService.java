package com.mservicesdev.inventory_mservices.services;

import com.mservicesdev.inventory_mservices.model.dtos.BaseResponse;
import com.mservicesdev.inventory_mservices.model.dtos.OrderItemRequest;
import com.mservicesdev.inventory_mservices.model.entities.Inventory;
import com.mservicesdev.inventory_mservices.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;


    /**
     * Validacion de existencia y cantidad de invetario > 0
     * @param sku
     * @return
     */
    public boolean isInStock(String sku) {
        var inventory = inventoryRepository.findBySku(sku);

        return inventory.filter(value -> value.getQuantity() > 0).isPresent();
    }

    /**
     *
     * @param orderItems
     * @return
     */
    public BaseResponse areInStock(List<OrderItemRequest> orderItems) {
        var errorList = new ArrayList<String>();

        List<String> skus = orderItems.stream().map(OrderItemRequest::getSku).toList();

        List<Inventory> inventoryList = inventoryRepository.findBySkuIn(skus);

        orderItems.forEach(orderItem -> {
           var inventory = inventoryList.stream().filter(value -> value.getSku().equals(orderItem.getSku())).findFirst();

           if(inventory.isEmpty()) {
               errorList.add("Product with sku " + orderItem.getSku() + "does not exist");
           } else if (inventory.get().getQuantity() < orderItem.getQuantity()) {
               errorList.add("Product with sku "+orderItem.getSku() + "has insuficient quantity");
           }
        });

        return errorList.size() > 0 ? new BaseResponse(errorList.toArray(new String[0])) : new BaseResponse(null);
    }

}
