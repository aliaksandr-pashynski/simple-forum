package com.pashynski.forum.config;

import com.pashynski.forum.config.KeycloakRolesConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        return http
                .cors(Customizer.withDefaults())
                .sessionManagement(c -> c.sessionCreationPolicy(STATELESS))
                .oauth2ResourceServer(
                        oauth2 ->
                                oauth2.jwt(c -> c.jwtAuthenticationConverter(customJwtConverter()))
                ).authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/v1/users/**").hasRole("FORUM_USER")
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/v1/topics/**",
                                "/api/v1/posts/**"
                        ).hasRole("FORUM_USER")
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/v1/categories/**",
                                "/api/v1/topics/**",
                                "/api/v1/posts/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                ).build();
    }

    @Bean
    public Converter<Jwt, ? extends AbstractAuthenticationToken> customJwtConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakRolesConverter());
        return converter;
    }
}