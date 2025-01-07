package com.petshop1018.sungil.domain;

public enum OrderStatus {
    TEMPORARY, // 임시 주문 상태
    CONFIRMED,      // 결제 완료
    SHIPPED,        // 배송 중
    DELIVERED,      // 배송 완료
    CANCELLED;      // 주문 취소
}
