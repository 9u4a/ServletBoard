package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MemberVO findMember(String findID) {
		MemberVO findMem = null;
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM S_USER WHERE user_ID=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, findID);
			ResultSet rs = pstmt.executeQuery();
			String id = rs.getString("user_ID");
			String pwd = rs.getString("user_Pw");
			String name = rs.getString("user_Name");
			String email = rs.getString("user_Email");
			findMem = new MemberVO(id, pwd, name, email);
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return findMem;
	}
	
	public void createMember(MemberVO memberVO) {
		try {
			conn = dataFactory.getConnection();
			String query = "INSERT INTO S_USER (user_ID, user_Pw, user_Name, user_Email)"
					+ " VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberVO.getUser_ID());
			pstmt.setString(2, memberVO.getUser_Pw());
			pstmt.setString(3, memberVO.getUser_Name());
			pstmt.setString(4, memberVO.getUser_Email());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateMember(MemberVO memberVO) {
		try {
			conn = dataFactory.getConnection();
			String query = "UPDATE S_USER SET user_Pw=?,user_Name=?,user_Email=?  WHERE user_ID=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberVO.getUser_Pw());
			pstmt.setString(2, memberVO.getUser_Name());
			pstmt.setString(3, memberVO.getUser_Email());
			pstmt.setString(4, memberVO.getUser_ID());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteMember(String id) {
		try {
			conn = dataFactory.getConnection();
			String query = "DELETE FROM S_USER WHERE user_ID=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

