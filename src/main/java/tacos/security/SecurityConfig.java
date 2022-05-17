package tacos.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(6);
//		return new StandardPasswordEncoder("53cr3t"); deprecated
	}

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			  .passwordEncoder(encoder());;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
			.antMatchers("/design", "/orders")
			.hasRole("USER")
			.antMatchers("/", "/**")
			.permitAll()
			.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/design",true) // Forces users to /design after login
			.loginProcessingUrl("/authenticate") //Where the login form should send the data
			.usernameParameter("user") // Name of username in login form
			.passwordParameter("pwd")  // Name of password in login form
			.and()
			.logout()
			.logoutSuccessUrl("/");
	}

	// Same as above
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.authorizeRequests()
//			.antMatchers("/design", "/orders")
//			.access("hasRole('ROLE_USER')")
//			.antMatchers("/", "/**")
//			.access("permitAll")
//		;
//	}

	// Only allows the creation and order of Tacos on Tuesdays
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.authorizeRequests()
//		    .antMatchers("/design", "/orders")
//			.access("hasRole('ROLE_USER') && T(java.util.Calendar).getInstance().get(T(java.util.Calendar).DAY_OF_WEEK) == T(java.util.Calendar).TUESDAY")
//			.antMatchers("/", "/**")
//			.access("permitAll")
//		;
//	}

	//JDBC based authentication
//	@Autowired
//	DataSource dataSource;
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth)
//		  throws Exception {
//		auth.jdbcAuthentication()
//			  .dataSource(dataSource)
//			  .usersByUsernameQuery(
//					"select username, password, enabled from Users " +
//					"where username=?")
//			  .authoritiesByUsernameQuery(
//					"select username, authority from UserAuthorities " +
//					"where username=?")
//			  .passwordEncoder(new BCryptPasswordEncoder(6));
//	}

//	LDAP authentication
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth)
//	throws Exception {
//		auth
//		.ldapAuthentication()
//		.userSearchBase("ou=people")
//		.userSearchFilter("(uid={0})")
//		.groupSearchBase("ou=groups")
//		.groupSearchFilter("member={0}");
//      .passwordCompare()
//      .passwordEncoder(new BCryptPasswordEncoder())
//		.passwordAttribute("passcode");
//      .contextSource()
//	    .root("dc=tacocloud,dc=com");
//	    .ldif("classpath:users.ldif");
//
//
//	    //For a remote LDAP server instead of the embedded above (.root())
//      .url("ldap://tacocloud.com:389/dc=tacocloud,dc=com");
//	}

	//In-memory based authentication
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth)
//		  throws Exception {
//		auth.inMemoryAuthentication()
//		  .withUser("buzz")
//		  .password("infinity")
//		  .authorities("ROLE_USER")
//		  .and()
//		  .withUser("woody")
//		  .password("bullseye")
//		  .authorities("ROLE_USER");
//	}
}