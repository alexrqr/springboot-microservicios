package com.mservicesdev.orders_mservices.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity()
@Table(name = "order_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;
    private Double price;
    private Long quantity;

    // Relacion con ORDER
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;



}
