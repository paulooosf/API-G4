package br.org.serratec.apig4.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.org.serratec.apig4.security.JwtUtil;

@Configuration
@EnableWebSecurity
public class ConfigSeguranca {

	@Autowired
	JwtUtil jwtUtil;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).cors((cors) -> cors.configurationSource(corsConfigurationSource()))
				.httpBasic(Customizer.withDefaults()).authorizeHttpRequests(requests -> {
					requests.requestMatchers(HttpMethod.GET, "/usuario").permitAll();
					requests.requestMatchers(HttpMethod.GET, "/relacionamentos").permitAll();
					requests.requestMatchers(HttpMethod.GET, "/postagem").permitAll();
					requests.requestMatchers(HttpMethod.GET, "/comentarios").permitAll().anyRequest().authenticated();
				}).sessionManagement(session -> {
					session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				});

		return http.build();
	}

	@Bean
	InMemoryUserDetailsManager userDetailService() {
		UserDetails user = User.withDefaultPasswordEncoder().username("teste").password("123456").build();
		return new InMemoryUserDetailsManager(user);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:8080/"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration.applyPermitDefaultValues());

		return source;
	}

}
