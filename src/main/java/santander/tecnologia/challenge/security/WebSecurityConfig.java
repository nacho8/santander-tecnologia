package santander.tecnologia.challenge.security;

import javax.ws.rs.HttpMethod;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import santander.tecnologia.challenge.security.jwt.JWTAuthorizationFilter;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.headers().frameOptions().disable();
		 http.csrf().disable()
			.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/logIn").permitAll()
			.antMatchers("/h2-console/**").permitAll()
			.antMatchers("/findAll/**").permitAll()
			.antMatchers(HttpMethod.GET,
                    "/v2/api-docs",
                    "/swagger-resources/**",
                    "/swagger-ui.html**",
                    "/webjars/**",
                    "favicon.ico").permitAll()
			.antMatchers("/confirmAssistence/**").hasAnyRole("USER","ADMIN")
			.antMatchers("/obtainWeather/**").hasAnyRole("USER","ADMIN")
			.antMatchers("/**").hasRole("ADMIN");
	}
}
