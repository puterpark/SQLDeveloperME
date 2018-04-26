package com.kosmo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {

	final String URL = "jdbc:oracle:thin:@localhost:1521:XE";	//자기 주소
	final String USER = "custom";								//DB 사용자
	final String PASSWORD = "1111";								//비밀번호

	public Connection dbConn() {

		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //JDBC 드라이버 로드 (예외처리 필요)
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD); //DB연결 (예외처리 필요)
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
	
	//select용 close()
	public void dbClose(ResultSet rs, Statement stmt, Connection conn) { //연결 해제
		
		try {
			if(rs != null) rs.close();		//코딩한 순서대로 연결 해제
			if(stmt != null) stmt.close();	//예외처리 필요
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//select용 PreparedStatement close()
	public void dbClose(ResultSet rs, PreparedStatement pstmt, Connection conn) { //연결 해제
		
		try {
			if(rs != null) rs.close();		//코딩한 순서대로 연결 해제
			if(pstmt != null) pstmt.close();	//예외처리 필요
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//insert/update/delete용 close()
	public void dbClose(Statement stmt, Connection conn) { //연결 해제
		
		try {
			if(stmt != null) stmt.close();	//예외처리 필요
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//insert/update/delete용 PreparedStateement close()
	public void dbClose(PreparedStatement pstmt, Connection conn) { //연결 해제
		
		try {
			if(pstmt != null) pstmt.close();	//예외처리 필요
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

//	public static void main(String[] args) {
//
//		DBConnect c = new DBConnect();
//		c.dbConn();
//
//	}

}
