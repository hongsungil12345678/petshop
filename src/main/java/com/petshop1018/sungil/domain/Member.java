package com.petshop1018.sungil.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity@NoArgsConstructor@Setter
@Getter@AllArgsConstructor@Builder
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String username;
    @Column(nullable = false, unique = true, length = 30)
    private String nickname;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // 양방향 관계 설정
    @OneToOne(mappedBy = "member",cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wishlist> wishlists = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments = new ArrayList<>();  // 한 명의 회원은 여러 결제를 가질 수 있음

    /* 소셜로그인시 이미 등록된 회원이라면 수정날짜만 업데이트해줘서
     * 기존 데이터를 보존하도록 예외처리 */
    public Member updateModifiedDate() {
        this.onPreUpdate();
        return this;
    }
    public String getRoleValue() {
        return this.role.getValue();
    }
    /* 회원정보 수정 */
    public void modify(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}
