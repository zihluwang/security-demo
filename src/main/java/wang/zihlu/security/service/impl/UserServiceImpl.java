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

package wang.zihlu.security.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import wang.zihlu.security.constant.BizErrorCode;
import wang.zihlu.security.exception.IncorrectCredentialException;
import wang.zihlu.security.mapper.UserMapper;
import wang.zihlu.security.model.proto.User;
import wang.zihlu.security.repository.UserRepository;
import wang.zihlu.security.service.UserService;

import java.util.Arrays;

/**
 * UserServiceImpl
 *
 * @author Zihlu Wang
 * @since 13 Sept, 2023
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserRepository, User> implements UserService {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User login(String username, String password) {
        try {
            var authentication = authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken.unauthenticated(username, password));

            if (authentication.isAuthenticated()) {
                if (authentication.getPrincipal() instanceof User user) {
                    return user;
                }
            } else {
                throw new IncorrectCredentialException(BizErrorCode.INCORRECT_USERNAME_OR_PASSWORD, "Incorrect username or password.");
            }
        } catch (AuthenticationException exception) {
            log.error("{}: {}", exception.getClass().getCanonicalName(), exception.getMessage());

            var iterator = Arrays.stream(exception.getStackTrace()).iterator();
            var count = 0;
            while (iterator.hasNext() && count < 10) {
                log.error("-------- at [{}] {}", count, iterator.next().toString());
                ++count;
            }
        }

        throw new IncorrectCredentialException(BizErrorCode.INCORRECT_USERNAME_OR_PASSWORD, "Incorrect username or password.");
    }

}
