package edu.pnu;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domein.Member;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
class BootMission6ApplicationTests {
	@Autowired
	private MemberRepository memberRepo;

	@Test
	void contextLoads() {
		List<Member> list = memberRepo.findAll();
		
		//list.forEach(System.out::println);
		for(Member m : list)
			System.out.println(m);
	}
}
