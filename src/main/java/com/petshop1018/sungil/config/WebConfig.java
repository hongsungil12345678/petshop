package com.petshop1018.sungil.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.orm.hibernate5.support.OpenSessionInViewInterceptor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * LoginUserArgumentResolver가 인식될 수 있도록 WebMvcConfigurer에 추가
 */
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;
    /* HandlerMethodArgumentResolver는 항상 addArgumentResolvers()를 통해 추가해야 함 */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserArgumentResolver);
    }

    /**
     * Spring MVC에서 정적 리소스를 처리하기 위한 설정
     * addResourceHandlers 메서드는 정적 파일(예: 이미지, CSS, JavaScript 파일)을 서버에서 제공할 때 필요한 경로를 설정하는 역할
     * .addResourceHandler("/**") -> URL 경로 패턴 지정, 즉 모든 경로에 대해서 설정
     * .addResourceLocations("file:src/main/resources/static/")
     * ->실제 정적 파일이 저장된 위치를 지정 file: 접두어는 로컬 파일 시스템에서 리소스를 찾도록 지시
     * 즉, 이 코드에서는 src/main/resources/static/ 디렉토리 내에 있는 파일들을 외부에서 접근할 수 있도록 설정
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/**")
                .addResourceLocations("file:src/main/resources/static/");
    }

}
