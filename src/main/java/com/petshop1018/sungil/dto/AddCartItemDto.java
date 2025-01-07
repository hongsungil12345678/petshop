package com.petshop1018.sungil.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddCartItemDto implements Serializable {
    private Long cartId;
    private Long productId;
    private String productTitle;
    private Double productPrice;
    private String productDescription;
    private int quantity;
}
