package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberDTO;
import edu.pnu.service.MemberService;



@RestController
@RequestMapping("/api")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	// 직접 인스턴스 생성 (Bean 주입이 @Service 아님 )
    public MemberController() {
        this.memberService = new MemberService(); // 직접 new로 생성
    }
	 // select(Read –select All)
	 @GetMapping("/member") 
	 public List< MemberDTO> getAllMember() {
		 return memberService.getAllMember() ;
	 }
//	 
//	// select(Read –select One)
//	 @GetMapping("/member/{id}") 
//	 public MemberDTO getMemberById(@PathVariable Integer id) {
//		 return memberService.getMemberById(id);
//	 }
//	 
//	
//	// insert 입력(Create -insert)
//	 @PostMapping("/member") 
//	 public MemberDTO postMember(@RequestBody MemberDTO memberDTO) {	 
//		 	return memberService.postMember(memberDTO);
//	 }
//	 
//	// Update 수정(Update – 객체 교체)
//	 @PutMapping("/member/{id}") 
//	 public MemberDTO putMember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
//	     return memberService.putMember(id, memberDTO); 
//	 }
//	 
//	 
//	// Update 수정(Update–일부정보수정)
//	 @PatchMapping("/member/{id}") 
//	 public MemberDTO patchMember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
//		 return memberService.patchMember(id, memberDTO);
//	 }
//	 
//	 
//	// Delete 삭제(Delete -delete)
//	 @DeleteMapping("/member/{id}") 
//	 public void deleteMember(@PathVariable Integer id) {
//		 memberService.deleteMember(id);
//	}

}
