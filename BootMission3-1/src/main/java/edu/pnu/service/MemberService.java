package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberDTO;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MemberService {
	
		@Autowired
	    private MemberDAO memberDAO;
	    
	    // select(Read –select All) 전체 조회
		public List<MemberDTO> getAllMember() {
	        return memberDAO.getAllMembers(); // DB에서 가져오기
		}
		
//		// select(Read –select One) 단건 조회
//		 public MemberDTO getMemberById(Integer id) {
//			 return memberDAO.getMemberById(id);
//		 }
//		
//		// insert 입력(Create -insert) 등록
//		 public MemberDTO postMember(MemberDTO memberDTO) {
//			 return memberDAO.insertMember(memberDTO);
//		 }
//		 
//		// Update 수정(Update – 객체 교체) 전체 수정
//		 public MemberDTO putMember(Integer id, MemberDTO memberDTO) {
//			 return memberDAO.updateMember(id, memberDTO);
//		 }
//		 
//		// Update 수정(Update–일부정보수정) 일부 수정
//		 public MemberDTO patchMember(Integer id, MemberDTO memberDTO) {
//			  return memberDAO.patchMember(id, memberDTO);
//		 }
//		 
//		// Delete 삭제(Delete -delete)
//		 public void deleteMember(Integer id) {
//			 memberDAO.deleteMember(id);
//		}
}
