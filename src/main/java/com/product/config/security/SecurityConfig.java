package com.product.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.product.config.jwt.JwtAuthFilter;
import com.product.config.security.CorsConfig;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfig corsConfig) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/error", "/swagger-ui/**", "/v3/api-docs/**", "/actuator/info",
                                        "/actuator/health")
                                .permitAll()

                                // Category
                                .requestMatchers(HttpMethod.GET, "/category/active").hasAnyAuthority("ADMIN", "CUSTOMER")
                                .requestMatchers("/category/**").hasAuthority("ADMIN")

                                // Product
                                .requestMatchers(HttpMethod.GET, "/product/active").hasAnyAuthority("ADMIN", "CUSTOMER")
                                .requestMatchers("/product/**").hasAuthority("ADMIN")

                                // Product images
                                .requestMatchers(HttpMethod.GET, "/product-image/{product_id}").hasAnyAuthority("ADMIN", "CUSTOMER")
                                .requestMatchers("/product-image/**").hasAuthority("ADMIN"))

                .cors(cors -> cors.configurationSource(corsConfig))
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form.disable())
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}