package com.petshop1018.sungil.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity@Data
@Table(name="wishlistItems")
public class WishlistItem extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="wishlist_id")
    private Wishlist wishlist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    public static WishlistItem createWishListItem(Wishlist wishlist,Product product){
        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setWishlist(wishlist);
        wishlistItem.setProduct(product);
        return wishlistItem;
    }
}
