package com.stream.oauth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;


/**
 * Created by Stream on 2018/10/31.
 */

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig  extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;


    // 在 Spring Security 5.0.x 以后，密码的一般格式为：{ID}encodedPassword ，
    // ID 主要用于查找 PasswordEncoder 对应的编码标识符，并且encodedPassword 是所选的原始编码密码 PasswordEncoder
    //升级2.0 以后，注意此问题。 There is no PasswordEncoder mapped for the id "null"
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
//                .withClient("client").secret("123456")
//                .withClient("client").secret("{noop}123456")
                .withClient("client").secret(passwordEncoder.encode("123456"))
                .authorizedGrantTypes("authorization_code","password", "refresh_token")
                .scopes("app")
//                .redirectUris("https://cn.bing.com")
//                .autoApprove(true); //登录后绕过批准询问(/oauth/confirm_access)
        ;
    }

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    // 没有设置  authenticationManager，则不支持  authorizedGrantType 为 password 的授权
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
        ;
    }

    //访问资源端时，进行 access_token 校验，需要用到这个，否则无法鉴权
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();//允许表单登录

    }
}
