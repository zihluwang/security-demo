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

package wang.zihlu.security.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wang.zihlu.security.repository.UserRepository;

import java.util.Objects;

import static wang.zihlu.security.model.proto.table.UserTableDef.USER;

/**
 * FlexUserDetailsServiceImpl
 *
 * @author Zihlu Wang
 * @since 14 Sept, 2023
 */
@Service
public class FlexUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public FlexUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.selectOneByCondition(USER.USERNAME.eq(username));
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Incorrect username or password is given.");
        }
        return user;
    }
}
