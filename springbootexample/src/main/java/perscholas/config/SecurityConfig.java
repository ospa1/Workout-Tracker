package perscholas.config;

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

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/pub/**", "/error/**", "/login/**", "/search")
				.permitAll().antMatchers("/admin/**").authenticated().and().formLogin().loginPage("/login/login")
				.loginProcessingUrl("/login/login").successHandler(successHandler).failureHandler(failureHandler).and()
				.logout().invalidateHttpSession(true).logoutUrl("/login/logout").logoutSuccessUrl("/index").and()
				.rememberMe().key("SR_KEY").tokenValiditySeconds(60 * 60 * 24 * 2).rememberMeParameter("remember-me")
				.and().exceptionHandling().accessDeniedPage("/error/404");
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
