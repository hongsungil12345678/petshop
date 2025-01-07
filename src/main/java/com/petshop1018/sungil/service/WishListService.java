package com.petshop1018.sungil.service;

import com.petshop1018.sungil.domain.Member;
import com.petshop1018.sungil.domain.Product;
import com.petshop1018.sungil.domain.Wishlist;
import com.petshop1018.sungil.domain.WishlistItem;
import com.petshop1018.sungil.repository.ProductRepository;
import com.petshop1018.sungil.repository.WishlistItemRepository;
import com.petshop1018.sungil.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WishListService {
    private final ProductRepository productRepository;
    private final WishlistRepository wishlistRepository;
    private final WishlistItemRepository wishlistItemRepository;
    private final WishListItemService wishListItemService;
    // 회원 wishlist 조회 및 생성

    @Transactional
    public Wishlist getWishlistByMember(Member member){
        Wishlist wishlist = wishlistRepository.findByMemberId(member.getId());

        if(wishlist == null){
            wishlist = Wishlist.createWishList(member);
            wishlistRepository.save(wishlist);
        }
        return wishlist;
    }


    // wishlist 추가
    @Transactional
    public Wishlist addItemToWishlist(Member member,Long productId){
        // 조회
        Wishlist wishlist = getWishlistByMember(member);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Product ID"));

        wishListItemService.addWishlistItem(wishlist,product);

        wishlistRepository.save(wishlist);
        return wishlist;
    }

    @Transactional
    public void removeItemFromWishlist(Member member,Long wishlistItemId){
        // 조회
        Wishlist wishlist = getWishlistByMember(member);

        wishListItemService.removeWishlistItem(wishlistItemId);
        wishlistRepository.save(wishlist);
    }

    @Transactional
    public List<WishlistItem> getWishlistItems(Member member){
        // 조회
        Wishlist wishlist = getWishlistByMember(member);
        return wishlist.getWishListItemList();
    }
    @Transactional
    public List<WishlistItem> getMemberWishlistItem(Wishlist wishlist){

        Long wishlistId = wishlist.getId();

        // 조건에 부합하는 것 넣어둘 곳
        List<WishlistItem> wishlistItemList = new ArrayList<>();
        // 전부 조회
        List<WishlistItem> wishlistItems = wishlistItemRepository.findAll();
        // Id 같은거 찾아서 넣어줌
        for(WishlistItem wishListItem: wishlistItems){
            if(wishListItem.getWishlist().getId() == wishlistId){
                wishlistItemList.add(wishListItem);
            }
        }

        return wishlistItemList;
    }

}
