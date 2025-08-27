package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class LogDAO {
	private Connection con;

	//db연결
	public LogDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/bootmission";
			String id = "musthave";
			String pwd = "tiger";
			con = DriverManager.getConnection(url, id, pwd);
			System.out.println("LogDAO DB 연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//로그남기기
	public void addLog(Map<String, Object> map) {
		String sql = "INSERT INTO dblog (method, sqlstring, success) VALUES (?, ?, ?)";
		try (PreparedStatement psmt = con.prepareStatement(sql)) {
			psmt.setString(1, (String) map.get("method"));
			psmt.setString(2, (String) map.get("sqlstring"));
			psmt.setBoolean(3, (Boolean) map.get("success"));
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
