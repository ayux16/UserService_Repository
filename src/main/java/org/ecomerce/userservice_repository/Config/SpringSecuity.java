package org.ecomerce.userservice_repository.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SpringSecuity {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
                    try {
                        requests
                                .anyRequest().permitAll() // allow signup without auth
                                .and().cors().disable()
                                .csrf().disable();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return http.build();
    }
}
