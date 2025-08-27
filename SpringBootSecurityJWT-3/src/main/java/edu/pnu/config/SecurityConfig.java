package edu.pnu.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import edu.pnu.config.filter.JWTAuthenticationFilter;
import edu.pnu.config.filter.JWTAuthorizationFilter;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//시큐리티 인증 필터 등록
	private final AuthenticationConfiguration authenticationConfiguration;
	
	//시큐리티 인가 필터 등록
	private final MemberRepository memberRepository;
	
	//application.properties   OAuth2 로그인 사용자를 DB에 저장(X)
	//@Resource(name = "${project.oauth2login.qualifier.name}")
	//OAuth2 로그인에 성공한 뒤 후처리를 진행하는 핸들러 등록
	//private AuthenticationSuccessHandler oauth2SuccessHandler;
	
	//--------------------------------------------
	//나중에 DB 미저장 버전으로 바꾸려면 @Qualifier("OAuth2SuccessHandlerWithoutDB")로 이름만 바꾸면 됨.
	//장점: null 주입 리스크 없음, 순환참조 회피, 가장 단순.
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http,
			@Qualifier("OAuth2SuccessHandlerWithDB") AuthenticationSuccessHandler oauth2SuccessHandler
			) throws Exception {
		// CSRF 보호비활성화(CsrfFilter제거)
		http.csrf(csrf->csrf.disable());
		http.authorizeHttpRequests(auth->auth
			.requestMatchers("/member/**").authenticated()
			.requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
			.requestMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().permitAll());  // AuthorizationFilter등록
		
		// Form을 이용한 로그인을 사용하지 않겠다는 명시적 설정
		// UsernamePasswordAuthenticationFilter가 현재 없지만 명시적 제거	
		http.formLogin(frmLogin->frmLogin.disable());
		
		// HttpBasic인증 방식을 사용하지 않겠다는 명시적 설정
		// BasicAuthenticationFilter가 현재 없지만 명시적 제거
		http.httpBasic(basic->basic.disable());
		
		// 세션을 유지하지 않겠다고 설정(SessionManagementFilter 등록)
		//Url 호출 뒤 응답할 때 까지는 유지되지만 응답 후 삭제
		http.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		// 스프링 시큐리티의 필터체인에 작성한 필터를 추가한다. UsernamePasswordAuthenticationFilter를 상속한 필터이므로
		// 원래 UsernamePasswordAuthenticationFilter가 위치하는 곳에 대신 추가된다.
		http.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()));
		
		// 스프링 시큐리티가 등록한 필터들 중에서 AuthorizationFilter 앞에 JWTAuthorizationFilter를 삽입한다.
		http.addFilterBefore(new JWTAuthorizationFilter(memberRepository), AuthorizationFilter.class);
		
		//OAuth2 로그인에 성공한 뒤 후처리를 진행하는 핸들러 등록
		//→JWT 토큰을 만들어서 응답 헤더에 설정
		//→필요에 따라 로그인 사용자 DB에 저장
		http.oauth2Login(oauth2->oauth2.successHandler(oauth2SuccessHandler));
		
		
		return http.build();
	} 
}
