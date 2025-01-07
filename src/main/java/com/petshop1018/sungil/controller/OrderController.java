package com.petshop1018.sungil.controller;

import com.petshop1018.sungil.config.LoginUser;
import com.petshop1018.sungil.domain.*;
import com.petshop1018.sungil.dto.MemberDto;
import com.petshop1018.sungil.repository.OrderRepository;
import com.petshop1018.sungil.repository.PaymentRepository;
import com.petshop1018.sungil.repository.ProductRepository;
import com.petshop1018.sungil.service.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final CartService cartService;
    @PostMapping("/orders/payment/success")
    public ResponseEntity<String> handlePaymentSuccess(@LoginUser MemberDto.Response dto, @RequestBody Map<String, String> requestData) {
        String transactionId = requestData.get("transactionId");
        String storeId = requestData.get("storeId");
        String orderId = requestData.get("orderId");
        if (transactionId == null || transactionId.isEmpty()) {
            return ResponseEntity.badRequest().body("Transaction ID is missing.");
        }
        log.info("ORDER CONTROLLER : TRANSACTION ID - "+transactionId);
        log.info("ORDER CONTROLLER : TRANSACTION ID - "+storeId);
        log.info("ORDER CONTROLLER : TRANSACTION ID - "+orderId);
        // 정상적인 결제 완료 되었으므로 order, payment 상태변경 결제 id 추가, product 수정 , cartItem 수정

        Order order = orderRepository.findById(Long.valueOf(orderId))
                .orElseThrow(()->new RuntimeException("존재하지 않습니다."));
// 추후 service  정리

        order.setStatus(OrderStatus.CONFIRMED);// 주문 처리
        Payment payment = order.getPayment();
        payment.setStatus(PaymentStatus.COMPLETED);
        payment.setStoreId(storeId);
        payment.setTransactionId(transactionId);
        orderRepository.save(order);
        paymentRepository.save(payment);

        // 2. 재고 업데이트
        for (OrderItem item : order.getOrderItems()) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException());
            if (product.getStockQuantity() < item.getQuantity()) {
                throw new RuntimeException("재고 부족: " + product.getTitle());
            }
            product.setOrderQuantity(product.getOrderQuantity()+item.getQuantity());
            product.setStockQuantity(product.getStockQuantity()-item.getQuantity());
            productRepository.save(product);
        }

        // 3. 장바구니 초기화
        Member member = memberService.findMemberById(dto.getId());
        cartService.clearCart(member);
        
        // 정상 처리
        return ResponseEntity.ok("결제가 완료 되었습니다!");
    }


}
