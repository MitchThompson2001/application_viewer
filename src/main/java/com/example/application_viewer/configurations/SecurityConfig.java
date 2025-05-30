package com.example.application_viewer.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.application_viewer.services.UserService;

@Configuration
public class SecurityConfig {

    @Autowired private UserService userService;
    @Autowired private PasswordEncoder passwordEncoder;

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     http
    //         .authorizeHttpRequests(authz -> authz
    //             .requestMatchers("/login", "/add_user", "/save_user", "/css/**", "/js/**").permitAll()
    //             .anyRequest().authenticated()
    //         )
    //         .formLogin(form -> form
    //             .loginPage("/login")
    //             .defaultSuccessUrl("/home", true)
    //             .permitAll()
    //         )
    //         .logout(logout -> logout
    //             .logoutSuccessUrl("/login?logout")
    //             .permitAll()
    //         );

    //     return http.build();
    // }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/login", "/add_user", "/save_user", "/css/**", "/js/**", "/test-auth").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
            );
    
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authBuilder =
        http.getSharedObject(AuthenticationManagerBuilder.class);

        authBuilder.userDetailsService(userService)
               .passwordEncoder(passwordEncoder);

        return authBuilder.build();
    }
}

