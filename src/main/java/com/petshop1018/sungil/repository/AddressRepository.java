package com.petshop1018.sungil.repository;

import com.petshop1018.sungil.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    Address findByMemberId(Long memberId);
}
