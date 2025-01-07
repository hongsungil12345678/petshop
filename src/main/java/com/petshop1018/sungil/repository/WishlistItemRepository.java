package com.petshop1018.sungil.repository;

import com.petshop1018.sungil.domain.CartItem;
import com.petshop1018.sungil.domain.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem,Long> {
    WishlistItem findByWishlistIdAndProductId(Long wishListId, Long productId);
}
