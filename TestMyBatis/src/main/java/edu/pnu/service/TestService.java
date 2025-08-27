package edu.pnu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {
	private final EntityManager em;
	public List<Board> getBoardList(String cond, String keyword) {
	 // 질의문생성
	String sql="select b from Board b where 1=1";
	StringBuilder sb= new StringBuilder(sql);
	if(cond!= null) {
		 if(cond.equals("title"))
			 sb.append(" AND b.title like '%"+ keyword+ "%'");
		 else if(cond.equals("content"))
			 sb.append(" AND b.content like '%"+ keyword+ "%'");
	}
	 sb.append(" ORDER BY b.id ASC");
	 // 질의객체생성
	TypedQuery<Board> query= em.createQuery(sb.toString(),Board.class);
	 query.setFirstResult(0); // 페이징시작위치(0-based)
	 query.setMaxResults(5); // 페이지사이즈: pageSize
	 return query.getResultList(); // 데이터베이스연결및질의실행
	}
}
