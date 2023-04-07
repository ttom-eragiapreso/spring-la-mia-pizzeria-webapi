package org.learning.lamiapizzeria.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new DatabaseUserDetailsService();
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println(passwordEncoder().encode("ciccio"));
        http.authorizeHttpRequests()
                .requestMatchers("/pizza", "/pizza/*", "/", "/webjars/**").hasAnyAuthority("ADMIN", "USER")
                /*  .requestMatchers("/pizza/create", "/pizza/create/**").hasAuthority("ADMIN")
                  .requestMatchers("/pizza/edit/**", "/pizza/delete/**").hasAuthority("ADMIN")
                  .requestMatchers("/ingredients/**", "/ingredients").hasAuthority("ADMIN")*/
                .requestMatchers("/**").hasAuthority("ADMIN")
/*
                .requestMatchers("/special-offers/**").hasAuthority("ADMIN")
*/
                .and().formLogin()
                .and().logout()
                .and().exceptionHandling();
        return http.build();
    }
}
