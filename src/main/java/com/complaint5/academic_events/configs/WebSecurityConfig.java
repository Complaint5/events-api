package com.complaint5.academic_events.configs;

import com.complaint5.academic_events.security.JWTutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTutil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    private static final String[] MATCHERS_GET = {
        "/usuario"
    };
    private static final String[] MATCHERS_POST = {
        "/usuario"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable());

        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(this.userDetailsService)
                .passwordEncoder(this.bCryptPasswordEncoder());
        this.authenticationManager = authenticationManagerBuilder.build();

        http.authorizeHttpRequests((authorizeConfig) -> {
            authorizeConfig.requestMatchers(HttpMethod.POST, MATCHERS_POST).permitAll();
            authorizeConfig.requestMatchers(HttpMethod.GET, MATCHERS_GET).permitAll();
            authorizeConfig.requestMatchers("/logout").permitAll();
            authorizeConfig.anyRequest().authenticated();
        });
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
