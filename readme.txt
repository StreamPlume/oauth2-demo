
#完整的授权服务，资源服务器，客户端 例子，供入门使用练习

#1.基于spring cloud最新版Greenwich.M1
#2.解决遇到的几个坑，并中文注释
    ##a. Spring Security 5.0.x 以后要使用加密算法的。
      提供两种解决方法，{ID}encodedPassword
        定义创建
              @Bean
              public PasswordEncoder passwordEncoder() {
                  return new BCryptPasswordEncoder();
              }
    ##b. 资源服务器需要授权服务器授权，需要重写方法
             public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
                 security
                         .tokenKeyAccess("permitAll()")
                         .checkTokenAccess("isAuthenticated()")
                         .allowFormAuthenticationForClients();//允许表单登录

             }
    ##c.当用一台电脑运行三个服务进行测试时，由于都是 localhost域名，导致Cookie冲突，自定义 server.servlet.session.cookie.name 属性
        可以每个服务都自定义一下。至少要重定义 授权服务器的。否则客户端授权会有问题。

#3.自测无问题上传


参考资料：

https://blog.csdn.net/peterwanghao/article/details/81135925
https://blog.csdn.net/adsadadaddadasda/article/details/81476653