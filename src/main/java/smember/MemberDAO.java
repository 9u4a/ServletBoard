package smember;

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
	public int loginMember(MemberVO memberVO) {
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT user_ID,user_Pw FROM s_member WHERE user_ID=? and user_Pw=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberVO.getUser_ID());
			pstmt.setString(2, memberVO.getUser_Pw());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String id = rs.getString("user_ID");
			String pwd = rs.getString("user_Pw");
			
			if ((id != null) && (pwd != null)) {
				
				return 1;
			}
			else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
	public MemberVO findMember(String findID) {
		MemberVO findMem = null;
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM s_member WHERE user_ID=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, findID);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
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
			String id = memberVO.getUser_ID();
			String pwd = memberVO.getUser_Pw();
			String name = memberVO.getUser_Name();
			String email = memberVO.getUser_Email();
			String query = "INSERT INTO s_member (user_ID, user_Pw, user_Name, user_Email)"
					+ " VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
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
			String id = memberVO.getUser_ID();
			String pwd = memberVO.getUser_Pw();
			String name = memberVO.getUser_Name();
			String email = memberVO.getUser_Email();
			String query = "UPDATE s_member SET user_Pw=?,user_Name=?,user_Email=?  WHERE user_ID=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
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
			String query = "DELETE s_member WHERE user_ID=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
