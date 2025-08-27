package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberDTO;

import lombok.Data;

//@RestController: 주방장. 외부 요청을 받으면 요리를 시작함 (요리는 메서드가 함).
//@RequestMapping("/api"): 레스토랑 입구 주소 = /api
//@GetMapping, @PostMapping, @PutMapping 등: 요리 종류. 손님이 “이 요리 주세요~” 하면 실행됨.
//Postman이나 브라우저 주소창: 요청(request)을 보냄.
//list: 창고(메모리 DB 역할). 모든 데이터가 여기 있음.

@Data
@RestController
@RequestMapping("/api")
public class MemberController {
	// 데이터저장용객체생성
	private List<MemberDTO> list = new ArrayList<>();
	public MemberController() { // 데이터초기화
		for(int i= 1 ; i<= 10 ; i++ ) {
			 list.add(MemberDTO.builder()
				 .id(i).name("name"+ i).pass("pass"+ i)
				 .regidate(new Date()).build());
		 }
	 }
	
	// select(Read –select All)
	 @GetMapping("/member") 
	 public List< MemberDTO> getAllMember() {
		 return list;
	 }
	 
	// select(Read –select One)
	 @GetMapping("/member/{id}") 
	 public MemberDTO getMemberById(@PathVariable Integer id) {
		// 코드를추가해서완성하세요
		for (MemberDTO m : list) {
		    if (Objects.equals(m.getId(), id)) {
		        return m;
		    }
		}
		 return null;
	 }
	 
	
	// insert 입력(Create -insert)
	 @PostMapping("/member") 
	 public MemberDTO postMember(@RequestBody MemberDTO memberDTO) {
		 	list.add(memberDTO); // 리스트에 추가
		 	return memberDTO;    // 저장된 내용 반환
	 }
	 
	// Update 수정(Update – 객체 교체)
	 @PutMapping("/member/{id}") 
	 public MemberDTO putMember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
		 
	     for (int i = 0; i < list.size(); i++) {
	    	 
		    if (Objects.equals(list.get(i).getId(), id)) {
	    		    list.set(i, memberDTO);
	    		    return memberDTO;
	    	}
	     }
	     return null; // 해당 id가 없으면 null 반환
	 }
	 
	 
	// Update 수정(Update–일부정보수정)
	 @PatchMapping("/member/{id}") 
	 public MemberDTO patchMember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
		 // 코드를추가해서완성하세요
		 for ( MemberDTO m : list) {
			 if(Objects.equals(m.getId(), id)) {
				 if (memberDTO.getName() != null) {
					 m.setName(memberDTO.getName());
				 }
				 if (memberDTO.getPass() != null) {
					 m.setPass(memberDTO.getPass());
				 }
				 if (memberDTO.getRegidate() != null) {
					 m.setRegidate(memberDTO.getRegidate());
				 }
				 return m;
			 }	 
		 }
		 return null;
	 }
	 
	 
	// Delete 삭제(Delete -delete)
	 @DeleteMapping("/member/{id}") 
	 public void deleteMember(@PathVariable Integer id) {
		 // 코드를추가해서완성하세요
		 list.removeIf(m -> Objects.equals(m.getId(), id));
		 
	}

}
