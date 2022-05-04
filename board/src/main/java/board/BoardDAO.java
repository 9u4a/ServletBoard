package board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;
	
	
	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List AllArticles(Map pagingMap) {
		List articlesList = new ArrayList();
		int section = (Integer)pagingMap.get("section");
		int pageNum = (Integer)pagingMap.get("pageNum");
		try {
			conn = dataFactory.getConnection();
			String query = "select ROWNUM as recNum,"
						 	+"art_No,"
						 	+"title,"
						 	+"art_ID,"
						 	+"writeDate,"
						 	+"category,"
						 	+"from s_board"
						 	+"ORDER SIBLINGS BY art_No DESC"
						 +"where recNum between(?-1)*100+(?-1)*10+1 and (?-1)*100+?*10";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, section);
			pstmt.setInt(2, pageNum);
			pstmt.setInt(3, section);
			pstmt.setInt(4, pageNum);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {			
				ArticleVO article = new ArticleVO();
				
				article.setArt_No(rs.getInt("art_No"));
				article.setArt_ID(rs.getString("art_ID"));
				article.setTitle(rs.getString("title"));
				article.setCategory(rs.getString("category"));
				article.setWriteDate(rs.getDate("writeDate"));
				
				articlesList.add(article);
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return articlesList;
	}
	
//	
//	public List CreateMember() {
//		
//	}
//	
//	public List ReadMember() {
//		
//	}
//	
//	public List UpdateMember() {
//		
//	}
//	
//	public List DeleteMember() {
//		
//	}
}
