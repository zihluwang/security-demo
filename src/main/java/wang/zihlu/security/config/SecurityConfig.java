/*
 * Copyright (c) 2023. Zihlu Wang
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see https://www.gnu.org/licenses/.
 */

package wang.zihlu.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;
import wang.zihlu.security.model.proto.User;

import java.util.function.Supplier;

/**
 * SecurityConfig
 *
 * @author Zihlu Wang
 * @since 12 Sept, 2023
 */
@Configuration
@EnableWebSecurity
@AutoConfigureAfter(CorsConfig.class)
public class SecurityConfig {

    private final ObjectMapper objectMapper;

    private final CorsConfigurationSource corsConfigurationSource;

    @Autowired
    public SecurityConfig(CorsConfigurationSource corsConfigurationSource, ObjectMapper objectMapper) {
        this.corsConfigurationSource = corsConfigurationSource;
        this.objectMapper = objectMapper;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests((customizer) -> customizer
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin((customizer) -> customizer
                        .loginProcessingUrl("/api/auth/login")
                        .successHandler(((request, response, authentication) -> {

                        }))
                        .failureHandler(((request, response, exception) -> {

                        })))
                .logout((customizer) -> customizer
                        .logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler(((request, response, authentication) -> {

                        })))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((customizer) -> customizer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors((customizer) -> customizer
                        .configurationSource(corsConfigurationSource))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
