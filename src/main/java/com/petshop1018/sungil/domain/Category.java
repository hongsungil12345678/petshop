package com.petshop1018.sungil.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Entity@Getter@Setter@NoArgsConstructor
@Table(name = "categories")
public class Category extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    // 카테고리 관련 태그 - 상위 카테고리, 하위 카테고리로 해결 안되는게 많아서 태그 넣어서 해결
    private String tags;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    @JsonBackReference // 부모 카테고리는 역참조로 처리
    private Category parentCategory; // 상위 카테고리

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // 자식 카테고리는 관리 참조로 처리
    private Set<Category> subCategories = new HashSet<>(); // 하위 카테고리들

    @OneToMany(mappedBy = "category")
    private Set<ProductCategory> productCategories = new HashSet<>(); // 상품과의 관계
    public Category(String name,String tags) {
        this.name = name;
        this.tags = tags;
    }

    public Category(String name, Category parentCategory,String tags) {
        this.name = name;
        this.parentCategory = parentCategory;
        this.tags = tags;
    }
}
