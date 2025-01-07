package com.petshop1018.sungil.repository;

import com.petshop1018.sungil.domain.Cart;
import com.petshop1018.sungil.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByMemberId(Long memberId);
    Cart findByMember(Member member);
}
