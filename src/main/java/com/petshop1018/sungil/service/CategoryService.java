package com.petshop1018.sungil.service;

import com.petshop1018.sungil.domain.Category;
import com.petshop1018.sungil.domain.Product;
import com.petshop1018.sungil.domain.ProductCategory;
import com.petshop1018.sungil.dto.CategoryDto;
import com.petshop1018.sungil.repository.CategoryRepository;
import com.petshop1018.sungil.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductCategoryRepository productCategoryRepository;

    /**
     * 카테고리 연결
     */
    public void assignCategoriesToProduct(Product product,Long parentCategoryId,Set<Long> categoryIds) {
        Set<Category> categories = getCategoriesByIds(categoryIds);
        for (Category category : categories) {
            ProductCategory productCategory = new ProductCategory(product, category);
            productCategoryRepository.save(productCategory);
        }
        // 최상위 카테고리 저장
        Category parentCategory = getCategory(parentCategoryId);
        ProductCategory parentProductCategory = new ProductCategory(product,parentCategory);
        productCategoryRepository.save(parentProductCategory);
    }

    public List<CategoryDto> getCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }
    //상위 카테고리 ID와 하위 카테고리 리스트 매핑
    public Map<Long, List<CategoryDto>> getSubCategoriesMap(List<CategoryDto> categories) {
        Map<Long, List<CategoryDto>> subCategoriesMap = new HashMap<>();
        for (CategoryDto parentCategory : categories) {
            List<CategoryDto> subCategories = parentCategory.getSubCategories();
            subCategoriesMap.put(parentCategory.getId(), subCategories);
        }
        return subCategoriesMap;
    }
    // 최상위 카테고리
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAllByParentCategoryIsNull();
        return categories.stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow();
    }

    public Set<Category> getCategoriesByIds(Set<Long> ids) {
        return ids.stream()
                .map(id -> categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + id)))
                .collect(Collectors.toSet());
    }


}