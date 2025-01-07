package com.petshop1018.sungil.repository;
import com.petshop1018.sungil.domain.Product;
import com.petshop1018.sungil.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {
    List<ProductCategory> findByProductId(Long productId);
    List<ProductCategory> findAll();
    ProductCategory findByProductIdAndCategoryId(Long productId, Long categoryId);
    List<Product> findByCategoryId(Long categoryId);

}
