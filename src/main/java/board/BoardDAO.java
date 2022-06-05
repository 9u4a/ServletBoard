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
			String query = "select * from ("
							+"select ROWNUM as recNum,"+"LVL,"
						 	+"art_No,"
							+"par_No"
						 	+"title,"
						 	+"art_ID,"
						 	+"writeDate,"
						 		+ " from ( select LEVEL as LVL,"
						 		+"art_No,"
								+"par_No"
							 	+"title,"
							 	+"art_ID,"
							 	+"writeDate,"
							 	+"from s_board"
							 	+" START WITH  par_No=0"
							    +" CONNECT BY PRIOR art_No = par_NO"
							 	+"ORDER SIBLINGS BY art_No DESC)"
						 + " )"
						 +" where recNum between(?-1)*100+(?-1)*10+1 and (?-1)*100+?*10";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, section);
			pstmt.setInt(2, pageNum);
			pstmt.setInt(3, section);
			pstmt.setInt(4, pageNum);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {			
				ArticleVO article = new ArticleVO();
				
				article.setLevel(rs.getInt("lvl"));
				article.setArt_No(rs.getInt("art_No"));
				article.setPar_No(rs.getInt("par_No"));
				article.setArt_ID(rs.getString("art_ID"));
				article.setTitle(rs.getString("title"));
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
	
	public List allArticles() {
		List articlesList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT LEVEL,art_No,par_No,title,content,art_ID,writeDate" + " from s_board"
					+ " START WITH  par_No=0" + " CONNECT BY PRIOR art_No=par_No"
					+ " ORDER SIBLINGS BY art_No DESC";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleVO article = new ArticleVO();
				
				article.setLevel(rs.getInt("level"));
				article.setArt_No(rs.getInt("art_No"));
				article.setPar_No(rs.getInt("par_No"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				article.setArt_ID(rs.getString("art_ID"));
				article.setWriteDate(rs.getDate("writeDate"));
				
				articlesList.add(article);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}
	public int maxart_No() {
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT  max(art_No) from s_board ";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			if (rs.next())
				return (rs.getInt(1) + 1);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int createArticle(ArticleVO article) {
		int art_No = maxart_No();
		try {
			conn = dataFactory.getConnection();
			String query = "INSERT INTO s_board (art_No, par_No, title, art_ID, content, image)"
					+ " VALUES (?, ? ,?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, art_No);
			pstmt.setInt(2, article.getPar_No());
			pstmt.setString(3, article.getTitle());
			pstmt.setString(4, article.getArt_ID());
			pstmt.setString(5, article.getContent());
			pstmt.setString(6, article.getImage());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return art_No;
	}
	
	public ArticleVO readArticle(int art_No) {
		ArticleVO article = new ArticleVO();
		try {
			conn = dataFactory.getConnection();
			String query = "select art_No, par_No, title, content,NVL(image, 'null') as image, art_ID, writeDate" + " from s_board"
					+ " where art_No=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, art_No);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String image = URLEncoder.encode(rs.getString("image"), "UTF-8"); 
			if(image.equals("null")) {
				image = null;
			}
			
			article.setArt_No(rs.getInt("art_No"));
			article.setPar_No(rs.getInt("par_No"));
			article.setTitle(rs.getString("title"));
			article.setContent(rs.getString("content"));
			article.setImage(image);
			article.setArt_ID(rs.getString("art_ID"));
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
		int art_No = article.getArt_No();
		String image = article.getImage();
		try {
			conn = dataFactory.getConnection();
			String query = "update s_board  set title=?,content=?";
			if (image != null && image.length() != 0) {
				query += ",image=?";
			}
			query += " where art_No=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContent());
			if (image != null && image.length() != 0) {
				pstmt.setString(3, image);
				pstmt.setInt(4, art_No);
			} else {
				pstmt.setInt(3, art_No);
			}
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteArticle(int art_No) {
		try {
			conn = dataFactory.getConnection();
			String query = "DELETE FROM s_board ";
			query += " WHERE art_No in (";
			query += "  SELECT art_No FROM  s_board ";
			query += " START WITH art_No = ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, art_No);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<Integer> selectRemovedArticles(int art_No) {
		List<Integer> art_NoList = new ArrayList<Integer>();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT art_No FROM  s_board  ";
			query += " START WITH art_NO = ?";
			query += " CONNECT BY PRIOR  art_NO = par_NO";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, art_No);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				art_No = rs.getInt("art_NO");
				art_NoList.add(art_No);
			}
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return art_NoList;
	}

	public int selectTotArticles() {
		try {
			conn = dataFactory.getConnection();
			String query = "select count(art_No) from s_board ";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				return (rs.getInt(1));
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}