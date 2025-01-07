package com.petshop1018.sungil.oauth;

import com.petshop1018.sungil.domain.Member;
import com.petshop1018.sungil.domain.Role;
import com.petshop1018.sungil.dto.MemberDto;
import com.petshop1018.sungil.repository.MemberRepository;
import com.petshop1018.sungil.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

/**
 * Security UserDetailsService == OAuth OAuth2UserService
 * */
@Slf4j
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;
    private final HttpSession session;
    private final CartService cartService;
    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        /* OAuth2 서비스 id 구분코드 ( 구글, 카카오, 네이버 ) */
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        log.info("registrationId : " + registrationId);

        /* OAuth2 로그인 진행시 키가 되는 필드 값 (PK) (구글의 기본 코드는 "sub") */
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        log.info("============================================");
        log.info("userNameAttributeName : " + userNameAttributeName);

        /* OAuth2UserService */
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        log.info("=============================================");
        log.info("getAttributes(): " + attributes.getAttributes());

        Member member = saveOrUpdate(attributes);
        // 0auth2에도 카트 자동으로 추가
        cartService.getCartByMember(member);
        /* 세션 정보를 저장하는 직렬화된 dto 클래스 */
        session.setAttribute("member", new MemberDto.Response(member));

        // "ROLE" 접두어 붙여줘야함!!!! 여기에서 몇시간 헤맨거냐 진짜로,,
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_"+member.getRole())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }
    /* 소셜로그인시 기존 회원이 존재하면 수정날짜 정보만 업데이트해 기존의 데이터는 그대로 보존한다. */
    @Transactional
    public Member saveOrUpdate(OAuthAttributes attributes) {
        String email = attributes.getEmail();
        log.info("SAVE OR UPDATE : "+email);
        Role role = email.equals("indiahong1@gmail.com") ? Role.ADMIN : Role.SOCIAL;
        Member member = memberRepository.findByEmail(attributes.getEmail())
                .map(Member::updateModifiedDate)
                .orElse(attributes.toEntity());
        member.setRole(role);
        return memberRepository.save(member);
    }
}