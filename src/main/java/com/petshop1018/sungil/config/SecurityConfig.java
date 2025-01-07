package com.petshop1018.sungil.config;

import com.petshop1018.sungil.oauth.CustomOAuth2UserService;
import com.petshop1018.sungil.oauth.CustomOAuth2AuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
@EnableGlobalMethodSecurity(prePostEnabled = true) // 설정을 추가하여 메서드 기반 권한 제어를 활성화
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomLoginSuccessHandler customLoginSuccessHandler;
    private final CustomLoginFailureHandler customLoginFailureHandler;
    private final CustomOAuth2UserService customOAuth2UserService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http
                .csrf((csrf)->csrf
                        .ignoringRequestMatchers("/submit/**","/api/**","/orders/**","/update/**")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/","/index/**","/about","/shop/**","/members/**","/cart/updateItem").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/css/**","/js/**","/img/**","/images/**","/assets/**","/assets.vendor/**","/vendor/**","/files/**","/style.css","/favicon.ico").permitAll()
                                .requestMatchers("/cart/add/**","/wishlist/add/**").authenticated() //로그인이 필요한 작업
                                .anyRequest().permitAll()
                )
                .formLogin(formLogin->
                        formLogin
                                .loginPage("/members/login")
                                .loginProcessingUrl("/members/loginProc")
                                .successHandler(customLoginSuccessHandler)// 커스텀 로그인 핸들러
                                .failureHandler(customLoginFailureHandler)
                                .permitAll()

                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/members/login")
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService))
                        .successHandler(new CustomOAuth2AuthenticationSuccessHandler())
                        .failureUrl("/members/login?error=true")
                )
                .logout(logout ->
                        logout.logoutUrl("/logout")
                                .logoutSuccessUrl("/index")
                                .invalidateHttpSession(true) // 세션 무효화
                                .deleteCookies("JSESSIONID") // 쿠키 삭제
                                .permitAll()
                );
        return http.build();
    }

@Bean
public BCryptPasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}
@Bean
public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class).authenticationProvider(authenticationProvider()).build();
}
    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider(customUserDetailsService,passwordEncoder());
    }
}
