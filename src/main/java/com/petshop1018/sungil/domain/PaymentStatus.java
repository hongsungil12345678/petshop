package com.petshop1018.sungil.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PaymentStatus {
    PENDING("Pending"),   // 결제 대기 중
    COMPLETED("Completed"), // 결제 완료
    FAILED("Failed"),     // 결제 실패
    CANCELED("Canceled"); // 결제 취소

    private final String description;

}
