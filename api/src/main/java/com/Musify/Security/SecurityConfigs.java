package com.Musify.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfigs {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfigs(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .cors()
                .and()
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/user/registration").permitAll() //access without authorization
                .requestMatchers("/user/get/**").hasRole("ADMIN") //just admins can access user information
                .requestMatchers("/user/inactivate/**").hasRole("ADMIN") // and just they can inactivate someone
                .requestMatchers("/user/promote/**").hasRole("ADMIN") // just admins can promote someone to admin
                .requestMatchers("/user/**").authenticated()
                .requestMatchers("/login").permitAll() //access without authorization
                .requestMatchers("/artist/**").hasRole("ADMIN")
                .requestMatchers("/playlist/get/**").hasAnyRole("ADMIN", "REGULAR")
                .requestMatchers("/playlist/**").hasRole("ADMIN")
                .requestMatchers("/songs/get/**").hasAnyRole("ADMIN", "REGULAR")
                .requestMatchers("/songs/**").hasRole("ADMIN")
                .requestMatchers("/album/get/**").hasAnyRole("ADMIN", "REGULAR")
                .requestMatchers("/album/**").hasRole("ADMIN")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){ //setting the allowed methods and origins
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200/"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Access-Control-Allow-Origin"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
