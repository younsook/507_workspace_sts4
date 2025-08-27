package com.rubypaper.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(security->security
			 .requestMatchers("/member/**").authenticated()
			 .requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
			 .requestMatchers("/admin/**").hasRole("ADMIN")
			 .anyRequest().permitAll());
		 
		http.csrf(cf->cf.disable());
			
		//http.formLogin(form->{});
		http.formLogin(form->form
				.loginPage("/login")
				.defaultSuccessUrl("/loginSuccess", true));
		
		http.exceptionHandling(ex->ex.accessDeniedPage("/accessDenied"));
		
		//로그아웃 설정
		http.logout(logout->logout
		//현재 브라우저와 연결된 세션 강제 종료(삭제)
		.invalidateHttpSession(true)
		 // 세션아이디가저장된쿠키삭제
		.deleteCookies("JSESSIONID")
		 // 로그아웃후이동할URL 지정
		.logoutSuccessUrl("/login"));

		return http.build();
	}
	
	//PasswordEncoder사용하기 > 비밀번호암호화Bean 객체등록방법
	@Bean
	PasswordEncoder passwordEncoder() {
	 return new BCryptPasswordEncoder();
	}
	
//	//db연동
//	@Autowired
//	private DataSource dataSource;
//	
//	@Autowired
//	public void  authenticate(AuthenticationManagerBuilder auth) throws Exception {
//		//테스트용
////		auth.inMemoryAuthentication()
////			.withUser("member").password("{noop}abcd").roles("MEMBER");
////		auth.inMemoryAuthentication()
////			.withUser("manager").password("{noop}abcd").roles("MANAGER");
////		auth.inMemoryAuthentication()
////			.withUser("admin").password("{noop}abcd").roles("ADMIN");
//		
//		//db연동
//		String query1 = "select id username, concat('{noop}', password) password, true enabled from member where id=?";
//		String query2 = "select id, role from member where id=?";
//		auth.jdbcAuthentication()
//			.dataSource(dataSource)
//			.usersByUsernameQuery(query1)
//			.authoritiesByUsernameQuery(query2);
//	}	
}
