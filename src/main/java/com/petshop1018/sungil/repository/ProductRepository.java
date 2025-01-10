package com.petshop1018.sungil.repository;

import com.petshop1018.sungil.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findProductById(Long id);
    @Query("SELECT p FROM Product p JOIN p.productCategories pc JOIN pc.category c WHERE c.name = :categoryName")
    List<Product> findByCategoryName(@Param("categoryName") String categoryName);

    @Query("SELECT p FROM Product p JOIN p.productCategories pc JOIN pc.category c WHERE c.name <> :categoryName")
    List<Product> findByCategoryNameNot(@Param("categoryName") String categoryName);

    @Query("SELECT p FROM Product p JOIN p.productCategories pc WHERE pc.category.id = :categoryId")
    List<Product> findByCategoryId(@Param("categoryId") Long categoryId);

    // orderQuantity 기준 내림차순 4개 조회 , 초기에는 주문이 다 0이니까 정렬 기준 (ID) 하나 더 추가해줌
    @Query("SELECT p FROM Product p ORDER BY p.orderQuantity DESC,p.id ASC")
    List<Product> findTOP6ByOrderQuantity(Pageable pageable);
    /**
     * product - productcategory - category 묶여 있어서
     * */
    @Query("SELECT p FROM Product p JOIN ProductCategory pc ON p.id = pc.product.id JOIN Category c ON pc.category.id = c.id WHERE c.tags = :categoryTag")
    List<Product> findByCategoryTags(@Param("categoryTag")String categoryTag);
// 페이징 추가
//    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
//    List<Product> findProductsByPriceRange(@Param("minPrice")double minPrice,@Param("maxPrice")double maxPrice);
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    Page<Product> findProductsByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice, Pageable pageable);
    Page<Product> findAll(Pageable pageable);

    @Query("SELECT p FROM Product p JOIN p.productCategories pc JOIN pc.category c WHERE c.name = :categoryName")
    Page<Product> findByCategoryName(@Param("categoryName") String categoryName, Pageable pageable);

    @Query("SELECT p FROM Product p JOIN ProductCategory pc ON p.id = pc.product.id JOIN Category c ON pc.category.id = c.id WHERE c.tags = :categoryTag")
    Page<Product> findByCategoryTags(@Param("categoryTag")String categoryTag,Pageable pageable);
}
