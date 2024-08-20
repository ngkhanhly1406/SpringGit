package org.example.exam.controller;

import org.example.exam.service.UserDetailServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private UserDetailServicelmpl userDetailService;

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http, HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
            .authorizeRequests(registry->{
            registry.requestMatchers("/users/list", "/register/**").permitAll();
            registry.requestMatchers("/users/showFormForAdd").hasRole("ADMIN");
            registry.requestMatchers("/users/showFormForUpdate").hasRole("ADMIN");
            registry.anyRequest().authenticated();
            })
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer.loginPage("/login")
                    .successHandler(new AuthenticationSuccessHandler())
                    .permitAll();
                })
            .logout((logout) -> logout.logoutUrl("/my/logout/uri"))
            .build();
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetails normalUser = User.builder()
                .username("Nam")
                .password("$2a$12$0gR6XWqOMy7PloRR7GGHGOfH44p4SRKPHlGu/EG1wsdD4Z9ujKwPy")
                .roles("USER")
                .build();
        UserDetails adminUser = User.builder()
                .username("Admin")
                .password("$2a$12$XBQwez5pbk8hDZOixgC0gOSXBwDe3FDuJXDubKc24Mul/.dsxKNdG")
                .roles("ADMIN", "USER")
                .build();
        return new InMemoryUserDetailsManager(normalUser, adminUser);
    }*/

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
