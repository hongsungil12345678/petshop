package com.petshop1018.sungil.service;

import com.petshop1018.sungil.domain.Category;
import com.petshop1018.sungil.domain.OrderItem;
import com.petshop1018.sungil.domain.Product;
import com.petshop1018.sungil.domain.ProductCategory;
import com.petshop1018.sungil.dto.*;
import com.petshop1018.sungil.repository.ProductCategoryRepository;
import com.petshop1018.sungil.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final CategoryService categoryService;
    private final FileService fileService;
    // 상품 등록
    @Transactional
    public Product createProduct(AddProductDto addProductDto, Set<Long> categoryIds, MultipartFile file) throws IOException {
        String imagePath = saveImageFile(file); // 이미지 파일 저장

        addProductDto.setImageName(imagePath); // 이름
        addProductDto.setImageUrl("/files/"+imagePath);
        Long parentCategoryId = addProductDto.getParentCategoryId();
        Product product = saveProduct(addProductDto); // 상품 생성 및 저장
        categoryService.assignCategoriesToProduct(product,parentCategoryId,categoryIds); // 카테고리 연결

        return product;
    }

    private String saveImageFile(MultipartFile file) throws IOException {
        return fileService.saveFile(file);
    }

    private Product saveProduct(AddProductDto addProductDto) {
        Product product = new Product();
        product.setPrice(addProductDto.getPrice());
        product.setDescription(addProductDto.getDescription());
        product.setImageUrl(addProductDto.getImageUrl());
        product.setImageName(addProductDto.getImageName());
        product.setTitle(addProductDto.getTitle());
        product.setStockQuantity(addProductDto.getStockQuantity());
        product.setOrderQuantity(0);
        return productRepository.save(product);
    }

    // 상품 조회
    public Product  getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
        return product;
    }


    // 상품 조회
    public Product findProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
        return product;
    }

    // 상품 삭제
    @Transactional
    public void deleteProduct(Long productId) throws IOException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
        fileService.deleteFile(product.getImageName());
        productRepository.delete(product);
    }

    public List<Product> getProductsByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }
    public List<Product> getProductsExcludingCategory(String categoryName) {
        return productRepository.findByCategoryNameNot(categoryName);
    }

    public Product getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return product;
    }

    @Transactional(readOnly = true)
    public Set<Category> getProductCategories(Long productId) {
        List<ProductCategory> productCategories = productCategoryRepository.findByProductId(productId);
        if (productCategories.isEmpty()) {
            log.info("No categories found for product with ID: {}", productId);
            return Set.of();
        }

        Set<Category> categories = productCategories.stream()
                .map(ProductCategory::getCategory)
                .collect(Collectors.toSet());

        log.info("Categories for product with ID {}: {}", productId, categories);
        return categories;
    }
//    @Transactional
//    public List<ProductDto> updateStock(List<OrderItemDto> orderData) {
//        List<ProductDto> updatedProducts = new ArrayList<>();
//
//        for (OrderItemDto orderItemDto : orderData) {
//            Product product = productRepository.findById(orderItemDto.getProductId())
//                    .orElseThrow(() -> {
//                        log.error("Product not found with ID: {}", orderItemDto.getProductId());
//                        return new RuntimeException("Product not found");
//                    });
//
//            int orderedQuantity = orderItemDto.getQuantity();
//            int currentStock = product.getStockQuantity();
//
//            if (currentStock >= orderedQuantity) {
//                product.setStockQuantity(currentStock - orderedQuantity);
//                product.setOrderQuantity(orderedQuantity);
//                productRepository.save(product);
//
//                updatedProducts.add(new ProductDto(
//                        product.getId(),
//                        product.getTitle(),
//                        product.getPrice(),
//                        product.getStockQuantity(),
//                        product.getImageUrl(),
//                        product.getCreatedDate(),
//                        product.getOrderQuantity()
//                ));
//            } else {
//                throw new RuntimeException("재고가 부족합니다.");
//            }
//        }
//        return updatedProducts;
//    }

    // BEST SELLING - ORDER QUANTITY 기준으로 6개, ID 오름차순 추가
    @Transactional(readOnly = true)
    public List<Product> getBestSellingProducts(){
        // 상위 6개만 가져오기
        PageRequest pageRequest = PageRequest.of(0,6);
        return productRepository.findTOP6ByOrderQuantity(pageRequest);
    }


    // tags 검색
    @Transactional(readOnly = true)
    public List<Product> getProductsByCategoryTags(String categoryTag){
        return productRepository.findByCategoryTags(categoryTag);
    }
    // 가격 필터링 , 페이징 추가
//    @Transactional(readOnly = true)
//    public List<Product> filterProductsByPrice(double minPrice, double maxPrice) {
//        return productRepository.findProductsByPriceRange(minPrice, maxPrice);
//    }

    public Page<Product> getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return productRepository.findAll(pageable);
    }

    @Transactional
    public Page<Product> getProductsByCategory(String categoryName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size); // 페이지 번호는 0부터 시작
        return productRepository.findByCategoryName(categoryName, pageable);
    }
    @Transactional(readOnly = true)
    public Page<Product> getProductsByCategoryTags(String categoryTag,int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        return productRepository.findByCategoryTags(categoryTag,pageable);
    }
    public Page<Product> filterProductsByPrice(double minPrice, double maxPrice, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findProductsByPriceRange(minPrice, maxPrice, pageable);
    }
}