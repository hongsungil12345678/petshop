package com.petshop1018.sungil.validator;

import com.petshop1018.sungil.dto.CheckoutForm;
import com.petshop1018.sungil.dto.MemberDto;
import com.petshop1018.sungil.repository.AddressRepository;
import com.petshop1018.sungil.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * 중복 확인, 유효성 검증 구현 클래스
 * */
@RequiredArgsConstructor
@Component
public class CustomValidators {

    @RequiredArgsConstructor
    @Component
    public static class EmailValidator extends AbstractValidator<MemberDto.Request> {
        private final MemberRepository memberRepository;

        // 수정 -> 해당 타입만 valid 처리 하기 위해서
        @Override
        protected Class<MemberDto.Request> getSupportedClass() {
            return MemberDto.Request.class;
        }

        @Override
        protected void doValidate(MemberDto.Request dto, Errors errors) {
            if (memberRepository.existsByEmail(dto.toEntity().getEmail())) {
                errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
            }
        }
    }

    @RequiredArgsConstructor
    @Component
    public static class NicknameValidator extends AbstractValidator<MemberDto.Request> {
        private final MemberRepository memberRepository;

        @Override
        protected Class<MemberDto.Request> getSupportedClass() {
            return MemberDto.Request.class;
        }

        @Override
        protected void doValidate(MemberDto.Request dto, Errors errors) {
            if (memberRepository.existsByNickname(dto.toEntity().getNickname())) {
                errors.rejectValue("nickname", "닉네임 중복 오류", "이미 사용중인 닉네임 입니다.");
            }
        }
    }

    @RequiredArgsConstructor
    @Component
    public static class UsernameValidator extends AbstractValidator<MemberDto.Request> {
        private final MemberRepository userRepository;

        @Override
        protected Class<MemberDto.Request> getSupportedClass() {
            return MemberDto.Request.class;
        }

        @Override
        protected void doValidate(MemberDto.Request dto, Errors errors) {
            if (userRepository.existsByUsername(dto.toEntity().getUsername())) {
                errors.rejectValue("username", "아이디 중복 오류", "이미 사용중인 아이디 입니다.");
            }
        }
    }

    @RequiredArgsConstructor
    @Component
    public static class CheckoutFormValidator extends AbstractValidator<CheckoutForm> {

        @Override
        protected Class<CheckoutForm> getSupportedClass() {
            return CheckoutForm.class;  // CheckoutForm을 반환해야 함
        }

        @Override
        protected void doValidate(CheckoutForm checkoutForm, Errors errors) {
        }
    }
}