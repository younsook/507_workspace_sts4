package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import edu.pnu.domain.MemberDTO;


public class MemberService {

	// ys_1. 데이터저장용 객체생성
		private List<MemberDTO> list = new ArrayList<>();
		
		// 데이터초기화
		public MemberService() { 
			for (int i = 1; i <= 10; i++) {
				MemberDTO m = new MemberDTO();
				m.setId(i);
				m.setName("name" + i);
				m.setPass("pass" + i);
				m.setRegidate(new Date());
				list.add(m);
			}
		 }
		
		// select(Read –select All)

		 public List< MemberDTO> getAllMember() {
			 return list;
		 }
		 
		// select(Read –select One)

		 public MemberDTO getMemberById(Integer id) {
			// 코드를추가해서완성하세요
			for (MemberDTO m : list) {
			    if (Objects.equals(m.getId(), id)) {
			        return m;
			    }
			}
			 return null;
		 }
		 
		
		// insert 입력(Create -insert)

		 public MemberDTO postMember(MemberDTO memberDTO) {
			 	list.add(memberDTO); // 리스트에 추가
			 	return memberDTO;    // 저장된 내용 반환
		 }
		 
		// Update 수정(Update – 객체 교체)

		 public MemberDTO putMember(Integer id, MemberDTO memberDTO) {
			 
		     for (int i = 0; i < list.size(); i++) {
		    	 
		    	if (Objects.equals(list.get(i).getId(), id)) {
		    		    list.set(i, memberDTO);
		    		    return memberDTO;
		    	}
		     }
		     return null; // 해당 id가 없으면 null 반환
		 }
		 
		 
		// Update 수정(Update–일부정보수정)

		 public MemberDTO patchMember(Integer id, MemberDTO memberDTO) {
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

		 public void deleteMember(Integer id) {
			 // 코드를추가해서완성하세요
			 list.removeIf(m -> Objects.equals(m.getId(), id));
			 
		}
}
