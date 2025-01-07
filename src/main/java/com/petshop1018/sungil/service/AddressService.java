package com.petshop1018.sungil.service;

import com.petshop1018.sungil.domain.Address;
import com.petshop1018.sungil.domain.Member;
import com.petshop1018.sungil.dto.CheckoutForm;
import com.petshop1018.sungil.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    public Address processCheckout(CheckoutForm checkoutForm,Member member) {
        Address address = convertToEntity(checkoutForm.getAddress(),member);
        // address 엔티티를 저장
        return addressRepository.save(address);
    }

    public Address convertToEntity(CheckoutForm.Address checkoutAddress, Member member) {
        Address address = new Address();
        address.setFirstName(checkoutAddress.getFirstName());
        address.setLastName(checkoutAddress.getLastName());
        address.setCompanyName(checkoutAddress.getCompanyName());
        address.setCountry(checkoutAddress.getCountry());
        address.setAddress1(checkoutAddress.getAddress1());
        address.setAddress2(checkoutAddress.getAddress2());
        address.setCity(checkoutAddress.getCity());
        address.setState(checkoutAddress.getState());
        address.setZip(checkoutAddress.getZip());
        address.setPhone(checkoutAddress.getPhone());
        address.setOrderEmail(checkoutAddress.getOrderEmail());
        address.setOrderNotes(checkoutAddress.getOrderNotes());
        address.setMember(member);
        return address;
    }
}
