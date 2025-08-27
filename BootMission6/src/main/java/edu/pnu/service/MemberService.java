package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domein.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class MemberService {

		@Autowired
		private MemberRepository memberRepo;
	
		// select(Read –select All)
		 public List< Member> getAllMember() {
			 return memberRepo.findAll();
		 }
		 
		// select(Read –select One)
		 public Member getMemberById(Long id) {
			 return memberRepo.findById(id).orElse(null);
		 }
		
		// insert 입력(Create -insert)
		 public Member postMember(Member member) {
			 return memberRepo.save(member);
		 }
		 
		// Update 전체 수정
		 public Member putMember(Long id, Member member) {			 
			 Member putData = memberRepo.findById(id).orElse(null);
			 
			 if(putData == null) return null;
			 
			 putData.setName(member.getName());
			 putData.setPass(member.getPass());
			 putData.setEmail(member.getEmail());
			 return memberRepo.save(putData);
		 }
		 
		 
		// Update 부분 수정
		 public Member patchMember(Long id, Member member) {
			 Member putData = memberRepo.findById(id).orElse(null);
			 
			 if(putData == null) return null;
			 
			 if(member.getName() != null) {
				 putData.setName(member.getName());
			 }
			 if(member.getPass() != null) {
				 putData.setPass(member.getPass());
			 }
			 if(member.getEmail() != null) {
				 putData.setEmail(member.getEmail());
			 }

			 return memberRepo.save(putData);
		 }
		 
		// Delete 삭제
		 public void deleteMember(Long id) {
			 memberRepo.deleteById(id);
		}
}
