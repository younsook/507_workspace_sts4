package com.rubypaper.persistence;

import com.rubypaper.domain.Member;


import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

}
