package com.petshop1018.sungil.controller;

import com.petshop1018.sungil.config.LoginUser;
import com.petshop1018.sungil.domain.Cart;
import com.petshop1018.sungil.domain.CartItem;
import com.petshop1018.sungil.domain.Member;
import com.petshop1018.sungil.domain.Wishlist;
import com.petshop1018.sungil.dto.MemberDto;
import com.petshop1018.sungil.dto.OrderItemDto;
import com.petshop1018.sungil.service.CartItemService;
import com.petshop1018.sungil.service.CartService;
import com.petshop1018.sungil.service.MemberService;
import com.petshop1018.sungil.service.WishListService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final MemberService memberService;
    private final WishListService wishListService;
    // 장바구니 수정 데이터를 세션에 저장하는 엔드포인트
    @PostMapping("/update/updateItem")
    @ResponseBody
    public List<OrderItemDto> updateCartSession(@RequestBody List<OrderItemDto> orderData, HttpSession session) {
        Long memberId = orderData.get(0).getMemberId();
        Member member = memberService.findMemberById(memberId);
        List<CartItem> cartItems = cartService.getCartItems(member);
        // 3. 변경된 데이터를 업데이트
        for (OrderItemDto orderItem : orderData) {
            cartItems.stream()
                    .filter(cartItem -> cartItem.getId().equals(orderItem.getCartItemId()))
                    .findFirst()
                    .ifPresent(cartItem -> {
                        // DB에 업데이트
                        cartItemService.updateCartItem(cartItem,orderItem.getQuantity());
                    });
        }
        Cart cart = cartService.updateCartQuantity(member);
        // 4. 세션에 업데이트된 장바구니 아이템 저장
        session.setAttribute("cart",cart);
        session.setAttribute("cartItems", cartItems);
        return orderData;
    }

    @PostMapping("/update/removeItem")
    public ResponseEntity<Void> removeCartItem(@RequestBody Map<String, String> payload,HttpSession session) {
        String cartItemId = payload.get("cartItemId");
        String memberId = payload.get("memberId");
        Member memberById = memberService.findMemberById(Long.valueOf(memberId));
        Cart cart = cartService.removeItemFromCart(memberById, Long.valueOf(cartItemId));
        List<CartItem> cartItems = cart.getCartItems();
        session.setAttribute("cart",cart);
        session.setAttribute("cartItems",cartItems);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/update/removeWishlistItem")
    public ResponseEntity<Void> removeWishlistItem(@RequestBody Map<String, String> payload) {
        String wishlistItemId = payload.get("wishlistItemId");
        String memberId = payload.get("memberId");
        Member memberById = memberService.findMemberById(Long.valueOf(memberId));
//        Wishlist wishlistByMember = wishListService.getWishlistByMember(memberById);
        wishListService.removeItemFromWishlist(memberById,Long.valueOf(wishlistItemId));
        return ResponseEntity.ok().build();
    }
}
