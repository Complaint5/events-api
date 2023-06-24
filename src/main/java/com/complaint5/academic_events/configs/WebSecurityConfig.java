package com.complaint5.academic_events.configs;

import com.complaint5.academic_events.security.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class WebSecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JWTAuthenticationFilter jwtAuthFilter;
    
    private static final String[] MATCHERS_GET = {
        "/usuario"
    };
    private static final String[] MATCHERS_POST = {
        "/usuario",
        "/usuario/login"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable());

        http.authorizeHttpRequests((authorizeConfig) -> {
            authorizeConfig.requestMatchers(HttpMethod.POST, MATCHERS_POST).permitAll();
            authorizeConfig.requestMatchers(HttpMethod.GET, MATCHERS_GET).permitAll();
            authorizeConfig.requestMatchers("/logout").permitAll();
            authorizeConfig.anyRequest().authenticated();
        });

        http.sessionManagement((sessionManagement) -> {
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        http.authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
