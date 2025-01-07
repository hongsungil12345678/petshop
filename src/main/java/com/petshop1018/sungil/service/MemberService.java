package com.petshop1018.sungil.service;

import com.petshop1018.sungil.domain.Member;
import com.petshop1018.sungil.domain.Role;
import com.petshop1018.sungil.dto.MemberDto;
import com.petshop1018.sungil.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;

    private static final String MEMBER_NOT_FOUND = "회원이 존재하지 않습니다.";
    private static final String USER_NOT_FOUND = "해당 사용자를 찾을 수 없습니다.";

    // 회원가입
    @Transactional
    public Member registerMember(MemberDto.Request memberDto) {
        //Member 엔티티로 변환
        memberDto.setPassword(encoder.encode(memberDto.getPassword()));
        Member member = memberDto.toEntity();
        // ADMIN 검사
        if(isAdminEmail(memberDto.getEmail())){
            member.setRole(Role.ADMIN);
        }
        
        // 회원 저장
        memberRepository.save(member); // 회원 저장 시 장바구니도 함께 저장됨
        return member;
    }

    // 회원 정보 조회
    @Transactional
    public Member findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(USER_NOT_FOUND));
    }

    // 회원 탈퇴
    @Transactional
    public Member deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(MEMBER_NOT_FOUND));

        memberRepository.delete(member);

        return member;
    }


    /* 회원가입 시, 유효성 검사 및 중복 체크 */
    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        /* 유효성 검사, 중복 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    /* 회원수정 (dirty checking) */
    @Transactional
    public void modify(MemberDto.Request dto) {
        Member member = memberRepository.findById(dto.toEntity().getId()).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        String encPassword = encoder.encode(dto.getPassword());
        member.modify(dto.getNickname(), encPassword);
    }
    private boolean isAdminEmail(String email) {
        return email.equalsIgnoreCase("indiahong1@gmail.com") ||
                email.endsWith("@admin.com")||
                email.equalsIgnoreCase("8431137@naver.com");
    }
}