package com.rubypaper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rubypaper.domain.Board;
import com.rubypaper.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	//전체조회
	//@GetMapping("/getBoardList")
	@RequestMapping(value="/getBoardList", method = {RequestMethod.GET, RequestMethod.POST})
	public String getBoardList(Model model) {
		List<Board> boardList = boardService.getBoardList();
		
		model.addAttribute("boardList", boardList);
		
		return "getBoardList";
	}
	
	//새글등록 1
	@GetMapping("/insertBoard")
	public String insertBoardView() {
		return "insertBoard";
	}
	//새글등록 2
	@PostMapping("/insertBoard")
	public String insertBoard(Board board) {
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	//상세조회 - 조회수 증가 없음
	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model) {
		Board findBoard = boardService.getBoard(board);
		model.addAttribute("board", findBoard);
		// model.addAttribute("board", boardService.getBoard(board)); //조회수2회증가됨
		return "getBoard";
	}
	
	// 상세조회 - 단순 조회수 증가 (테스트용)
//	@GetMapping("/getBoard")
//	public String getBoard(@RequestParam Long seq, Model model) {
//	    // 1) 해당 글 가져오기
//	    Board board = boardService.getBoardBySeq(seq);
//	
//
//	    // 2) cnt + 1
//	    Long currentCnt = (board.getCnt() == null ? 0L : board.getCnt());
//	    board.setCnt(currentCnt + 1);
//
//	    // 3) 저장
//	    boardService.updateBoard(board);
//
//	    // 4) 모델에 담아서 뷰로 전달
//	    model.addAttribute("board", board);
//	    return "getBoard";
//	}
	
	//수정
	@PostMapping("updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "forward:getBoardList"; //"redirect:getBoardList"
		//forward 경우 @RequestMapping(value="/getBoardList", method = {RequestMethod.GET, RequestMethod.POST})
	}
	
	//삭제
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}

}
