package com.kuang.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//开去授权认证
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人可以访问，功能页只有有权限的人才能访问
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                //拥有vip1权限的人才可以访问 /level1 下的所有文件
                .antMatchers("/level1/**").hasRole("vip1")
                //拥有vip2权限的人才可以访问 /level2 下的所有文件
                .antMatchers("/level2/**").hasRole("vip2")
                //拥有vip3权限的人才可以访问 /level3 下的所有文件
                .antMatchers("/level3/**").hasRole("vip3");

        //没有权限默认去登录页
        http.formLogin();

        //记住我
        http.rememberMe();

        //注销
        http.logout().logoutSuccessUrl("/");
    }

    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //正常情况应该使用auth.jdbcAuthentication()，从数据库获取用户及角色
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                //给此用户分配权限 vip2 和 vip3，结合上面的授权，kuangshen这个用户只能访问level2 和 level3下的页面
                .withUser("kuangshen").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2", "vip3")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip2", "vip3")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");
    }
}
