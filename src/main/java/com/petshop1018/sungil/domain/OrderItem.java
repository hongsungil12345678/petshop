package com.petshop1018.sungil.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Builder
@AllArgsConstructor
@NoArgsConstructor @Getter
@Setter @Entity
@Table(name = "order_items")
public class OrderItem extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 양방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;  // OrderItem은 Order와 N:1 관계

    // 단방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;  // OrderItem은 Product와 N:1 관계
    private int quantity;
    private double price;
    private double totalPrice;

    public static OrderItem createOrderItem(Product product, int quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(product.getPrice());
        orderItem.setTotalPrice(product.getPrice()*quantity);
        return orderItem;
    }

}
