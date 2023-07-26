package br.com.admin.config;

import static br.com.admin.enumeration.AuthRole.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final TokenFilter tokenFilter;

    public WebSecurityConfig(TokenFilter tokenFilter) {
        this.tokenFilter = tokenFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(t -> t.disable())
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(m -> m.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(r ->
                r.requestMatchers("/api/admin/signin").permitAll()
                        .requestMatchers("/api/partner/**").hasAnyAuthority(ADMIN.key(), MANAGER.key(), AFFILIATE.key())

        );
        return http.build();
    }

}
