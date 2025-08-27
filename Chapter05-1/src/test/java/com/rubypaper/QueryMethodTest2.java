package com.rubypaper;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;
@SpringBootTest
public class QueryMethodTest2 {
	@Autowired
	private BoardRepository boardRepo;
	
	@BeforeAll
	static void dataPrepare(@Autowired BoardRepository boardRepo) {
		System.out.println("dataPrepare()");
		
		Random random = new Random();
		
		for(int i =1; i<=100; i++) {
			Board board = new Board();
			board.setTitle("테스트 제목 "+i);
			board.setWriter("테스터");
			board.setContent("테스트 내용 "+i);
			board.setCreateDate(new Date());
			
			int cnt = random.nextInt(101);
			board.setCnt((long) cnt);
			
			boardRepo.save(board);
		}
		System.out.println("✔ 테스트 데이터 100건 삽입 완료");
	}
	 @Test
	 public void testFindByTitle() {
	 List<Board> boardList = boardRepo.findByTitle("테스트 제목 10");
	 
	 	System.out.println("검색 결과");	
	 	for (Board board : boardList) {
			 System.out.println("---> " + board.toString());
		}
	 }
	 @Test
	 public void testFindByTitleContaining() {
	 List<Board> boardList = boardRepo.findByTitleContaining("1");
	 
	 	System.out.println("title에 '1'이 포함된 데이터 검색 결과");	
	 	for (Board board : boardList) {
			 System.out.println("---> " + board.toString());
		}
	 }
	 @Test
	 public void testFindByCntGreaterThan() {
	     List<Board> boardList = boardRepo.findByCntGreaterThan(50L);
	     System.out.println("cnt > 50 데이터:");
	     for (Board board : boardList) {
	         System.out.println("---> " + board);
	     }
	 }

	 @Test
	 public void testfindFindByTitleContainingAndCntGreaterThan() { //findByGreaterThanEqual
	 List<Board> boardList = boardRepo.findByTitleContainingAndCntGreaterThan("1", 50L);
	 
	 	System.out.println("title에 '1'이 포함되면서 cnt가 50보다 큰 데이터 결과");	
	 	for (Board board : boardList) {
			 System.out.println("---> " + board.toString());
		}
	 }
	 @Test
	 public void testFindByCntBetweenOrderBySeqAsc() {
	     List<Board> boardList = boardRepo.findByCntBetweenOrderBySeqAsc(10L, 50L);

	     System.out.println("cnt가 10~50 사이인 데이터를 seq 오름차순으로 출력");
	     for (Board board : boardList) {
	         System.out.println("---> " + board);
	     }
	 }


	
	 

}
