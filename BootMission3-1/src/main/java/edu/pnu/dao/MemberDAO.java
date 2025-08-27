package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberDTO;


@Component
@Repository
public class MemberDAO {
	private Connection con;
	
	public MemberDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/bootmission";
			String id = "musthave";
			String pwd = "tiger";
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(기본 생성자)");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
    }
	
	//DAO - Service - Controller
	// 전체 조회
	public List<MemberDTO> getAllMembers() {
	    List<MemberDTO> list = new ArrayList<>();
	    String query = "SELECT * FROM member";
	    
	    
	    try(PreparedStatement psmt = con.prepareStatement(query);
	        ResultSet rs = psmt.executeQuery()) {
	    	
	        while (rs.next()) {
	            MemberDTO dto = new MemberDTO();
	            dto.setId(rs.getInt("id"));
	            dto.setPass(rs.getString("pass"));
	            dto.setName(rs.getString("name"));
	            dto.setRegidate(rs.getTimestamp("regidate"));
	            list.add(dto);
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }// 자동으로 psmt.close()와 rs.close() 호출됨

	    return list;
	}
	
	// 일부 조회
	

	
	public static void main(String[] args) {
	    MemberDAO dao = new MemberDAO(); // 기본 생성자 호출 (DB 연결됨)

	    List<MemberDTO> members = dao.getAllMembers(); // 전체 회원 목록 조회

	    // 결과 출력
	    for (MemberDTO m : members) {
	        System.out.println("ID: " + m.getId());
	        System.out.println("Name: " + m.getName());
	        System.out.println("Pass: " + m.getPass());
	        System.out.println("Regidate: " + m.getRegidate());
	        System.out.println("----------------------");
	    }
	}
	
}


