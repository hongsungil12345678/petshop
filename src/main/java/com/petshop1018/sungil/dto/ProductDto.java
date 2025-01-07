package com.petshop1018.sungil.dto;
import com.petshop1018.sungil.domain.Product;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private double price;
    private String imageUrl;


    // Product 엔티티를 ProductDto로 변환하는 메서드
    public static ProductDto fromEntity(Product product) {
        return new ProductDto(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getImageUrl()
        );
    }
    // Product 리스트를 ProductDto 리스트로 변환하는 메서드
    public static List<ProductDto> fromEntityList(List<Product> products) {
        return products.stream()
                .map(ProductDto::fromEntity)
                .collect(Collectors.toList());
    }
}