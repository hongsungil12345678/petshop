package com.petshop1018.sungil.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
@Table(name="reviews")
public class Review extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private double rating;

    private String content;
    public static Review createReview(Product product, Member member, double rating, String content) {
        Review review = new Review();
        review.setProduct(product);
        review.setMember(member);
        review.setRating(rating);
        review.setContent(content);
        return review;
    }
}
