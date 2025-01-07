package com.petshop1018.sungil.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity@NoArgsConstructor@Getter@Setter
@Table(name = "payments")
public class Payment extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;  // Payment는 하나의 Order에 연결됩니다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id",nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PENDING;

    private double amount;
    private String transactionId; // 결제 API에서 제공하는 트랜잭션 ID
    private String storeId;
}