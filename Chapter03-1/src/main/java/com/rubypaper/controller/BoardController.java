package com.rubypaper.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardVO;

@RestController
public class BoardController {
	public BoardController() {
		 System.out.println("===> BoardController생성");
	}
	 @GetMapping("/hello")
	 public String hello(String name) {
		 return "Hello : " + name;
	 }
	 
	// @GetMapping("/getBoard")
	 public BoardVO getBoard() {
		// 기존 set 방식 
//		 BoardVO board = new BoardVO();
//		 board.setSeq(1);
//		 board.setTitle("테스트제목...");
//		 board.setWriter("테스터");
//		 board.setContent("테스트내용입니다...............");
//		 board.setCreateDate(new Date());
//		 board.setCnt(0);
		 
		// Builder 방식 사용
	    BoardVO board = BoardVO.builder()
			            .seq(1)
			            .title("테스트제목...")
			            .writer("테스터")
			            .content("테스트내용입니다…")
			            .createDate(new Date())
			            .cnt(0)
			            .build();

		 
		 return board;
	 }
	 
// 	 @GetMapping("/getBoard")
// 	 public BoardVO board(Integer seq) {
// 		BoardVO board = new BoardVO();
// 		board.setSeq(seq);
// 		board.setWriter("테스터");
// 		return board;
// 	 }
 	 
 	@GetMapping("/getBoard")
 	 public BoardVO board() {
 		 BoardVO board = new BoardVO();
 		 board.setWriter("테스터");
 		 return board;
 	 }
	 
	 @GetMapping("/getBoardList")
	 public  List<BoardVO> getBoardList() {
		 List<BoardVO> boardList = new ArrayList<BoardVO>();
		 for (int i = 1; i <= 10; i++) {
			 BoardVO board = new BoardVO();
			 board.setSeq(i);
			 board.setTitle("제목" +i);
			 board.setWriter("테스터");
			 board.setContent(i+"번 내용입니다.");
			 board.setCreateDate(new Date());
			 board.setCnt(0);
			 boardList.add(board);
		 }
		 return boardList;
	 }
	 

	

	 
}
