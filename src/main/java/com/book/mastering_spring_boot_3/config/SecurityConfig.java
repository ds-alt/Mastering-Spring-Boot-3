package com.book.mastering_spring_boot_3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                                                .requestMatchers("/", "/books/view", "/login").permitAll()
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/login") // укажува на login форма
                                                .defaultSuccessUrl("/", true) // каде оди после успешен login
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login?logout")
                                                .permitAll())
                                .csrf(csrf -> csrf.disable()); // Исклучено поради API повици

                return http.build();
        }

        @Bean
        public UserDetailsService users() {
                UserDetails user = User.withUsername("user")
                                .password("{noop}password") // {noop} значи без енкрипција
                                .roles("USER")
                                .build();

                UserDetails admin = User.withUsername("admin")
                                .password("{noop}admin")
                                .roles("ADMIN")
                                .build();

                return new InMemoryUserDetailsManager(user, admin);

        }

}
