package perscholas.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import perscholas.security.AuthenticationFailureHandlerImpl;
import perscholas.security.AuthenticationSuccessHandlerImpl;
import perscholas.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationSuccessHandlerImpl successHandler;

	@Autowired
	private AuthenticationFailureHandlerImpl failureHandler;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	public final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		
		LOG.debug("in configure");

		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/pub/**", "/error/**", "/login/**", "/search").permitAll()
				.antMatchers("/admin/**", "/mainpage").authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.successHandler(successHandler)
				.failureHandler(failureHandler)
				.and()
			.logout()
				.invalidateHttpSession(true)
				.logoutUrl("/logout")
				.logoutSuccessUrl("/index")
				.and()
			.rememberMe()
				.key("SR_KEY")
				.tokenValiditySeconds(60 * 60 * 24 * 2)
				.rememberMeParameter("remember-me")
				.and()
				.exceptionHandling()
				.accessDeniedPage("/error/404");
	}

	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(getPasswordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(getAuthenticationProvider());
	}

	@Bean(name = "passwordEncoder")
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
