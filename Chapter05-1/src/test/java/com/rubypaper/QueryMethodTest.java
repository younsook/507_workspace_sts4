package com.rubypaper;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;
@SpringBootTest
public class QueryMethodTest {
	@Autowired
	private BoardRepository boardRepo;
	
	@BeforeAll //@BeforeAll : 모든 테스트 실행 전에 한번 실행 
	static void dataPrepare(@Autowired BoardRepository boardRepo) {
		System.out.println("dataPrepare()");
		for(int i =1; i<=200; i++) {
			Board board = new Board();
			board.setTitle("테스트 제목 "+i);
			board.setWriter("테스터");
			board.setContent("테스트 내용 "+i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardRepo.save(board);
		}
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
	 public void testByContentContaining() {
	 List<Board> boardList= boardRepo.findByContentContaining("17"); 
	 
	 	System.out.println("검색결과");	
	 	for(Board board : boardList) {
			 System.out.println("---> " + board.toString());
		}
	 }
	 @Test
	 public void testFindByTitleContainingOrContentContaining() {
		List<Board> boardList = boardRepo.findByTitleContainingOrContentContaining("17", "17");
		
		System.out.println("검색결과");	
	 	for(Board board : boardList) {
			 System.out.println("---> " + board.toString());
		}
	 }
	 @Test
	 public void  testFindByTitleContainingOrderBySeqDesc() {
		 List<Board> boardList = boardRepo.findByTitleContainingOrderBySeqDesc("17");
		
		System.out.println("검색결과");	
	 	for(Board board : boardList) {
			 System.out.println("---> " + board.toString());
		}
	 }
//	 //페이징
//	 @Test
//	 public void testFindByTitleContaining() {
//		 Pageable paging = PageRequest.of(0, 5);
//		 List<Board> boardList = boardRepo.findByTitleContaining("제목", paging);
//			
//			System.out.println("title에'제목'이포함된 데이터를 검색한뒤5개씩\r\n"
//					+ "페이징해서첫번째페이지를검색(0-based index)");	
//		 	for(Board board : boardList) {
//				 System.out.println("---> " + board.toString());
//			}
//	 }
//	//페이징&정렬
//	 @Test
//	 public void testFindByTitleContaining() {
//		
//		 Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "seq");
//		 
//		 List<Board> boardList = boardRepo.findByTitleContaining("제목", paging); //findByTitleContainingOrderbyseqasc
//			
//			System.out.println("title에'제목'이포함된 데이터를 검색한뒤5개씩\r\n"
//					+ "페이징해서첫번째페이지를검색(0-based index)");	
//		 	for(Board board : boardList) {
//				 System.out.println("---> " + board.toString());
//			}
//	 }
	 @Test
	 public void testFindByTitleContaining() {
		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "seq");
		 
		Page<Board> pageInfo = boardRepo.findByTitleContaining("제목", paging);
		System.out.println("PAGE SIZE : " + pageInfo.getSize());
		System.out.println("TOTAL PAGES : "+ pageInfo.getTotalPages());
		System.out.println("TOTAL COUNT : "+ pageInfo.getTotalElements());
		System.out.println("NEXT : "+ pageInfo.nextPageable());
		 
		List<Board> boardList = pageInfo.getContent();
				 
		System.out.println("TOTAL title에'제목'이포함된 데이터를 검색한뒤5개씩\r\n"
				+ "페이징해서첫번째페이지를검색(0-based index)");	
	 	for(Board board : boardList) {
			 System.out.println("---> " + board.toString());
		}
	 }

}
