package com.petshop1018.sungil.repository;

import com.petshop1018.sungil.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(String name);
    boolean existsByName(String name);

    Set<Category> findByIdIn(Set<Long> categoryIds);

    List<Category> findByParentCategoryId(Long parentCategoryId);

    List<Category> findAllByParentCategoryIsNull();

    Set<Category> findAllByParentCategoryIsNullAndProductCategories(Set<Category>productCategories);
}
