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

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wang.zihlu.security.constant.BizErrorCode;
import wang.zihlu.security.exception.BadRequestException;
import wang.zihlu.security.model.proto.User;
import wang.zihlu.security.repository.UserRepository;
import wang.zihlu.security.service.UserService;

import java.util.Objects;

import static wang.zihlu.security.model.proto.table.UserTableDef.USER;

/**
 * UserServiceImpl
 *
 * @author Zihlu Wang
 * @since 13 Sept, 2023
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserRepository, User> implements UserDetailsService, UserService  {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = getOne(QueryWrapper.create()
                .select()
                .where(USER.USERNAME.eq(username)));
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Incorrect username or password is given.");
        }
        return user;
    }

}
