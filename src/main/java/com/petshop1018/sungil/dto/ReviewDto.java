package com.petshop1018.sungil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class ReviewDto {
    private Long productId;
    private double rating;
    private String content;
}
