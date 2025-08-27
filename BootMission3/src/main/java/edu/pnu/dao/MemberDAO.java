package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.MemberDTO;


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
	public MemberDTO getMemberById(int id) {
	    String query = "SELECT * FROM member WHERE id=?";
	    try (PreparedStatement psmt = con.prepareStatement(query)) {
	        psmt.setInt(1, id);
	        try (ResultSet rs = psmt.executeQuery()) {
	            if (rs.next()) {
	                return MemberDTO.builder()
	                    .id(rs.getInt("id"))
	                    .name(rs.getString("name"))
	                    .pass(rs.getString("pass"))
	                    .regidate(rs.getTimestamp("regidate"))
	                    .build();
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	//insert 등록
	public MemberDTO insertMember(MemberDTO member) {
	    String query = "INSERT INTO member (id, name, pass, regidate) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement psmt = con.prepareStatement(query)) {
	        psmt.setInt(1, member.getId());
	        psmt.setString(2, member.getName());
	        psmt.setString(3, member.getPass());
	        psmt.setTimestamp(4, new java.sql.Timestamp(member.getRegidate().getTime()));
	        psmt.executeUpdate();
	        return member;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	//put 전체 수정
	public MemberDTO updateMember(int id, MemberDTO member) {
	    String query = "UPDATE member SET name = ?, pass = ?, regidate = ? WHERE id = ?";
	    try (PreparedStatement psmt = con.prepareStatement(query)) {
	        psmt.setString(1, member.getName());
	        psmt.setString(2, member.getPass());
	        psmt.setTimestamp(3, new java.sql.Timestamp(member.getRegidate().getTime()));
	        psmt.setInt(4, id);
	        int result = psmt.executeUpdate();
	        return result > 0 ? member : null;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	//patch 일부수정
	public MemberDTO patchMember(int id, MemberDTO member) {
	    // 기존 회원 가져오기
	    MemberDTO existing = getMemberById(id);
	    if (existing == null) return null;

	    // 변경 값만 반영
	    if (member.getName() != null) existing.setName(member.getName());
	    if (member.getPass() != null) existing.setPass(member.getPass());
	    if (member.getRegidate() != null) existing.setRegidate(member.getRegidate());

	    return updateMember(id, existing); // 재사용
	}

	//delete 삭제
	public boolean deleteMember(int id) {
	    String query = "DELETE FROM member WHERE id = ?";
	    try (PreparedStatement psmt = con.prepareStatement(query)) {
	        psmt.setInt(1, id);
	        int result = psmt.executeUpdate();
	        return result > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}





	
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


