package com.petshop1018.sungil.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Entity@Getter @Setter
@Table(name = "wishlists")
public class Wishlist extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;  // Wishlist는 하나의 User에 속함

    @OneToMany(mappedBy = "wishlist",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<WishlistItem> wishListItemList;
    private int wishListQuantity;

    public static Wishlist createWishList(Member member) {
        Wishlist wishlist = new Wishlist();
        wishlist.setMember(member);
        wishlist.setWishListQuantity(0);  // 장바구니의 총 수량 초기화
        return wishlist;
    }
}
