package com.petshop1018.sungil.service;

import com.petshop1018.sungil.domain.*;
import com.petshop1018.sungil.repository.OrderRepository;
import com.petshop1018.sungil.repository.PaymentRepository;
import com.petshop1018.sungil.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final PaymentRepository paymentRepository;
    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final ProductService productService;
//    @Transactional
//    public Order createOrder(Member member) {
//        // 1. 장바구니 아이템 가져오기
//        List<CartItem> cartItems = cartService.getCartItems(member);
//
//        if (cartItems.isEmpty()) {
//            throw new IllegalArgumentException("장바구니에 상품이 없습니다.");
//        }
//
//        // 2. 주문 생성
//        Order order = new Order();
//        order.setMember(member);
//
//        for (CartItem cartItem : cartItems) {
//            OrderItem orderItem = new OrderItem();
//            orderItem.setOrder(order);
//            orderItem.setProduct(cartItem.getProduct());
//            orderItem.setQuantity(cartItem.getQuantity());
//            orderItem.setPrice(cartItem.getProduct().getPrice()); // 주문 당시 가격 설정
//            orderItem.setTotalPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
//            order.getOrderItems().add(orderItem);
//        }
//
//        // 3. 주문 저장
//        orderRepository.save(order);
////
////        // 4. 장바구니 비우기
////        cartService.clearCart(member);
//
//        return order;
//    }

    // 주문 , 결제 임시 생성
    @Transactional
    public Order createTempOrder(Address address,Member member,List<CartItem> cartItems,String paymentMethod, double totalAmount) {
        // 주문 생성
        Order order = new Order(member,address);
        order.setStatus(OrderStatus.TEMPORARY);//default
        order.setOrderTotalPrice(totalAmount);
        log.info("ORDER SERVICE - PAYMENTMETHOD : "+paymentMethod);
        log.info("ORDER SERVICE - is equals : "+paymentMethod.equals(PaymentMethod.CREDIT_CARD));
        log.info("TOTAL AMOUNT : "+totalAmount);
        // 결제 생성
        Payment payment = new Payment();
        payment.setMember(member);
        payment.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setAmount(totalAmount);

        // OrderItems 추가
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = OrderItem.createOrderItem(cartItem.getProduct(), cartItem.getQuantity());
            order.addOrderItem(orderItem);
        }
        Order savedOrder = orderRepository.save(order);
        payment.setOrder(savedOrder);// payment - order 연결
        paymentRepository.save(payment);
        return savedOrder;
    }

    // 주문 내역을 조회하는 메서드 (주문 상태가 COMPLETED인 것만) - 전부 조회
    public List<Order> getCompletedOrdersByMember(Long memberId) {
        // OrderStatus가 COMPLETED인 주문만 조회
        return orderRepository.findByMemberIdAndStatus(memberId, OrderStatus.CONFIRMED);
    }

    // 주문 단건 조회 결제 성공 페이지
    public Order  completedOrderPayment(Long memberId){
        return orderRepository.findFirstByMemberIdAndStatusOrderByModifiedDateDesc(memberId,OrderStatus.CONFIRMED);
    }
}

//    private final OrderRepository orderRepository;
//    private final OrderItemRepository orderItemRepository;
//    // 장바구니의 아이템들을 주문으로 변환
//    @Transactional
//    public Order createOrder(Member member, List<CartItem> cartItems) {
//        // 주문 생성
//        Order order = new Order();
//        order.setMember(member);
//
//        // 장바구니의 각 아이템을 주문 아이템으로 변환하여 주문에 추가
//        for (CartItem cartItem : cartItems) {
//            OrderItem orderItem = OrderItem.createOrderItem(cartItem.getProduct(),cartItem.getQuantity()); // CartItem을 OrderItem으로 변환
//            order.addOrderItem(orderItem); // 주문에 아이템 추가
//            orderItemRepository.save(orderItem); // 아이템 저장
//        }
//
//        // 주문 저장
//        orderRepository.save(order);
//        return order;
//    }
//    // 회원의 주문 내역 조회
//    public List<Order> getOrdersByMember(Long memberId) {
//        return orderRepository.findOrdersByMemberId(memberId);
//    }
//
//    // 주문 상세 정보 조회
//    public Order getOrderDetails(Long orderId) {
//        return orderRepository.findById(orderId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid Order ID"));
//    }
//
//    // 주문 아이템 업데이트
//    @Transactional
//    public void updateOrderItem(Long orderItemId, int quantity) {
//        OrderItem orderItem = orderItemRepository.findById(orderItemId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid OrderItem ID"));
//
//        orderItem.setQuantity(quantity);
//        orderItem.setPrice(orderItem.getPrice());
//        orderItem.setTotalPrice(orderItem.getPrice()*quantity);
//        orderItemRepository.save(orderItem);
//    }