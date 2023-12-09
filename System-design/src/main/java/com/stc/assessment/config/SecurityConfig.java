package com.stc.assessment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        return http.build();
    }

    @Autowired
    public void defaultAuthUsers(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()

                .withUser("editor@email.com")
                .password("{noop}password")
                .and()
                .withUser("viewer@email.com")
                .password("{noop}password");
    }

}
