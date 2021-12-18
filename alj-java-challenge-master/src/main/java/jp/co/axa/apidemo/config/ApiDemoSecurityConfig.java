package jp.co.axa.apidemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security Config Class
 *
 * @author Jimmie
 *
 */
@Configuration
@EnableWebSecurity
public class ApiDemoSecurityConfig {

	@Configuration
	public static class ApiConfigurationAdapter extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http
				.exceptionHandling()
		            .authenticationEntryPoint(new ApiAuthenticationEntryPoint())
		            .accessDeniedHandler(new ApiAccessDeniedHandler())
		            .and()
		        .addFilterAt(loginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
		        .authorizeRequests()
		        	.antMatchers("/api/v1/**").hasRole("USER")
		        .and()
		        .logout()
	            	.logoutUrl("/api/logout")
	            	.invalidateHttpSession(true)
	            	.logoutSuccessHandler(new ApiLogoutSuccessHandler())
	            .and()
				.csrf()
					.disable();

		}

		@Bean
	    PasswordEncoder passwordEncoder() {
	        return NoOpPasswordEncoder.getInstance();
	    }

		@Bean
	    ApiLoginAuthenticationFilter loginAuthenticationFilter() throws Exception {
			ApiLoginAuthenticationFilter filter = new ApiLoginAuthenticationFilter();
	        filter.setAuthenticationManager(authenticationManagerBean());
	        filter.setAuthenticationSuccessHandler(new ApiAuthenticationSuccessHandler());
	        filter.setAuthenticationFailureHandler(new ApiAuthenticationFailureHandler());
	        filter.setFilterProcessesUrl("/api/login");
	        return filter;
	    }

		@Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.inMemoryAuthentication()
	                .withUser("user").password("123").roles("USER");
	    }

		@Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	}

}
