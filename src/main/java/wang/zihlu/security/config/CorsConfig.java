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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import wang.zihlu.security.config.prop.CorsProperties;

import java.util.Arrays;
import java.util.Optional;

/**
 * CorsConfig
 *
 * @author Zihlu Wang
 * @since 12 Sept, 2023
 */
@Configuration
@EnableConfigurationProperties({CorsProperties.class})
public class CorsConfig {

    private final CorsProperties corsProperties;

    @Autowired
    public CorsConfig(CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.stream(corsProperties.getAllowedOrigins()).toList());
        configuration.setAllowedMethods(Arrays.stream(corsProperties.getAllowedMethods()).map(RequestMethod::name).toList());
        configuration.setAllowedHeaders(Arrays.stream(corsProperties.getAllowedHeaders()).toList());
        configuration.setExposedHeaders(Arrays.stream(corsProperties.getExposedHeaders()).toList());

        Optional.of(corsProperties)
                .map(CorsProperties::getAllowCredentials)
                .ifPresent(configuration::setAllowCredentials);

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
