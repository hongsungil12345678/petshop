package com.petshop1018.sungil.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * 굳이굳이 카테고리 따로 분리한 이유 -> 카테고리 별로 검색을 위해서
 * */

@NoArgsConstructor
@AllArgsConstructor
@Entity @Getter @Setter
@Table(name = "product_category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public ProductCategory(Product product, Category category) {
        this.product = product;
        this.category=category;
    }
}