package board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {
	BoardDAO boardDAO;

	public BoardService() {
		boardDAO = new BoardDAO();
	}

	public Map listArticles(Map<String, Integer> pagingMap) {
		Map articlesMap = new HashMap();
		List<ArticleVO> articlesList = boardDAO.allArticles(pagingMap);
		int totArticles = boardDAO.selectTotArticles();
		articlesMap.put("articlesList", articlesList);
		articlesMap.put("totArticles", totArticles);
		return articlesMap;
	}

	public List<ArticleVO> listArticles() {
		List<ArticleVO> articlesList = boardDAO.allArticles();
		return articlesList;
	}

	public int addArticle(ArticleVO article) {
		return boardDAO.createArticle(article);
	}

	public ArticleVO viewArticle(int art_No) {
		ArticleVO article = null;
		article = boardDAO.readArticle(art_No);
		return article;
	}

	public void modArticle(ArticleVO article) {
		boardDAO.updateArticle(article);
	}

	public List<Integer> removeArticle(int art_No) {
		List<Integer> art_NoList = boardDAO.selectRemovedArticles(art_No);
		boardDAO.deleteArticle(art_No);
		return art_NoList;
	}

	public int addReply(ArticleVO article) {
		return boardDAO.createArticle(article);
	}

}
