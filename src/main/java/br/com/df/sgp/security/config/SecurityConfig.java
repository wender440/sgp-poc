package br.com.df.sgp.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.df.sgp.security.auth.UserAuthenticationProvider;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages={"br.com.df.sgp.security.*"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserAuthenticationProvider userAuthenticationProvider;
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(userAuthenticationProvider);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**")
        .antMatchers("/javax.faces.resource/**");
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().cacheControl();
		http.exceptionHandling().accessDeniedPage("/pages/errors/403.xhtml")
			.and()			
			.authorizeRequests()
			.antMatchers("/pages/login.xhtml").permitAll()			
			.antMatchers("/pages/coordenador/**").hasAnyRole("ADMINISTRADOR","COORDENADOR")	
			.anyRequest().authenticated()
			.and()
			.logout()
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/pages/login.xhtml?logout")
			.permitAll()
			.and()
			.formLogin().loginPage("/pages/login.xhtml")
			.defaultSuccessUrl("/pages/index.xhtml")
			.failureUrl("/pages/login.xhtml?erro")
			.permitAll();
	}
}