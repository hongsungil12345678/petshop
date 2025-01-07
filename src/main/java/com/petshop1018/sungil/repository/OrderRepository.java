package com.petshop1018.sungil.repository;

import com.petshop1018.sungil.domain.Member;
import com.petshop1018.sungil.domain.Order;
import com.petshop1018.sungil.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    // 결제 성공 여러건
    List<Order> findByMemberIdAndStatus(Long memberId, OrderStatus orderStatus);
    // 단건 조회 수정 날짜 기준으로 하나만.
    Order findFirstByMemberIdAndStatusOrderByModifiedDateDesc(Long memberId, OrderStatus orderStatus);
}
