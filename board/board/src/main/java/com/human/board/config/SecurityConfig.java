package com.human.board.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/",
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/uploads/**",
                    "/boards",
                    "/boards/**",
                    "/download/**",
                    "/login",
                    "/join"
                ).permitAll()
                .anyRequest().permitAll()
            );

        return http.build();
    }

}
