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
