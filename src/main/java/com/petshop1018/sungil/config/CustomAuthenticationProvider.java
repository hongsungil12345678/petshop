package com.petshop1018.sungil.config;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
/**
 * CustomUserDetailsService -> 사용자 정보 조회 Username 기반 DB에서 조회하여 UserDetails 형태 반환
 * CustomAuthenticationProvider -> 실제 인증 과정, 사용자 이름,비밀번호 입력받아
 * CustomUserDetailsService에서 Userdetails와 비밀번호 검증을 포함한 전체 로직 처리
 * */


// AuthenticationProvider 인터페이스를 이용해 커스텀 인증 로직 처리 구현
// 1. authenticate(Authentication authentication): 사용자의 자격 증명을 검증하는 로직을 구현하는 메서드입니다.
// 2. supports(Class<?> authentication): 특정 Authentication 타입을 이 AuthenticationProvider가 처리할 수 있는지 여부를 결정합니다.
@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 사용자 입력 정보 가져오기
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        // DB에서 사용자 정보 가져오기
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        // 비밀번호 검증
        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username or password"); // 비밀번호가 틀리면 예외 던짐
        }

        // 사용자가 가진 권한 리스트를 가져옴 (예: ROLE_USER, ROLE_ADMIN 등)
        List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());

        // CustomAuthenticationProvider에서 사용자 인증이 성공하면,
        // UsernamePasswordAuthenticationToken을 반환하면서 인증된 사용자 정보가 SecurityContext에 저장
        // 이후 Spring Security가 CustomAuthenticationSuccessHandler로 흐름을 넘겨,
        // 로그인 성공 이후 동작을 정의
        return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 이 AuthenticationProvider가 처리할 수 있는 인증 객체의 타입을 지정 (주로 UsernamePasswordAuthenticationToken)
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
