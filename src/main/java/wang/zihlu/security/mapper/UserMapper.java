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

package wang.zihlu.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import wang.zihlu.security.model.proto.User;
import wang.zihlu.security.model.request.LoginRequest;
import wang.zihlu.security.model.vo.UserVo;

/**
 * UserMapper
 *
 * @author Zihlu Wang
 * @since 14 Sept, 2023
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id", source = "user.id"),
            @Mapping(target = "username", source = "user.username"),
            @Mapping(target = "email", source = "user.email"),
    })
    UserVo transform(User user);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "username", source = "loginRequest.username"),
            @Mapping(target = "password", source = "loginRequest.password"),
            @Mapping(target = "email", ignore = true),
    })
    User transform(LoginRequest loginRequest);

}
