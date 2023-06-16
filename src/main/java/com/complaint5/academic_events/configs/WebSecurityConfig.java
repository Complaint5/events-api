package com.complaint5.academic_events.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final String[] MATCHERS_GET = {
        "/usuario"
    };
    private static final String[] MATCHERS_POST = {
        "/usuario"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authorizeConfig) -> {
            authorizeConfig.requestMatchers(HttpMethod.POST, MATCHERS_POST).permitAll();
            authorizeConfig.requestMatchers(HttpMethod.GET, MATCHERS_GET).permitAll();
            authorizeConfig.requestMatchers("/logout").permitAll();
            authorizeConfig.anyRequest().authenticated();
        }).csrf(csrf -> csrf.disable()).cors(cors -> cors.disable()).build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
