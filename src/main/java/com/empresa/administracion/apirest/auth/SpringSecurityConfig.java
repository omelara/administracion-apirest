package com.empresa.administracion.apirest.auth;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableGlobalMethodSecurity(securedEnabled  = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	//1, - Inyectamos UserDetailsService
		@Autowired
		private UserDetailsService userService;
		
		//2.-sobreescribir el metodo configure de AuthenticationManagerBuilder
		@Override
		@Autowired
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(this.userService).passwordEncoder(passwordEncoder());
		}

		//3.crear el metodo passwordEncoder() y anotarlo como mun bean
		@Bean
		private static BCryptPasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
		
		//4.sobreescribir el metodo
		@Bean
		@Override
		protected AuthenticationManager authenticationManager() throws Exception {
			return super.authenticationManager();
		}
		
		public void configure(HttpSecurity http) throws Exception {
			http.authorizeHttpRequests()
			.anyRequest().authenticated()
			.and()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
		}
		
}
