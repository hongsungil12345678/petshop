package com.petshop1018.sungil.domain;

import com.petshop1018.sungil.repository.CategoryRepository;
import com.petshop1018.sungil.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@RequiredArgsConstructor
@Component
public class ProductInitializer implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
            // 카테고리 초기화
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

            // 상품 초기화
            List<Product> products = List.of(
                    createProduct("Premium Dog Food", "High-quality food for your dog", 50, 200, food,
                            Set.of(dogFood), "item1.jpg"),
                    createProduct("Cat Litter Box", "Easy-to-clean litter box for cats", 30, 150, food,
                            Set.of(catFood), "item2.jpg"),
                    createProduct("Chew Toy for Dogs", "Durable chew toy to keep your dog entertained", 15, 300, equipment,
                            Set.of(dogLeash), "item3.jpg"),
                    createProduct("Fish Tank", "50L glass aquarium for fish", 120, 40, equipment,
                            Set.of(fishTank), "item4.jpg"),
                    createProduct("Dog Shampoo", "Gentle shampoo for dogs", 15, 250, health,
                            Set.of(dogShampoo), "item5.jpg"),
                    createProduct("Pet Travel Carrier", "Portable carrier for dogs and cats", 45, 70, others,
                            Set.of(travelAccessories), "item6.jpg"),
                    createProduct("Dog Treats", "Delicious treats for your dog", 20, 500, food,
                            Set.of(dogTreats), "item7.jpg"),
                    createProduct("Dog Leash", "Durable leash for your dog", 25, 300, clothesAndAccessories,
                            Set.of(dogLeash), "item8.jpg"),
                    createProduct("Cat Clothes", "Stylish clothes for your cat", 30, 150, clothesAndAccessories,
                            Set.of(catClothes), "item9.jpg"),
                    createProduct("Dog House", "Comfortable house for your dog", 100, 50, equipment,
                            Set.of(dogHouse), "item10.jpg"),
                    createProduct("Fish Food", "Nutritious food for your fish", 10, 400, food,
                            Set.of(fishFood), "item11.jpg"),
                    createProduct("Cat Bed", "Soft bed for your cat", 40, 200, food,
                            Set.of(catFood), "item12.jpg"),
                    createProduct("Bird Cage", "Cage for your pet bird", 60, 100, equipment,
                            Set.of(birdCage), "item13.jpg"),
                    createProduct("Flea Treatment", "Treatment for fleas on dogs", 25, 350, health,
                            Set.of(fleaTreatment), "item14.jpg"),
                    createProduct("Dog Shampoo", "Gentle shampoo for dogs", 20, 400, health,
                            Set.of(dogShampoo), "item15.jpg"),
                    createProduct("Travel Accessories Set", "Complete travel set for pets", 60, 80, others,
                            Set.of(travelAccessories), "item16.jpg"),
                    createProduct("Cat Scratcher", "Scratch post for your cat", 15, 500, food,
                            Set.of(catFood), "item17.jpg"),
                    createProduct("Portable Dog Bed", "Portable bed for dogs on the go", 35, 100, food,
                            Set.of(dogFood), "item18.jpg"),
                    createProduct("DATA INITIALIZER END POINT", "Muzzle to control dog barking", 12, 250, health,
                            Set.of(dogShampoo), "item19.jpg")
            );
            productRepository.saveAll(products);
            System.out.println("Initial products and categories saved.");
        }
    }

    private Category createCategory(String name, Category parentCategory,String tags) {
        return categoryRepository.findByName(name).orElseGet(() -> {
            Category category = new Category(name,tags);
            category.setParentCategory(parentCategory); // 부모 카테고리 설정
            return categoryRepository.save(category);
        });
    }

    private Product createProduct(String title, String description, double price, int stockQuantity, Category mainCategory, Set<Category> selectedSubCategories, String imageName) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        product.setImageUrl("/files/" + imageName);
        product.setImageName(imageName);
        product.setOrderQuantity(0);

        // 상품 카테고리 설정
        Set<ProductCategory> productCategories = new HashSet<>();

        // 주 카테고리 추가
        productCategories.add(new ProductCategory(product, mainCategory));  // 상위 카테고리

        // 선택된 하위 카테고리들을 추가
        for (Category subCategory : selectedSubCategories) {
            productCategories.add(new ProductCategory(product, subCategory));  // 하위 카테고리들
        }
        product.setProductCategories(productCategories);
        return product;
    }
}