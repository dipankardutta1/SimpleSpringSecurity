package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.CustomUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private LoginAccessDeniedHandler loginAccessDeniedHandler;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/js/**","/css/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login").defaultSuccessUrl("/dash")
			.permitAll()
			.and()
			.logout().invalidateHttpSession(true).clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout")
			.permitAll()
			.and()
			.exceptionHandling()
			.accessDeniedHandler(loginAccessDeniedHandler);
			
		
	}
	
	
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       /* auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
            .and()
                .withUser("manager").password("password").roles("MANAGER");*/
    	
    	
    	
    	auth.authenticationProvider(authProvider());
    	
    }
	
	
	@Bean
    public PasswordEncoder passwordEncoder() {
    	//return new BCryptPasswordEncoder();
    	
    	return NoOpPasswordEncoder.getInstance();
    	
    	//return new  MessageDigestPasswordEncoder("MD5");
    	
    	//return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    	
    	
    	 
    	
    	
    	
    	
    }
	
	
	@Bean
    public DaoAuthenticationProvider authProvider() {
    	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    	authenticationProvider.setUserDetailsService(customUserDetailsService);
    	authenticationProvider.setPasswordEncoder(passwordEncoder());
    	
    	return authenticationProvider;
    }
	
	
	
	
}
