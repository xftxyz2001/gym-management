package com.xftxyz.gymadmin.config;

import com.xftxyz.gymadmin.interceptor.AuthInterceptor;
import com.xftxyz.gymadmin.interceptor.LoggingInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 */
@Configuration
@RequiredArgsConstructor
@Import({AuthInterceptor.class, LoggingInterceptor.class})
public class InterceptorConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;
    private final LoggingInterceptor loggingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor).addPathPatterns("/**");

        // 排除登录接口/login、swagger接口
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/admin/login", "/swagger-ui/**", "/v3/api-docs/**");

    }
}