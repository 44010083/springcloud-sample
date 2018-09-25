package com.example.demo;

import com.example.demo.filter.MyFilter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@RestController
@EnableWebSecurity
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public MyFilter tokenFilter() {
        return new MyFilter();
    }

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping("/gitlab")
    public String gitlab() {
        String url = "http://47.106.114.41/api/v4/user?code=2e55079efe9ca5981d0158aad2d4441aed6a6a8c2ecb569ddbfdff5d5899d703";
        String result = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Component
    @EnableOAuth2Sso // 实现基于OAuth2的单点登录，建议跟踪进代码阅读以下该注解的注释，很有用
    public static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.
                    antMatcher("/**")
                    // 所有请求都得经过认证和授权
                    .authorizeRequests().anyRequest().authenticated()
                    .and().authorizeRequests().antMatchers("/", "/anon").permitAll()
                    .and()
                    // 这里之所以要禁用csrf，是为了方便。
                    // 否则，退出链接必须要发送一个post请求，请求还得带csrf token
                    // 那样我还得写一个界面，发送post请求
                    .csrf().disable()
                    // 退出的URL是/logout
                    .logout().permitAll()
                    // 退出成功后，跳转到/路径。
                    .logoutSuccessUrl("/");
        }
    }


}
