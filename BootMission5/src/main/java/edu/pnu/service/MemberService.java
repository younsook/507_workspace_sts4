package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberDTO;

@Service
public class MemberService {
		@Autowired
		private MemberDAO memberDAO; // 멤버 필드로 선언
	
		// 데이터초기화
		public MemberService() { 
			this.memberDAO = new MemberDAO(); // DB에서 연결
		 }
		
		// select(Read –select All)

		 public List< MemberDTO> getAllMember() {
			 return memberDAO.getAllMembers(); // 이제 DB에서 조회
		 }
		 
		// select(Read –select One)

		 public MemberDTO getMemberById(Integer id) {
			// 코드를추가해서완성하세요
			 return memberDAO.getMemberById(id);
		 }
		 
		
		// insert 입력(Create -insert)

		 public MemberDTO postMember(MemberDTO memberDTO) {
			 return memberDAO.insertMember(memberDTO);
		 }
		 
		// Update 수정(Update – 객체 교체)

		 public MemberDTO putMember(Integer id, MemberDTO memberDTO) {
			 
			 return memberDAO.updateMember(id, memberDTO);
		 }
		 
		 
		// Update 수정(Update–일부정보수정)

		 public MemberDTO patchMember(Integer id, MemberDTO memberDTO) {
			 // 코드를추가해서완성하세요
			 return memberDAO.patchMember(id, memberDTO);
		 }
		 
		// Delete 삭제(Delete -delete)

		 public void deleteMember(Integer id) {
			 // 코드를추가해서완성하세요
			 memberDAO.deleteMember(id);
			 
		}
}
