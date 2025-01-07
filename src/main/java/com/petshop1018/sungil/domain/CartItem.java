package com.petshop1018.sungil.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity@Data
@Table(name = "cart_items")
public class CartItem extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore  // CartItem에서 Cart 참조를 직렬화하지 않도록 함
    private Cart cart;  // CartItem은 Cart와 N:1 관계

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;  // CartItem은 Product와 N:1 관계

    private int quantity;
// 옵션
    private String color;
    private String size;
    public static CartItem createCartItem(Cart cart, Product product, int quantity,String color,String size) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setColor(color);
        cartItem.setSize(size);
        return cartItem;
    }

    // 수량 추가
//    public void increaseQuantity(int amount) {
//        this.quantity += amount;
//    }
//
//    // 수량 감소
//    public void decreaseQuantity(int amount) {
//        if (this.quantity - amount >= 0) {
//            this.quantity -= amount;
//        } else {
//            throw new IllegalArgumentException("수량은 0보다 작을 수 없습니다.");
//        }
//    }
//    // 상품 삭제 시 장바구니 상품 삭제 메서드 추가 가능
//    public void removeFromCart() {
//        this.cart.getCartItems().remove(this);
//    }
}

