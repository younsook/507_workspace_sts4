package com.rubypaper.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rubypaper.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
//	List<Board> findByTitle(String searchKeyword); //
//	List<Board> findByContentContaining(String searchKeyword);
//	List<Board> findByTitleContaining(String searchKeyword);
//	Page<Board> findByTitleContaining(String searchKeyword, Pageable paging); //List<Board>
//	
//	List<Board> findByTitleContainingOrContentContaining(String title, String content);
//	List<Board> findByTitleContainingOrderBySeqDesc (String searchKeyword);
//	List<Board> findByCntGreaterThanEqual(Long cnt);
//	List<Board> findByCntGreaterThan(Long cnt);
//	List<Board> findByTitleContainingAndCntGreaterThan(String string, Long cnt);
//	List<Board> findByCntBetweenOrderBySeqAsc(Long start, Long end);
//	
//	 //@Query("SELECT b FROM Board b WHERE b.title like %?1% ORDER BY b.seq DESC")
//	 //List<Board> queryAnnotationTest1(String searchKeyword);
//	 
//	 @Query("SELECT b FROM Board b WHERE b.title like %:searchKeyword% ORDER BY b.seq DESC")
//	 List<Board> queryAnnotationTest1(@Param("searchKeyword") String searchKeyword);
//	 
//	 @Query("SELECT b.seq, b.title, b.writer, b.createDate FROM Board b WHERE b.title like %?1% ORDER BY b.seq DESC")
//	 List<Object[]> queryAnnotationTest2(@Param("searchKeyword") String searchKeyword);
//	 
//	 @Query("SELECT new com.rubypaper.domain.dto.BoardDTO(b.seq, b.title, b.writer)"
//			 + "FROM Board b "
//			 + "WHERE b.title like %:searchKeyword% ORDER BY b.seq DESC")
//	 List<Object[]> queryAnnotationTest21(String searchKeyword);
//	 
//	//네이티브 쿼리
//	 @Query(value="SELECT seq,title,writer,create_date FROM board WHERE title like '%'||:searchKeyword||'%' ORDER BY seq DESC", nativeQuery=true)
//	 List<Object[]> queryAnnotationTest3(String searchKeyword);
//	 
//	//페이징 및 정렬 처리하기
//	 @Query("select b from Board b order by b.seq DESC")
//	 List<Board> queryAnnotationTest4(Pageable paging);
	 

}
