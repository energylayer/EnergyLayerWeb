package com.energylayer.web.config;

import com.energylayer.dao.UserDao;
import com.energylayer.service.SecService;
import com.energylayer.service.impl.SecServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @author: rkotelnikov
 */
@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDao userDao;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin()
                .loginPage("/login").failureUrl("/login?error").defaultSuccessUrl("/").permitAll()
                .and()
            .logout()
                .logoutUrl("/logout").logoutSuccessUrl("/").permitAll()
                .and()
            .authorizeRequests()
                .antMatchers("/login/**", "/data/**", "/static/**").permitAll()
                .anyRequest().authenticated();
//                .anyRequest().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(secService())
            .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecService secService(){
        SecServiceImpl secService = new SecServiceImpl();
        secService.setPasswordEncoder(passwordEncoder());
        secService.setUserDao(userDao);
        return secService;
    }
}
