package com.rubypaper;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@SpringBootTest
public class QueryAnnotationTest {
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void testQueryAnnotationTest1() {
		List<Board> boardList = boardRepo.queryAnnotationTest1("테스트 제목 10");
		System.out.println("검색 결과 Test1");
		for (Board board : boardList) {
			System.out.println("--->"+board.toString());
		}
	}
	
	@Test
	public void testQueryAnnotationTest2() {
		List<Object[]> boardList = boardRepo.queryAnnotationTest2("테스트 제목 10");
		 System.out.println("검색 결과 Test2");
		 for (Object[] row : boardList) {
			 System.out.println("---> " + Arrays.toString(row));
		 }
	}
	// @Query –특정변수만조회하기
	@Test
	public void testQueryAnnotationTest21() {
		List<Object[]> boardList = boardRepo.queryAnnotationTest21("테스트 제목 10");
		 System.out.println("검색 결과 Test21");
		 for (Object[] row : boardList) {
			 System.out.println("---> " + Arrays.toString(row));
		 }
		
	}
	//네이티브 쿼리
	@Test
	public void testQueryAnnotationTest3() {
		List<Object[]> boardList = boardRepo.queryAnnotationTest3("테스트 제목 10");
		System.out.println("검색 결과 Test3");
		 for (Object[] row : boardList) {
			 System.out.println("---> " + Arrays.toString(row));
		 }
	}
	//페이징 및 정렬 처리하기
	@Test
	public void testQueryAnnotationTest4() {
		Pageable paging = PageRequest.of(0, 3, Sort.Direction.DESC, "seq");
		List<Board> boardList = boardRepo.queryAnnotationTest4(paging);
		System.out.println("검색 결과 Test4");
		 for (Board board : boardList) {
			 System.out.println("---> " + board.toString());
		 }
	}

}
