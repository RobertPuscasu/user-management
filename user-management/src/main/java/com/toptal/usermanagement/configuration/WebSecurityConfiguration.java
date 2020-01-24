package com.toptal.usermanagement.configuration;

import java.net.URI;

import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;

import com.toptal.usermanagement.jwt.JwtAuthenticationManager;
import com.toptal.usermanagement.jwt.JwtHeadersExchangeMatcher;
import com.toptal.usermanagement.jwt.TokenAuthenticationConverter;
import com.toptal.usermanagement.jwt.TokenProvider;
import com.toptal.usermanagement.jwt.UnauthorizedAuthenticationEntryPoint;
import com.toptal.usermanagement.model.Role;
import lombok.RequiredArgsConstructor;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {


    private static final String[] AUTH_WHITELIST = {
            "/authorize/**"
    };


    private final ReactiveUserDetailsService reactiveUserDetailsService;
    private final TokenProvider tokenProvider;



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder();
    }

/*    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .csrf().disable()
                .authorizeExchange()
                .matchers(PathRequest.toStaticResources().atCommonLocations())
                .permitAll()
                .matchers(EndpointRequest.to("health"))
                .permitAll()
                .matchers(EndpointRequest.to("info"))
                .permitAll()
                .matchers(EndpointRequest.toAnyEndpoint())
                .hasRole(Role.ADMIN.name())
                .pathMatchers("/users/**")
                .permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler())
                .and()
                .build();
    }*/

    @Bean
    public ServerLogoutSuccessHandler logoutSuccessHandler() {
        RedirectServerLogoutSuccessHandler logoutSuccessHandler =
                new RedirectServerLogoutSuccessHandler();
        logoutSuccessHandler.setLogoutSuccessUrl(URI.create("/"));
        return logoutSuccessHandler;
    }


    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, UnauthorizedAuthenticationEntryPoint entryPoint) {

        http.httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .logout().disable();

        http
                .exceptionHandling()
                .authenticationEntryPoint(entryPoint)
                .and()
                .authorizeExchange()
                .matchers(EndpointRequest.to("health", "info"))
                .permitAll()
                .and()
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS)
                .permitAll()
                .and()
                .authorizeExchange()
                .matchers(EndpointRequest.toAnyEndpoint())
                .hasRole(Role.ADMIN.name())
                .pathMatchers("/users/**")
                .permitAll()
                .and()
                .authorizeExchange()
                .matchers(EndpointRequest.toAnyEndpoint())
                .hasAuthority(Role.ADMIN.name())
                .and()
                .addFilterAt(webFilter(), SecurityWebFiltersOrder.AUTHORIZATION)
                .authorizeExchange()
                .pathMatchers(AUTH_WHITELIST).permitAll()
                .anyExchange().authenticated();
        return http.build();
    }

    @Bean
    public AuthenticationWebFilter webFilter() {
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(repositoryReactiveAuthenticationManager());
        authenticationWebFilter.setServerAuthenticationConverter(new TokenAuthenticationConverter(tokenProvider));
        authenticationWebFilter.setRequiresAuthenticationMatcher(new JwtHeadersExchangeMatcher());
        authenticationWebFilter.setSecurityContextRepository(new WebSessionServerSecurityContextRepository());
        return authenticationWebFilter;
    }

    @Bean
    public JwtAuthenticationManager repositoryReactiveAuthenticationManager() {
        return new JwtAuthenticationManager(reactiveUserDetailsService, passwordEncoder());
    }
}
