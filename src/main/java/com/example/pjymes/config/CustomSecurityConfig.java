package com.example.pjymes.config;

import com.example.pjymes.security.CustomUserDetailsService;
import com.example.pjymes.security.filter.TokenCheckFilter;
import com.example.pjymes.security.handler.Custom403Handler;
import com.example.pjymes.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Log4j2
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class CustomSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JWTUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("------------------configure------------------");

//        //AuthenticationManager 설정
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//
//        authenticationManagerBuilder
//                .userDetailsService(customUserDetailsService)
//                .passwordEncoder(passwordEncoder());
//
//        // Get AuthenticationManager
//        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
//
//        //반드시필요
//        http.authenticationManager(authenticationManager);
//
//        LoginFilter loginFilter = new LoginFilter("/login1");
//        loginFilter.setAuthenticationManager(authenticationManager);
//
//        LoginSuccessHandler successHandler = new LoginSuccessHandler(jwtUtil);
//        loginFilter.setAuthenticationSuccessHandler(successHandler);
//
//        // LoginFilter의 위치조정
//        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);
//        // 모든 경로는 TokenCheckFilter 동작
//        http.addFilterBefore(
//                tokenCheckFilter(jwtUtil, customUserDetailsService),
//                UsernamePasswordAuthenticationFilter.class
//        );
//
//        // refreshToken 호출처리
//        http.addFilterBefore(
//                new RefreshTokenFilter("/refreshToken", jwtUtil),
//                TokenCheckFilter.class);
        http
                .csrf(AbstractHttpConfigurer::disable) // csrf 기능 막음
                .cors(httpSecurityCorsConfigurer -> {
                    httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
                })
//                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //세션 사용 안함
                .authorizeHttpRequests((request) ->
                        request
                                .requestMatchers("/login","/health").permitAll()
                                .requestMatchers("/adminMenu/**").hasRole("ADMIN")
                                .requestMatchers("/systemManage/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/main", true)
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true))
                .exceptionHandling((exceptionConfig) ->
                        exceptionConfig
                                .accessDeniedHandler(accessDeniedHandler()) // 403 예외처리
                );
//                .cors(httpSecurityCorsConfigurer -> {
//                    httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
//                });


        return http.build();
    }

    private TokenCheckFilter tokenCheckFilter(JWTUtil jwtUtil, CustomUserDetailsService customUserDetailsService) {
        return new TokenCheckFilter(customUserDetailsService, jwtUtil);
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new Custom403Handler();
    };

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        log.info("------------------web configure------------------");
        return (web) -> web.ignoring()
//                .requestMatchers("/img/**", "/css/**", "/js/**");
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "CacheControl", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
