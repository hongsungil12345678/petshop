package com.petshop1018.sungil.service;

import com.petshop1018.sungil.domain.Cart;
import com.petshop1018.sungil.domain.CartItem;
import com.petshop1018.sungil.domain.Product;
import com.petshop1018.sungil.dto.AddCartItemDto;
import com.petshop1018.sungil.repository.CartItemRepository;
import com.petshop1018.sungil.repository.CartRepository;
import com.petshop1018.sungil.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service@Slf4j
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    // 장바구니에 상품 추가
    @Transactional
    public CartItem addCartItem(Cart cart, Product product, int quantity,String color,String size) {
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId());

        if (cartItem != null) {
            // 상품이 이미 장바구니에 있다면 수량만 증가 , cart 갯수 변화 +  옵션 바뀔 수 있으니까 같이 적용시켜주기-> color, size 각각 조회 추가 굳이?
            cartItem.setQuantity(quantity);
            cartItem.setSize(size);
            cartItem.setColor(color);
            cartItemRepository.save(cartItem);
        } else {
            // 장바구니에 상품이 없다면 새로 추가
            cartItem = CartItem.createCartItem(cart, product, quantity,color,size);
            cartItemRepository.save(cartItem);
        }
        return cartItem;
    }

    @Transactional
    public CartItem updateCartItem(CartItem cartItem,int quantity){
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    // 장바구니에서 상품 삭제
    @Transactional
    public void removeCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid CartItem ID"));

        Cart cart = cartItem.getCart();
        // cart TotalPrice
        double totalPrice = cartItem.getProduct().getPrice() * cartItem.getQuantity();
        double temp = cart.getCartTotalPrice();
        cart.setCartTotalPrice(temp-totalPrice);
        cart.setCartQuantity(cartItem.getCart().getCartQuantity() - cartItem.getQuantity());

        cartItemRepository.delete(cartItem);
        cartRepository.save(cart);
    }

    // 장바구니에서 특정 상품 정보 조회
    public CartItem getCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid CartItem ID"));
    }
}
