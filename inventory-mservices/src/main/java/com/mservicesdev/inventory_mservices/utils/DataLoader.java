/**
 * Clase para generar data de prueba en caso esté vacío la tabla...
 */
package com.mservicesdev.inventory_mservices.utils;

import com.mservicesdev.inventory_mservices.model.entities.Inventory;
import com.mservicesdev.inventory_mservices.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) throws Exception {
      log.info("Loading data for inventory......");

      if (inventoryRepository.findAll().size() == 0) { // si está vacío se agregarían registros....
          inventoryRepository.saveAll(
                  List.of(
                          Inventory.builder().sku("000001").quantity(10L).build(),
                          Inventory.builder().sku("000002").quantity(20L).build(),
                          Inventory.builder().sku("000003").quantity(30L).build(),
                          Inventory.builder().sku("000004").quantity(40L).build(),
                          Inventory.builder().sku("000005").quantity(50L).build()
                  )
          );
      }

    }
}
