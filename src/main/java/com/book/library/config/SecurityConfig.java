package com.book.library.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.function.ServerRequest.Headers;

@Configuration
@EnableWebSecurity

public class SecurityConfig  {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf(Csrf ->Csrf.disable())
		.headers(headers ->headers.frameOptions().disable())
		.authorizeHttpRequests(auth -> auth
				.requestMatchers(
						 "/h2-console/**",
		                    "/v3/api-docs/**",
		                    "/swagger-ui/**",
		                    "/swagger-ui.html"
		                    ).permitAll()
				.anyRequest().authenticated()).httpBasic();
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder()
				.username("admin")
				.password("{noop}password")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}

}
