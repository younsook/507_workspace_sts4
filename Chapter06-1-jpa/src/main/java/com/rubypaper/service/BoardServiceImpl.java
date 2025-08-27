package com.rubypaper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;
	@Override
	public List<Board> getBoardList() {
		// 전체조회
		return boardRepo.findAll();
	}

	@Override
	public Board getBoard(Board board) {
		// 상세조회
//		return boardRepo.findById(board.getSeq()).get();
		Board findBoard = boardRepo.findById(board.getSeq()).get();
		findBoard.setCnt(findBoard.getCnt() + 1);
		return boardRepo.save(findBoard);
	}
	//----------------------------------------------------------------------------
//	@Override
//    public Board getBoardBySeq(Long seq) {
//		//상세조회시 게시글 읽어오기 (조회수 증가위해)
//        return boardRepo.findById(seq)
//                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다. seq=" + seq));
//    }
	//----------------------------------------------------------------------------

	@Override
	public void insertBoard(Board board) {
		// 새글 등록
		boardRepo.save(board);
	}

	@Override
	public void updateBoard(Board board) {
		// 수정
		Board findBoard = boardRepo.findById(board.getSeq()).get();
		
		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		boardRepo.save(findBoard);

	}

	@Override
	public void deleteBoard(Board board) {
		// 삭제
		 boardRepo.deleteById(board.getSeq());

	}

}
