package com.petshop1018.sungil.repository;

import com.petshop1018.sungil.domain.Cart;
import com.petshop1018.sungil.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    boolean existsByCart_memberIdAndCart_idAndProductId(Long memberId, Long cartId, Long productId);
    Optional<CartItem> findByCart_memberIdAndCart_idAndProductId(Long memberId, Long cartId, Long productId);

    boolean existsByCart_memberIdAndId(Long memberId, Long cartItemId);
    void deleteByCart_memberIdAndId(Long memberId, Long cartItemId);

    CartItem findByCartIdAndProductId(Long cartId, Long productId);



    CartItem findCartItemById(Long id);
    List<CartItem> findCartItemByProductId(Long productId);
}
