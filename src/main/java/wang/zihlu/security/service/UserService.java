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

package wang.zihlu.security.service;

import com.mybatisflex.core.service.IService;
import org.springframework.stereotype.Service;
import wang.zihlu.security.model.proto.User;

/**
 * UserService
 *
 * @author Zihlu Wang
 * @since 13 Sept, 2023
 */
@Service
public interface UserService extends IService<User> {

    User login(String username, String password);

}
