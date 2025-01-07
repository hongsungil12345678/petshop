package com.petshop1018.sungil.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
public class AddProductDto implements Serializable {
    private String title;
    private double price;
    private String description;
    private Long parentCategoryId; // 상위 카테고리 ID
    private Set<Long> categoryIds;
    private int stockQuantity;
    private String imageUrl;
    private String imageName;
}
