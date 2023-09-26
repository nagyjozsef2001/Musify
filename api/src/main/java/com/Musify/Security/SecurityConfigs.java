package com.Musify.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigs {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
                .requestMatchers("/user/registration").permitAll() //access without authorization
                .requestMatchers("/user/get/**").hasRole("ADMIN") //just admins can access user information
                .requestMatchers("/user/inactivate/**").hasRole("ADMIN") // and just they can inactivate someone
                .requestMatchers("/user/promote/**").hasRole("ADMIN") // just admins can promote someone to admin
                .requestMatchers("/user/**").authenticated()
                .requestMatchers("/login").permitAll() //access without authorization
                .and()
                .csrf().disable()
                .cors().and().httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
