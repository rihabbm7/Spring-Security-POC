package com.anywr.test.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(   prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    private  JwtAuthFilter jwtAuthfilter;
    @Autowired
    private  AuthenticationProvider authenticationProvider;



    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http)throws  Exception{
       http.cors().and() .csrf().disable()
       			.headers().frameOptions().disable()
       			.and()
                // paths that does not require any auth
               .authorizeRequests()
               .antMatchers("/api/auth/**","/h2-console/**")
               .permitAll()
               .antMatchers(HttpMethod.OPTIONS)
               .permitAll()
               // other paths that need authorization
               .anyRequest()
               .authenticated()
               .and()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               .authenticationProvider(authenticationProvider)
               .addFilterBefore(jwtAuthfilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
        
    }
    
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/api/auth","/api/register");
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD","OPTIONS");
            }
        };
    }
}
