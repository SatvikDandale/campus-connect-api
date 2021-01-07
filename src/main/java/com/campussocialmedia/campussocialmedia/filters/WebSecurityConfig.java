package com.campussocialmedia.campussocialmedia.filters;

import java.util.Arrays;

import com.campussocialmedia.campussocialmedia.service.MyCommitteeDetailsService;
// import com.campussocialmedia.campussocialmedia.service.MyCommitteeDetailsService;
import com.campussocialmedia.campussocialmedia.service.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/*
This class is responsible for the configuration of spring security.
Here we have made the following configurations:
1: Configured Spring Security to use our own version of UserDetailsService
2: Specified the password encoder to be used
3: Removed the "/login" and "/signUp" endpoints from JWT Security
*/

@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	@Qualifier("User")
	private UserDetailsService myUserDetailsService;

	// @Autowired
	// @Qualifier("Committee")
	// private UserDetailsService committeeDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	// UserDetailsService myUserDetailsService = new MyUserDetailsService();
	// UserDetailsService committeeUserDetailsService = new
	// MyCommitteeDetailsService();
	/*
	 * This method is used to tell spring security to use our own version of
	 * UserDetailsService instead of the default UserDetailsService provided by
	 * Spring Security
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
		// auth.userDetailsService(committeeDetailsService);
	}

	/*
	 * We want the Spring Security to treat the incoming password as it as and not
	 * do any hashing. Hence we have used to NoOpPasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	// We need to manually define the bean for AuthenticationManager for @Autowired
	// AuthenticationManger
	// to work
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and().csrf().disable().authorizeRequests()
				.antMatchers("/login", "/signUp", "/addDomainName", "/college/{emailId}", "/user/confirm-account" , "/committee/signUp", "/committee/login", "/committee/confirm-account", "/forgotPassword", "/reset-password").permitAll().anyRequest()
				.authenticated().and().exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// Asking spring security not to create and manage sessions because we want to
		// use JWT as authentication.

		// Adding the JWTRequestFilter in the filter chain:
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}

}
