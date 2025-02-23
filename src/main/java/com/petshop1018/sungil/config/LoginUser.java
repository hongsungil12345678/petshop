package com.petshop1018.sungil.config;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 세션 관련 중복코드 제거하기 위해 LoginUser 어노테이션 생성
 */

@Target(ElementType.PARAMETER) // 파라미터로 선언된 객체만 사용
@Retention(RetentionPolicy.RUNTIME) //런타임까지 남아있는다.
@AuthenticationPrincipal // oauth2 로그인 세션 포함
public @interface LoginUser {
}