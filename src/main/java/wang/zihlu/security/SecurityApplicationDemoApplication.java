package wang.zihlu.security;

import cn.org.codecrafters.guid.GuidCreator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("wang.zihlu.security.repository")
@SpringBootApplication
public class SecurityApplicationDemoApplication {

    public static void main(String[] args) {
        var ctx = SpringApplication.run(SecurityApplicationDemoApplication.class, args);
        var userIdCreator = ctx.getBean("userIdCreator", GuidCreator.class);
    }

}
