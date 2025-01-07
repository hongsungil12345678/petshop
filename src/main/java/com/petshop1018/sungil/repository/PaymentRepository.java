package com.petshop1018.sungil.repository;

import com.petshop1018.sungil.domain.Order;
import com.petshop1018.sungil.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment findByOrder(Order order);
}
