package com.example.chatdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // 禁用 CSRF 保护
                .authorizeRequests()
                .antMatchers("/**").permitAll()  // 放行所有接口
                .and()
                .formLogin().disable()  // 禁用默认登录
                .httpBasic().disable()  // 禁用基本认证
                .cors().configurationSource(corsConfigurationSource());  // 配置 CORS
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Collections.singletonList("*")); // 允许任何来源
        configuration.addAllowedHeader(CorsConfiguration.ALL); // 允许任何请求头
        configuration.addAllowedMethod(CorsConfiguration.ALL); // 允许任何方法
        configuration.setAllowCredentials(true); // 允许凭证
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}