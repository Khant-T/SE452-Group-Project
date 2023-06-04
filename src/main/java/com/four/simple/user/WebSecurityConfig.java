package com.four.simple.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        /*http.cors().and().csrf().disable().authorizeHttpRequests()
                .requestMatchers("/api/auth/signup").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/test/**").permitAll()
                .requestMatchers("/tasks/**").hasRole("USER")
                .requestMatchers("/subtasks/**").permitAll()
                .requestMatchers("/statuses/**").permitAll()
                .requestMatchers("/workspaces/**").permitAll()
                .requestMatchers("/api/workspaces").permitAll()
                .requestMatchers("/swagger-ui/**").hasRole("ADMIN")
                .requestMatchers("/h2-console").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .and().formLogin();*/

        http.cors().and().csrf().disable().authorizeHttpRequests()
                .requestMatchers("/swagger-ui/**").hasRole("ADMIN")
                .requestMatchers("/workspaces/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/tasks/**").permitAll()
                .requestMatchers("/subtasks/**").permitAll()
                .requestMatchers("/statuses/**").permitAll()
                .requestMatchers("/api/workspaces").permitAll()
                .requestMatchers("/api/tasks").permitAll()
                .requestMatchers("/api/subtasks").permitAll()
                .requestMatchers("/api/statuses").permitAll()
                .requestMatchers("/api/auth/signup").permitAll()
                .anyRequest().permitAll()
                .and().formLogin();

        http.headers().frameOptions().sameOrigin();

        http.authenticationProvider(authenticationProvider());
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider= new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
