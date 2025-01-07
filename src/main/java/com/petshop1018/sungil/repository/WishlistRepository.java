package com.petshop1018.sungil.repository;

import com.petshop1018.sungil.domain.Cart;
import com.petshop1018.sungil.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Long> {
    Wishlist findByMemberId(Long memberId);
}
