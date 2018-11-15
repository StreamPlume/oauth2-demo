package com.stream.oauth.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * Created by Stream on 2018/11/12.
 */
@Configuration
public class OAuth2Config {


//  当传递令牌时，OAuth2客户端将其接收到的OAuth2令牌转发给资源服务。
//  由于我们已经声明了@EnableOauth2Sso注解，Spring Boot 会在请求上下文中添加一个OAuth2ClientContext对象，
//  因此我们可以在客户端应用程序中创建自己的OAuth2RestTemplate。
    @Bean
    public OAuth2RestOperations restOperations(
            OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context) {
        return new OAuth2RestTemplate(resource, context);
    }
}
