package com.petshop1018.sungil.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderItemDto implements Serializable {
    private Long cartItemId;
    private Long productId;
    private Long memberId;
    private String cartItemTitle;
    private int quantity;
    private double price;
    private double subtotal;
    private String imageUrl;
    private String createdDate;
}
