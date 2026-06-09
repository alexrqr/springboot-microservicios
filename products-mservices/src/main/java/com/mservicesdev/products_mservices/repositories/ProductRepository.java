package com.mservicesdev.products_mservices.repositories;

import com.mservicesdev.products_mservices.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
