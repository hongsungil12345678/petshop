package com.petshop1018.sungil.config;

import com.petshop1018.sungil.domain.Member;
import com.petshop1018.sungil.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
// 사용자 정보를 DB에서 Username으로 조회하여 Userdetails 반환,
// 실제 검증은 CustomAuthenticationProvider에서 수행

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final HttpSession session;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다."));
        return new CustomUserDetails(member);
    }
}
