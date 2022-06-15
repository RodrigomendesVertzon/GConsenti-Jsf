package br.com.vertzon.gconsenti.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import br.com.vertzon.gconsenti.domain.model.enumerator.ProfileEnum;
import br.com.vertzon.gconsenti.security.handler.CustomAuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private static CustomAuthenticationFailureHandler authenticationFailureHandler;

	@Bean
	protected DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}

	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Configuration
	@Order(1)
	public static class FormConfigurationAdapter extends WebSecurityConfigurerAdapter {

		@Bean
		public AuthenticationEntryPoint formAuthenticationEntryPoint() {
			BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
			entryPoint.setRealmName("gconsenti");
			entryPoint.afterPropertiesSet();
			return entryPoint;
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.httpBasic()
				.authenticationEntryPoint(formAuthenticationEntryPoint())
			.and()
				.authorizeRequests()
				.antMatchers("/secure/dashboard.xhtml").hasAnyAuthority(ProfileEnum.ADMINISTRATOR.getLabel(), ProfileEnum.WEBMASTER.getLabel(), ProfileEnum.AUDITOR.getLabel())
				.antMatchers("/secure/auditor/**").hasAnyAuthority(ProfileEnum.ADMINISTRATOR.getLabel(), ProfileEnum.AUDITOR.getLabel())
				.antMatchers("/secure/admin/**").hasAuthority(ProfileEnum.ADMINISTRATOR.getLabel())
				.antMatchers("/login.xhtml", "/javax.faces.resource/**")
				.permitAll().anyRequest().authenticated()
			.and()
			      .exceptionHandling().accessDeniedPage("/secure/denied.xhtml")
			.and()
				.formLogin()
				.loginPage("/login.xhtml")
				.loginProcessingUrl("/perform_login")
				.defaultSuccessUrl("/secure/dashboard.xhtml")
                .failureHandler(authenticationFailureHandler)
				.failureUrl("/login.xhtml?error=true")
			.and()
				.logout()
				.logoutUrl("/perform_logout")
				.logoutSuccessUrl("/login.xhtml")
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
			.and()
				.cors()
			.and()
				.csrf().disable();
		}
	}

	@Configuration
	@Order(2)
	public static class ApiConfigurationAdapter extends WebSecurityConfigurerAdapter {

		public AuthenticationEntryPoint authenticationEntryPoint() {
			BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
			entryPoint.setRealmName("GConsenti API");
			entryPoint.afterPropertiesSet();
			return entryPoint;
		}

		protected void configure(HttpSecurity http) throws Exception {
			http
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.httpBasic()
				.authenticationEntryPoint(authenticationEntryPoint())
			.and()
				.authorizeRequests().antMatchers("/gconsenti/api/v1/**")
				.hasAnyAuthority(ProfileEnum.WEBMASTER.getLabel())
				.anyRequest().authenticated()
			.and()
				.csrf().disable();
		}
	}
}
