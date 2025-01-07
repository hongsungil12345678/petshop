package com.petshop1018.sungil.domain;

import com.petshop1018.sungil.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
@RequiredArgsConstructor
@Component
public class CategoryInitialize implements CommandLineRunner {
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            // 상위 카테고리 등록
            Category food = createCategory("Food", null,null);
            Category clothesAndAccessories = createCategory("Clothes & Accessories", null,null);
            Category equipment = createCategory("Equipment & Appliances", null,null);
            Category health = createCategory("Health & Hygiene", null,null);
            Category others = createCategory("Others", null,null);

            // 하위 카테고리 등록 및 부모 카테고리 연결
            Category dogFood = createCategory("Dog Food", food,"dog");
            Category catFood = createCategory("Cat Food", food,"cat");
            Category fishFood = createCategory("Fish Food", food,"fish");
            Category dogTreats = createCategory("Dog Treats", food,"dog");

            Category dogClothes = createCategory("Dog Clothes", clothesAndAccessories,"dog");
            Category catClothes = createCategory("Cat Clothes", clothesAndAccessories,"cat");
            Category dogLeash = createCategory("Dog Leash", clothesAndAccessories,"dog");

            Category dogHouse = createCategory("Dog House", equipment,"dog");
            Category fishTank = createCategory("Fish Tank", equipment,"fish");
            Category birdCage = createCategory("Bird Cage", equipment,"bird");

            Category dogShampoo = createCategory("Dog Shampoo", health,"dog");
            Category fleaTreatment = createCategory("Flea Treatment", health,"pet");

            Category travelAccessories = createCategory("Travel Accessories", others,"etc");

            System.out.println("Categories initialized.");
        }
    }

    // 카테고리 생성 메서드
    private Category createCategory(String name, Category parentCategory,String tags) {
        return categoryRepository.findByName(name).orElseGet(() -> {
            Category category = new Category(name,tags);
            if (parentCategory != null) {
                category.setParentCategory(parentCategory);
                parentCategory.getSubCategories().add(category); // 부모 카테고리 하위 카테고리 추가
                category.setTags(tags);
            }
            return categoryRepository.save(category);
        });
    }
}