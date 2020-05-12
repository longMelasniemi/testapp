package com.example.Note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.Note.domain.impl.UserDetailServiceImpl;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
		@Autowired
		private UserDetailServiceImpl userDetailsService;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
			.antMatchers("/css/**","/register","/saveuser","/image/**").permitAll().and()
			.authorizeRequests().antMatchers("/userlist").hasAuthority("ADMIN").and()
			.authorizeRequests().anyRequest().authenticated().and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/notelist")
			.permitAll().and()
			.logout()
			.logoutSuccessUrl("/login")
			.invalidateHttpSession(true)
			.permitAll()
			;
		}
		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		}
}

