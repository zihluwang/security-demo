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

package wang.zihlu.security.config;

import cn.org.codecrafters.guid.GuidCreator;
import cn.org.codecrafters.guid.SnowflakeGuidCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * GuidConfig
 *
 * @author Zihlu Wang
 * @since 14 Sept, 2023
 */
@Configuration
public class GuidConfig {

    @Bean(name = "userIdCreator")
    public GuidCreator<Long> userIdCreator() {
        return new SnowflakeGuidCreator(0x0, 0x0);
    }

}
