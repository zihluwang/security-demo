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
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package wang.zihlu.security.filter;

import cn.org.codecrafters.simplejwt.TokenResolver;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import wang.zihlu.security.constant.CommonHeaders;
import wang.zihlu.security.model.vo.UserVo;

import javax.util.Strings;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/**
 * WebTokenAuthenticationFilter
 *
 * @author Zihlu Wang
 * @since 14 Sept, 2023
 */
@Component
public class WebTokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenResolver<?> tokenResolver;

    @Autowired
    public WebTokenAuthenticationFilter(TokenResolver<?> tokenResolver) {
        this.tokenResolver = tokenResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = request.getHeader(CommonHeaders.AUTH_KEY);
        if (!Strings.hasText(token)) {
            filterChain.doFilter(request, response);
        }

        filterChain.doFilter(request, response);
    }

}
