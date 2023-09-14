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

package wang.zihlu.security.controller;

import cn.org.codecrafters.simplejwt.TokenResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.zihlu.security.constant.ResponseHeaders;
import wang.zihlu.security.model.request.LoginRequest;
import wang.zihlu.security.model.vo.UserVo;
import wang.zihlu.security.service.UserService;

import java.time.Duration;

/**
 * AuthController
 *
 * @author Zihlu Wang
 * @since 14 Sept, 2023
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final TokenResolver<?> tokenResolver;

    @Autowired
    public AuthController(UserService userService, TokenResolver<?> tokenResolver) {
        this.userService = userService;
        this.tokenResolver = tokenResolver;
    }

    @PostMapping("/login")
    public ResponseEntity<UserVo> login(@RequestBody LoginRequest loginRequest) {
        var loggedUser = userService.login(loginRequest.username(), loginRequest.password());
        var loggedUserVo = new UserVo(loggedUser.getId(), loggedUser.getUsername(), loggedUser.getEmail());
        var token = tokenResolver.createToken(Duration.ofHours(3),
                loggedUserVo.username(), "Security Demo Application", loggedUserVo);

        return ResponseEntity.status(HttpStatus.OK)
                .header(ResponseHeaders.AUTH_KEY, token)
                .body(loggedUserVo);
    }

}