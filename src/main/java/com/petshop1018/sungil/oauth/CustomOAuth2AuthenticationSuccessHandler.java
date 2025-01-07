package com.petshop1018.sungil.oauth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import java.io.IOException;

public class CustomOAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final CsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // CSRF 토큰 생성
        CsrfToken csrfToken = csrfTokenRepository.generateToken(request);
        csrfTokenRepository.saveToken(csrfToken, request, response);
        // CSRF 토큰을 클라이언트에 전달
        response.addHeader("Set-Cookie", "XSRF-TOKEN=" + csrfToken.getToken() + "; Path=/; HttpOnly=false");

        // 리다이렉션 처리
        response.sendRedirect("/index");
    }
}
