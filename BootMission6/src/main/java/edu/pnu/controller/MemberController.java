package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domein.Member;
import edu.pnu.service.MemberService;




@RestController
@RequestMapping("/api")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	//전체 조회
	@GetMapping("/member")
	public List<Member> getMembers() {
		return memberService.getAllMember();
	}
	
	//특정 id 조회
	@GetMapping("/member/{id}")
	public Member getMemberById(@PathVariable Long id) {
		return memberService.getMemberById(id);
	}
	
	//insert입력 
	@PostMapping("/member")
	public Member postMember(@RequestBody Member member) {
		return memberService.postMember(member);
	}
	
	//update 전체 수정
	@PutMapping("/member/{id}")
	public Member putMember(@PathVariable Long id, @RequestBody Member member) {
		return memberService.putMember(id, member);
	}
	
	//update 부분 수정
	@PatchMapping("/member/{id}")
	public Member patchMember(@PathVariable Long id, @RequestBody Member member) {
		return memberService.patchMember(id, member);
	}
	
	//delete 삭제
	 @DeleteMapping("/member/{id}") 
	 public void deleteMember(@PathVariable Long id) {
		 memberService.deleteMember(id);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
