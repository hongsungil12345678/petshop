package com.petshop1018.sungil.service;

import com.petshop1018.sungil.domain.*;
import com.petshop1018.sungil.repository.WishlistItemRepository;
import com.petshop1018.sungil.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class WishListItemService {
    private final WishlistItemRepository wishlistItemRepository;
    private final WishlistRepository wishlistRepository;


    // wishlistItem 상품 추가
    @Transactional
    public WishlistItem addWishlistItem(Wishlist wishlist, Product product) {
        WishlistItem wishlistItem = wishlistItemRepository.findByWishlistIdAndProductId(wishlist.getId(), product.getId());

        if (wishlistItem != null) {
            // 상품이 이미 wishlistItem 있다면 그대로
        } else {
            // wishlistItem 상품이 없다면 새로 추가
            wishlistItem = WishlistItem.createWishListItem(wishlist, product);
            wishlistItemRepository.save(wishlistItem);
            wishlist.setWishListQuantity(wishlist.getWishListQuantity()+1);
        }
        // 총 개수 갱신
        wishlistRepository.save(wishlist);
        return wishlistItem;
    }

    @Transactional
    public WishlistItem updateWishlistItem(WishlistItem wishlistItem){
        return wishlistItemRepository.save(wishlistItem);
    }

    // 장바구니에서 상품 삭제
    @Transactional
    public void removeWishlistItem(Long wishlistItemId) {
        WishlistItem wishlistItem = wishlistItemRepository.findById(wishlistItemId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid CartItem ID"));

        Wishlist wishlist = wishlistItem.getWishlist();
        wishlist.setWishListQuantity(wishlist.getWishListQuantity()-1);
        wishlistItemRepository.delete(wishlistItem);

        wishlistRepository.save(wishlist);
    }
    // 장바구니에서 특정 상품 정보 조회
    public WishlistItem getWishlistItemById(Long wishlistItemId) {
        return wishlistItemRepository.findById(wishlistItemId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid CartItem ID"));
    }

}
