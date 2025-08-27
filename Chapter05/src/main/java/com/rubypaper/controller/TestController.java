package com.rubypaper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@RestController
@RequestMapping("/api")
public class TestController {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@GetMapping("/board")
	public List<Board> getBoards(){
		//전체 게시글 목록 조회
		return boardRepo.findAll(); //검색 결과 목록 리턴 
	}
	
	@GetMapping("/board/{seq}") //경로변수
	public Board getBoard (@PathVariable Long seq) {
		//특정 게시글 1건 조회
		return boardRepo.findById(seq).orElseThrow() ; //검색 결과 객체 리턴
	}
	
	@PostMapping("/board")
	public Board postBoard(@RequestBody Board board) { //@RequestBody에 담아서 보내겠다.
		//게시글 등록 create
		return boardRepo.save(board); //입력 객체 리턴
	}
	
	@PutMapping("/board/{seq}")
	public Board putBoard(@PathVariable Long seq, @RequestBody Board board) {
		//전체 수정
		Board putBoard = boardRepo.findById(seq).orElseThrow();
		// 모든 필드 업데이트
		putBoard.setTitle(board.getTitle());
		putBoard.setContent(board.getContent());
		putBoard.setWriter(board.getWriter());
	 return boardRepo.save(putBoard); // 수정 객체 리턴
	}
	
	@PatchMapping("/board/{seq}")
	public Board patchBoard(@PathVariable Long seq, @RequestBody Board board) {
		//부분 수정
		Board existing = boardRepo.findById(seq).orElseThrow();

        // null이 아닌 필드만 업데이트
        if (board.getTitle() != null) existing.setTitle(board.getTitle());
        if (board.getContent() != null) existing.setContent(board.getContent());
        if (board.getWriter() != null) existing.setWriter(board.getWriter());

        return boardRepo.save(existing);  //수정 객체 리턴

	}
	
	@DeleteMapping("/board/{seq}")
	public Board deleteBoard(@PathVariable Long seq) {
		//삭제
		 Board existing = boardRepo.findById(seq).orElseThrow();
	     boardRepo.delete(existing);
		return existing; //삭제 객체 리턴
	}

}
