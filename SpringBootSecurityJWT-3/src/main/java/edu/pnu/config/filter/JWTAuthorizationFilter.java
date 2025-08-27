package edu.pnu.config.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import edu.pnu.domain.Member;
import edu.pnu.domain.Role;
import edu.pnu.persistence.MemberRepository;
import edu.pnu.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {
	private final MemberRepository memberRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain) throws ServletException, IOException {
		
		 // 요청헤더에서JWT를얻어온다.
		String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(jwtToken == null || jwtToken.startsWith(JWTUtil.prefix)) {
			// 없거나"Bearer "로시작하지않는다면
			filterChain.doFilter(request, response);  // 필터를그냥통과
			return;
		}
		// 토큰에서 username 추출
		String username = JWTUtil.getClaim(jwtToken, JWTUtil.usernameClaim);
		User user = null;
		if(username != null) {
			Optional<Member> opt = memberRepository.findById(username);
			// 토큰에서 얻은 username으로 DB를 검색해서 사용자를 검색
			if(!opt.isPresent()) { 							// 사용자가 존재하지 않는다면
				System.out.println("[JWTAuthorizationFilter]not found user!");
				filterChain.doFilter(request, response);  	// 필터를 그냥 통과
				return;
			}
			
			Member findmember = opt.get();
			System.out.println("[JWTAuthorizationFilter]"+ findmember);
			// DB에서 읽은 사용자정보를 이용해서 UserDetails 타입의 객체를 만들어서
			user = new User(findmember.getUsername(), findmember.getPassword(),
					AuthorityUtils.createAuthorityList(findmember.getRole().toString()));
		} else {
			String provider = JWTUtil.getClaim(jwtToken, JWTUtil.providerClaim);
			String email = JWTUtil.getClaim(jwtToken, JWTUtil.emailClaim);
			System.out.println("[JWTAuthorizationFilter]username:"
					+ provider + "_" + email);
			user = new User(provider + "_" + email, "****",
					AuthorityUtils.createAuthorityList(Role.ROLE_MEMBER.toString()));
		}
		
		// UserDetails타입객체생성
		//User user = new User(findmember.getUsername(), findmember.getPassword(),
		//		AuthorityUtils.createAuthorityList(findmember.getRole().toString()));
		
		// 인증객체생성: 사용자명과권한관리를위한정보를입력(암호는필요없음)
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		// 시큐리티세션에등록
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		// SecurityFilterChain의다음필터로이동
		filterChain.doFilter(request, response);
		
		
		
	}
}
