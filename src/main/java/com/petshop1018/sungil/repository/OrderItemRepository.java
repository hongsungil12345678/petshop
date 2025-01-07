package com.petshop1018.sungil.repository;

import com.petshop1018.sungil.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
//    List<OrderItem> findOrderItemByMemberId(Long memberId);
    List<OrderItem> findAll();
//    OrderItem findOrderItemById(Long orderProductId);
//    @Query("SELECT new map(o.cartItemId as cartItemId, o.productId as productId, o.quantity as quantity, o.price as price, o.subtotal as subtotal) FROM OrderItem o")
//    List<Map<String, Object>> findAllOrderItems();
}
