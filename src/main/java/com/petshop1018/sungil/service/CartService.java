package com.petshop1018.sungil.service;

import com.petshop1018.sungil.domain.*;
import com.petshop1018.sungil.repository.CartItemRepository;
import com.petshop1018.sungil.repository.CartRepository;
import com.petshop1018.sungil.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor@Slf4j
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemService cartItemService;
    private final CartItemRepository cartItemRepository;

    private final ProductRepository productRepository;

    // 회원의 장바구니 조회
    @Transactional
    public Cart getCartByMember(Member member) {
        Cart cart = cartRepository.findByMemberId(member.getId());

        if (cart == null) {
            // 장바구니가 존재하지 않으면 새로 생성
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }
        return cart;
    }

    // 장바구니에 상품 추가
    @Transactional
    public Cart addItemToCart(Member member, Long productId, int quantity,String color,String size) {
        // 회원의 장바구니 조회
        Cart cart = getCartByMember(member);

        // 상품 조회
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Product ID"));

        // 장바구니에 상품 추가
        cartItemService.addCartItem(cart, product, quantity,color,size);
        
        // 장바구니 업데이트
        Cart cart1 = updateCartQuantity(member);
//        // 변경된 장바구니 저장
//        cartRepository.save(cart);

        return cart1;
    }

    // 장바구니에서 상품 삭제
    @Transactional
    public Cart removeItemFromCart(Member member, Long cartItemId) {
        // 회원의 장바구니 조회
        Cart cart = getCartByMember(member);

        // 해당 상품을 장바구니에서 제거
        cartItemService.removeCartItem(cartItemId);

        // 변경된 장바구니 저장
        cartRepository.save(cart);
        return cart;
    }

    // 장바구니 비우기
    @Transactional
    public void clearCart(Member member) {
        Cart cart = getCartByMember(member);

        // 장바구니 아이템 모두 삭제
        // 전체 cartItem 찾기
        List<CartItem> cartItems = cartItemRepository.findAll();

        // 반복문을 이용하여 해당하는 User 의 CartItem 만 찾아서 삭제
        for(CartItem cartItem : cartItems){
            if(cartItem.getCart().getId()==cart.getId())
                cartItemRepository.delete(cartItem);
        }
        cart.setCartQuantity(0);
        cart.setCartTotalPrice(0);
        cartRepository.save(cart);
    }
    @Transactional
    public List<CartItem> getCartItems(Member member) {
        Cart cart = getCartByMember(member);
        return cart.getCartItems();
    }
    @Transactional
    public Cart updateCartQuantity(Member member){
        Cart cart = getCartByMember(member);
        int cartQuantity=0;
        double totalPrice=0;
        List<CartItem> cartItems = cart.getCartItems();
        for(CartItem cartItem:cartItems){
            cartQuantity+= cartItem.getQuantity();
            totalPrice+=cartItem.getProduct().getPrice()*cartItem.getQuantity();
        }
        cart.setCartQuantity(cartQuantity);
        cart.setCartTotalPrice(totalPrice);
        return cartRepository.save(cart);
    }
//    @Transactional
//    public Order placeOrder(Member member) {
//        Cart cart = getCartByMember(member);
//
//        if (cart == null || cart.getCartItems().isEmpty()) {
//            throw new IllegalStateException("장바구니가 비어 있습니다.");
//        }
//
//        List<CartItem> cartItems = cart.getCartItems();
//        Order order = orderService.createOrder(member, cartItems);
//
//        // 장바구니에서 상품 삭제
//        cartItemService.removeCartItems(cartItems);
//
//        return order;
//    }

    //    // 카트 상품 리스트 중 해당하는 유저가 담은 상품만 반환
    // 유저의 카트 id와 카트상품의 카트 id가 같아야 함
    public List<CartItem> getMemberCartItem(Cart memberCart) {

        // 유저의 카트 id를 꺼냄
        Long memberCartId = memberCart.getId();

        // id에 해당하는 유저가 담은 상품들 넣어둘 곳
        List<CartItem> userCartItems = new ArrayList<>();

        // 유저 상관 없이 카트에 있는 상품 모두 불러오기
        List<CartItem> cartItems = cartItemRepository.findAll();

        for(CartItem cartItem : cartItems) {
            if(cartItem.getCart().getId() == memberCartId) {
                userCartItems.add(cartItem);
            }
        }

        return userCartItems;
    }
    @Transactional
    public Cart getMemberCartSession(Member member, HttpSession session){
        Cart cart = cartRepository.findByMember(member);
        List<CartItem> cartItems;
        if(cart == null){
            cart = Cart.createCart(member);
            cartItems = new ArrayList<>();
            cart.setCartItems(cartItems);
            cartRepository.save(cart);
        }
        else{
            cartItems = cart.getCartItems();
        }
        session.setAttribute("cart",cart);
        session.setAttribute("cartItems",cartItems);
        return cart;
    }
}
