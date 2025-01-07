package com.petshop1018.sungil.service;

import com.petshop1018.sungil.domain.Member;
import com.petshop1018.sungil.domain.Product;
import com.petshop1018.sungil.domain.Review;
import com.petshop1018.sungil.repository.MemberRepository;
import com.petshop1018.sungil.repository.ProductRepository;
import com.petshop1018.sungil.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Review addReview(Long productId, Long memberId, double rating, String content) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));

        Review review = Review.createReview(product, member, rating, content);
        return reviewRepository.save(review);
    }
}
