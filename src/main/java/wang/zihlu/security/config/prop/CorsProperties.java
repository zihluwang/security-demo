package wang.zihlu.security.config.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * CorsProperties
 *
 * @author Zihlu Wang
 * @since 12 Sept, 2023
 */
@Data
@ConfigurationProperties("app.cors")
public class CorsProperties {

    private Boolean allowCredentials;

    private String[] allowedOrigins = {"*"};

    private String[] allowedHeaders;

    private RequestMethod[] allowedMethods = {
            RequestMethod.GET,
            RequestMethod.POST,
            RequestMethod.PUT,
            RequestMethod.PATCH,
            RequestMethod.DELETE
    };

    private String[] exposedHeaders;

}
