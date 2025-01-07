package com.petshop1018.sungil.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity@Getter@Setter
@RequiredArgsConstructor
public class Address extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String companyName;
    private String country;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String orderEmail;
    private String orderNotes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")  // 주소는 여러 명의 회원이 가질 수 있으므로 ManyToOne 관계
    private Member member;
    @OneToOne(mappedBy = "address")
    private Order order; // 배송 주소와 주문 1:1 관계
    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", country='" + country + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", orderEmail='" + orderEmail + '\'' +
                ", orderNotes='" + orderNotes + '\'' +
                '}';
    }
}

