package com.rubypaper;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;


public class JPAClient {

	
	public static void main(String[] args) {
		//메인.EntityManagerFactory 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		
		try {
			//메인 
			insert(emf);
			System.out.println("---- insert 완료 ----");
			select(emf);
			System.out.println("---- select 완료 ----");
			update(emf);
			System.out.println("---- update 완료 ----");
			delete(emf);
			System.out.println("---- delete 완료 ----");
		
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			//사용한 리소스 객체 닫기
			emf.close();
		}
	}
	//delete
	private static void delete(EntityManagerFactory emf) {
		//EntityManager 생성
	    EntityManager em = emf.createEntityManager();

	    //Transaction 생성
	    EntityTransaction tx = em.getTransaction();

	    try {
	        //Transaction 시작
	        tx.begin();

	        //삭제할 게시글 조회
	        Board board = em.find(Board.class, 1L);
	        if (board != null) {
	            em.remove(board); // 삭제 수행
	            System.out.println("삭제 완료: " + board);
	        } else {
	            System.out.println("삭제 대상 게시글이 없습니다.");
	        }

	        //Transaction commit
	        tx.commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	      //Transaction rollback
			tx.rollback();

	    } finally {
	        em.close(); // 리소스 정리
	    }
		
	}
	//update
	private static void update(EntityManagerFactory emf) {
		//EntityManager 생성
		EntityManager em = emf.createEntityManager();
			
		//1.Transaction 생성
		EntityTransaction tx = em.getTransaction();
		
		try {	
			//Transaction 시작
			tx.begin();
			
			//수정할 게시글 조회
			Board board = em.find(Board.class, 1L);
			board.setItile("22JPA 제목");

			
			//Transaction commit
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//Transaction rollback
			tx.rollback();
		} finally {
			//사용한 리소스 객체 닫기
			em.close();
		}	
		
	}
	//insert
	private static void insert(EntityManagerFactory emf) {
		//EntityManager 생성
		EntityManager em = emf.createEntityManager();
			
		//1.Transaction 생성
		EntityTransaction tx = em.getTransaction();
		
		try {	
			//1.Transaction 시작
			tx.begin();
			
			//1.DB에 저장할 객체 생성
			Board board = new Board();
			board.setItile("JPA 제목");
			board.setWriter("관리자");
			board.setContent("JPA 글 등록 잘 됩니다.");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			
			//1.글 등록
			em.persist(board);
			
			//1.Transaction commit
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//Transaction rollback
			tx.rollback();
		} finally {
			//사용한 리소스 객체 닫기
			em.close();
		}		
		
	}
	//select
	private static void select(EntityManagerFactory emf) {
		//EntityManager 생성
		EntityManager em = emf.createEntityManager();
		
		try {
		//2.글 상세 조회
		Board searchBoard = em.find(Board.class, 1L);
		System.out.println("--->"+ searchBoard.toString());
		
		//2.
		List<Board> list = em.createQuery("select b from Board b", Board.class).getResultList();
		list.stream().forEach(System.out::println);
		
		@SuppressWarnings("unchecked")
		List<Board> list1 = em.createNativeQuery("select * from Board", Board.class).getResultList();
		list1.stream().forEach(System.out::println);
		 } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close(); 
        }
		
	}
}
