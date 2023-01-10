package com.alihmzyv.springsecuritybasics3.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:security.properties")
public class SecurityConfig {
    private Environment env;

    public SecurityConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                .requestMatchers("/user").authenticated()
                .requestMatchers("/user/**").authenticated()
                //.requestMatchers(GET, "/welcome") // method type can be provided
                //.anyRequest().denyAll()// Note that the matchers are considered in order.
                //thus will not override the matchers above, only deny the rest of all
                .anyRequest().permitAll()
                .and().formLogin()
                .and().httpBasic()
                .and().build();
    }

    @Bean
    public UserDetailsService jdbcUsers() {
        return new JdbcUserDetailsManager(securityDataSource());
    }

    @Bean("spring.security.datasource")
    public DataSource securityDataSource() {
        return DataSourceBuilder.create()
                .driverClassName(env.getProperty("driver_class_name"))
                .url(env.getProperty("url"))
                .username(env.getProperty("db_username"))
                .password(env.getProperty("password"))
                .build();
    }

    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); //again not deprecated, just to point out that it is insecure
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //again not deprecated, just to point out that it is insecure
    }
}
