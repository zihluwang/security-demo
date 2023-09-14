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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.zihlu.security.constant.BizErrorCode;
import wang.zihlu.security.exception.ServerErrorException;
import wang.zihlu.security.mapper.UserMapper;
import wang.zihlu.security.model.proto.User;
import wang.zihlu.security.model.vo.UserVo;
import wang.zihlu.security.service.UserService;

/**
 * UserController
 *
 * @author Zihlu Wang
 * @since 14 Sept, 2023
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/me")
    public ResponseEntity<UserVo> me() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User user) {
            return ResponseEntity.ok(userMapper.transform(user));
        }
        throw new ServerErrorException(BizErrorCode.SERVER_ERROR, "Server error!");
    }

}
