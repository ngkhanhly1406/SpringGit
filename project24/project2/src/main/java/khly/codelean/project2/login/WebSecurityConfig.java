package khly.codelean.project2.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig {

	@Autowired
	private DataSource dataSource;

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))

				.authorizeHttpRequests(authorizeRequests ->
						authorizeRequests

								.requestMatchers("/cart/**", "/checkout/**").authenticated()
								.requestMatchers("/shop-list-no-sidebar").permitAll()
								.requestMatchers("/cart/**", "/add/**").authenticated()
							//	.requestMatchers("/my_account").permitAll() // Yêu cầu người dùng phải xác thực để vào trang account

								.anyRequest().permitAll()
				)


				.formLogin(formLogin ->
						formLogin
								.loginPage("/login") // Trang đăng nhập tùy chỉnh của bạn
								.usernameParameter("email") // Tên trường cho tên người dùng (username)
								.passwordParameter("password") // Tên trường cho mật khẩu
								.defaultSuccessUrl("/", true)
								.failureUrl("/login?error") // URL khi đăng nhập thất bại
								.permitAll()
				)

				.logout(logout ->
						logout
								.logoutUrl("/logout") // URL thực hiện logout
								.logoutSuccessUrl("/") // Trang chuyển hướng sau khi logout thành công
								.permitAll()
				);

		return http.build();
	}


	@Bean
	public AuthenticationConfiguration authenticationConfiguration() {
		return new AuthenticationConfiguration();
	}
}
