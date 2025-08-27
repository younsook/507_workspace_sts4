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
import edu.pnu.persistence.MemberRepository;
import edu.pnu.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {  // 하나의 요청에 대해 필터를 한번만 거치도록 OncePerRequestFilter상속
	private final MemberRepository memberRepository; // 인가 설정을 위해 사용자의 Role 정보를 읽어 들이기 위한 객체 설정
	
	@Override
	 protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			 						throws IOException, ServletException {
		
		String srcToken = request.getHeader(HttpHeaders.AUTHORIZATION); // 요청 헤더에서 Authorization을 얻어온다.
		if (srcToken == null || !srcToken.startsWith("Bearer ")) {  // 없거나 "Bearer "로 시작하지 않는다면
			 filterChain.doFilter(request, response); // 필터를 그냥 통과
			 return;
		}
		String jwtToken = srcToken.replace("Bearer ", ""); // 토큰에서 "Bearer "를 제거
		
		// 토큰에서 username 추출
		String username = JWTUtil.getClaim(jwtToken);
		Optional<Member> opt = memberRepository.findById(username); // 토큰에서 얻은 username으로 DB를 검색해서 사용자를 검색
		if (!opt.isPresent()) { // 사용자가 존재하지 않는다면
			 filterChain.doFilter(request, response); // 필터를 그냥 통과
			 return;
		}
		Member findmember = opt.get();
		
		User user = new User(findmember.getUsername(), findmember.getPassword(),
				 AuthorityUtils.createAuthorityList(findmember.getRole().toString())); // UserDetails 타입 객체 생성
		
		// Authentication 객체를 생성 : 사용자명과 권한 관리를 위한 정보를 입력(암호는 필요 없음)
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		// 시큐리티 세션에 등록한다.
		SecurityContextHolder.getContext().setAuthentication(auth);
		// SecurityFilterChain의 다음 필터로 이동
		filterChain.doFilter(request, response);		 
		 
		
		
	}
	
}