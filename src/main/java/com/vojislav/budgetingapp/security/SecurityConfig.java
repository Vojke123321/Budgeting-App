package com.vojislav.budgetingapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//i dont worry about csfr(this is project for portfolio....)
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/js/**","/css/**").permitAll()  
		.antMatchers("/register").permitAll()
		.antMatchers("/**").hasRole("USER").and()
		.formLogin().loginPage("/login").defaultSuccessUrl("/budgets").permitAll()
		.and().logout().logoutSuccessUrl("/").permitAll();
 		
		
	}
//	
//	@Bean
//	public BCryptPasswordEncoder getEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
}
