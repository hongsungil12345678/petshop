package com.petshop1018.sungil.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PaymentMethod {
    CREDIT_CARD("creditCard"),
    DEBIT_CARD("checkPayment"),
    PAYPAL("paypal"),
    BANK_TRANSFER("bankTransfer"),
    CASH_ON_DELIVERY("cashOnDelivery");
    private final String description; // 형태

}
