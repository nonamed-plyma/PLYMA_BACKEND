package org.testboard.plyma_backend.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.testboard.plyma_backend.global.error.AuthenticationEntryPointImpl;
import org.testboard.plyma_backend.global.jwt.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationEntryPointImpl authenticationEntryPoint;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider,
                          AuthenticationEntryPointImpl authenticationEntryPoint) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin().disable()
                .cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .httpBasic().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/*").permitAll()
                        .requestMatchers("/post/search").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user").authenticated()
                        .anyRequest().authenticated()
                )
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                .apply(new FilterConfig(jwtTokenProvider));
        return http.build();
    }
}
