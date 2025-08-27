package com.rubypaper.controller;

import com.rubypaper.domain.Member; 
import com.rubypaper.persistence.MemberRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
	
	@Autowired
	private MemberRepository memberRepo; 

	
	@GetMapping("/member")
	public List<Member> getMember(){
		//전체 게시글 목록 조회
		return memberRepo.findAll(); //검색 결과 목록 리턴 
	}
	


}
