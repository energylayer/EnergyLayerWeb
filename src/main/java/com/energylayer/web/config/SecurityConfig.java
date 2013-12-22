package com.energylayer.web.config;

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
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

/**
 * @author: rkotelnikov
 */
@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    private JdbcUserDetailsManager jdbcUserDetailsManager;

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
//                .anyRequest().authenticated();
                .anyRequest().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema()
                .passwordEncoder(passwordEncoder());
        this.jdbcUserDetailsManager = (JdbcUserDetailsManager) auth.getDefaultUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(){
        return this.jdbcUserDetailsManager;
    }

    @Bean
    public SecService secService(){
        SecServiceImpl secService = new SecServiceImpl();
        secService.setPasswordEncoder(passwordEncoder());
        secService.setUserDetailsManager(jdbcUserDetailsManager);
        return secService;
    }
}
