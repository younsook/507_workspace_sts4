package com.rubypaper;

import java.util.Date;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class BoardRepositoryTest {

	 @Autowired
	 private BoardRepository boardRepo;
	 
	 @Test
	 @Order(1)
	 public void testInsertBoard() {
		 Board board = new Board();
		 board.setTitle("첫 번째 게시글");
		 board.setWriter("테스터");
		 board.setContent("잘 등록되나요?");
		 board.setCreateDate(new Date());
		 board.setCnt(0L);
		 boardRepo.save(board);
	 }
	 
	 @Test
	 @Order(2)
	 public void testGetBoard() {
		 //testGetBoard = select
		 Board board = boardRepo.findById(2L).get();
		 System.out.println(board);
	 }
	 
	 @Test
	 public void testUpdateBoard() {
		 System.out.println("==1번 게시글 조회==");
		 Board board = boardRepo.findById(2L).get();
		 System.out.println("==게시글 제목 수정==");
		 board.setTitle("제목을 수정했습니다.");
		 boardRepo.save(board);
	 }
	 
	 //@Test
	 public void testDeleteBoard() {
		 boardRepo.deleteById(1L);
	 }
}
