package com.petshop1018.sungil.config;

import com.petshop1018.sungil.domain.Member;
import com.petshop1018.sungil.dto.MemberDto;
import com.petshop1018.sungil.repository.MemberRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;


// AuthenticationSuccessHandler :기본 인터페이스 SimpleUrlAuthenticationSuccessHandler 구현체
// 로그인 성공시 세션에 Member 객체 저장
// 즉 CustomAuthenticationProvider 이후 세션에 저장한다
@RequiredArgsConstructor
@Component
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final MemberRepository memberRepository;
    private final HttpSession session;  // 세션을 통해 사용자 정보를 저장
    private final CsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // 로그인한 사용자 정보를 가져와 세션에 저장
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        
        Member member = memberRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + username));

        // member 세션 등록
        session.setAttribute("member", new MemberDto.Response(member));


        // csrf 처리
        CsrfToken csrfToken = csrfTokenRepository.generateToken(request);
        csrfTokenRepository.saveToken(csrfToken, request, response);
        response.addHeader("Set-Cookie", "XSRF-TOKEN=" + csrfToken.getToken() + "; Path=/; HttpOnly=false");

        // 로그인 성공 후 특정 URL로 리다이렉트
        setDefaultTargetUrl("/index");  // 로그인 성공 후 이동할 페이지 설정
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
