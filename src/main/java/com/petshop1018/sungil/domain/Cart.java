package com.petshop1018.sungil.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor@AllArgsConstructor
@Entity@Getter @Setter
@Table(name = "carts")
public class Cart extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();  // Cart는 여러 CartItem을 가짐
    private int cartQuantity;
    private double cartTotalPrice;
    public static Cart createCart(Member member) {
        Cart cart = new Cart();
        cart.setMember(member);
        cart.setCartQuantity(0);  // 장바구니의 총 수량 초기화
        cart.setCartTotalPrice(0);
        return cart;
    }

}