package com.petshop1018.sungil.dto;

import com.petshop1018.sungil.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private List<CategoryDto> subCategories;

    // Category 엔티티를 DTO로 변환하는 메서드
    public static CategoryDto fromEntity(Category category) {
        List<CategoryDto> subCategories = category.getSubCategories().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
        return new CategoryDto(category.getId(), category.getName(), subCategories);
    }
}