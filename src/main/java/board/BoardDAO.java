package board;

import java.net.URLEncoder;
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
	
	public List allArticles(Map pagingMap) {
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
	public int maxArticleNO() {
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT  max(art_NO) from s_board ";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			if (rs.next())
				return (rs.getInt(1) + 1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int createArticle(ArticleVO article) {
		int articleNO = maxArticleNO();
		try {
			conn = dataFactory.getConnection();
			String query = "INSERT INTO s_board (art_No, title, art_ID, content, category, writeDate)"
					+ " VALUES (?, ? ,?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.setString(2, article.getTitle());
			pstmt.setString(3, article.getArt_ID());
			pstmt.setString(4, article.getContent());
			pstmt.setString(5, article.getCategory());
			pstmt.setDate(6, article.getWriteDate());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleNO;
	}
	
	public ArticleVO readArticle(int articleNO) {
		ArticleVO article = new ArticleVO();
		try {
			conn = dataFactory.getConnection();
			String query = "select art_No, title, art_ID, content, category, writeDate" + " from s_board"
					+ " where art_No=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			article.setArt_No(rs.getInt("art_No"));
			article.setTitle(rs.getString("title"));
			article.setArt_ID(rs.getString("art_ID"));
			article.setContent(rs.getString("content"));
			article.setCategory(rs.getString("category"));
			article.setWriteDate(rs.getDate("writeDate"));
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}
	
	public void updateArticle(ArticleVO article) {
		int articleNO = article.getArt_No();
		try {
			conn = dataFactory.getConnection();
			String query = "update s_board  set title=?,content=? where art_No=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContent());
			pstmt.setInt(3, article.getArt_No());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteArticle(int articleNO) {
		try {
			conn = dataFactory.getConnection();
			String query = "DELETE FROM s_board ";
			query += " WHERE art_No in (";
			query += "  SELECT art_No FROM  s_board ";
			query += " START WITH art_No = ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}