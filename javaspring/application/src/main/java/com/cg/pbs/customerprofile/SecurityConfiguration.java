package com.cg.pbs.customerprofile;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.sap.cloud.security.xsuaa.XsuaaServiceConfigurationDefault;
import com.sap.cloud.security.xsuaa.XsuaaServicePropertySourceFactory;
import com.sap.cloud.security.xsuaa.token.TokenAuthenticationConverter;
import com.sap.cloud.security.xsuaa.token.authentication.XsuaaJwtDecoderBuilder;

/**
 * 
 * @author gbangalo
 *
 */

@EnableWebSecurity
@PropertySource(factory = XsuaaServicePropertySourceFactory.class, value = { "" })
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	XsuaaServiceConfigurationDefault xsuaaServiceConfiguration;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.csrf().disable().cors().and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session is created by approuter
			.and()
				.authorizeRequests()
				//.antMatchers("/customerprofile").hasAuthority("openid")
				//.anyRequest().authenticated()
				.anyRequest().permitAll()	
			.and()
				.oauth2ResourceServer()
				.jwt()
				.jwtAuthenticationConverter(getJwtAuthenticationConverter());
		// @formatter:on
	}

	/**
	 * Customizes how GrantedAuthority are derived from a Jwt
	 */
	Converter<Jwt, AbstractAuthenticationToken> getJwtAuthenticationConverter() {
		TokenAuthenticationConverter converter = new TokenAuthenticationConverter(xsuaaServiceConfiguration);
		// converter.setLocalScopeAsAuthorities(true);
		return converter;
	}

	@Bean
	JwtDecoder jwtDecoder() {
		return new XsuaaJwtDecoderBuilder(xsuaaServiceConfiguration).build();
	}

	@Bean
	XsuaaServiceConfigurationDefault config() {
		return new XsuaaServiceConfigurationDefault();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
				
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "https://webidetesting4339530-p1941230732trial.dispatcher.hanatrial.ondemand.com"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","OPTIONS","PATCH","DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "content-type", "Accept", "X-Requested-With", "remember-me", "x-xhr-logon"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	
	
}
