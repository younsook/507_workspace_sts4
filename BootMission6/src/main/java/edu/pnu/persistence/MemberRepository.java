package edu.pnu.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domein.Member;



public interface MemberRepository extends JpaRepository<Member, Long> {

}
