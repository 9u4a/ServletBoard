package board;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;


@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static String ARTICLE_IMAGE_REPO = "C:\\board\\image";
	BoardService boardService;
	ArticleVO articleVO;

	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService();
		articleVO = new ArticleVO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session;
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			
			if (action==null){	
				String _section=request.getParameter("section");
				String _pageNum=request.getParameter("pageNum");
				int section = Integer.parseInt(((_section==null)? "1":_section) );
				int pageNum = Integer.parseInt(((_pageNum==null)? "1":_pageNum));
				Map<String, Integer> pagingMap = new HashMap<String, Integer>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				Map articlesMap=boardService.listArticles(pagingMap);
				articlesMap.put("section", section);
				articlesMap.put("pageNum", pageNum);
				request.setAttribute("articlesMap", articlesMap);
				nextPage = "/board/ listArticles.jsp";
				
				
			}else if(action.equals("/listArticles.do")){  		
				String _section=request.getParameter("section");
				String _pageNum=request.getParameter("pageNum");
				int section = Integer.parseInt(((_section==null)? "1":_section) );
				int pageNum = Integer.parseInt(((_pageNum==null)? "1":_pageNum));
				Map pagingMap=new HashMap();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				Map articlesMap=boardService.listArticles(pagingMap);
				articlesMap.put("section", section);
				articlesMap.put("pageNum", pageNum);
				request.setAttribute("articlesMap", articlesMap);
				nextPage = "/board/listArticles.jsp";
				
			} else if (action.equals("/articleForm.do")) {
				nextPage = "/board/articleForm.jsp";
				
			} else if (action.equals("/addArticle.do")) {
				int art_No = 0;
				Map<String, String> articleMap = upload(request, response);
				String art_ID = articleMap.get("art_ID");
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String image = articleMap.get("image");
				

				articleVO.setPar_No(0);
				articleVO.setArt_ID(art_ID);
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImage(image);
				art_No = boardService.addArticle(articleVO);
				if (image != null && image.length() != 0) {
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + image);
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + art_No);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('추가완료.');" + " location.href='" + request.getContextPath()
						+ "/board/listArticles.do';" + "</script>");
				return;
				
			} else if (action.equals("/viewArticle.do")) {
				String art_No = request.getParameter("art_No");
				articleVO = boardService.viewArticle(Integer.parseInt(art_No));
				request.setAttribute("article", articleVO);
				nextPage = "/board/viewArticle.jsp";
				
			} else if (action.equals("/modArticle.do")) {
				Map<String, String> articleMap = upload(request, response);
				int art_No = Integer.parseInt(articleMap.get("art_No"));
				articleVO.setArt_No(art_No);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String image = articleMap.get("image");
				articleVO.setPar_No(0);
//				articleVO.setArt_ID(art_ID);
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImage(image);
				boardService.modArticle(articleVO);
				if (image != null && image.length() != 0) {
					String originalFileName = articleMap.get("originalFileName");
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + image);
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + art_No);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					;
					File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + art_No + "\\" + originalFileName);
					oldFile.delete();
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('수정완료.');" + " location.href='" + request.getContextPath()
						+ "/board/viewArticle.do?art_No=" + art_No + "';" + "</script>");
				return;
				
			} else if (action.equals("/removeArticle.do")) {
				int art_No = Integer.parseInt(request.getParameter("art_No"));
				List<Integer> art_NoList = boardService.removeArticle(art_No);
				for (int _art_No : art_NoList) {
					File imgDir = new File(ARTICLE_IMAGE_REPO + "\\" + _art_No);
					if (imgDir.exists()) {
						FileUtils.deleteDirectory(imgDir);
					}
				}

				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('삭제완료.');" + " location.href='" + request.getContextPath()
						+ "/board/listArticles.do';" + "</script>");
				return;

			} else if (action.equals("/replyForm.do")) {
				int parentNO = Integer.parseInt(request.getParameter("parentNO"));
				session = request.getSession();
				session.setAttribute("parentNO", parentNO);
				nextPage = "/board/replyForm.jsp";
				
			} else if (action.equals("/addReply.do")) {
				session = request.getSession();
				int par_No = (Integer) session.getAttribute("par_No");
				// art_ID 받아와야함
				session.removeAttribute("par_No");
				Map<String, String> articleMap = upload(request, response);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String image = articleMap.get("image");
				articleVO.setPar_No(par_No);
//				articleVO.setArt_ID(art_ID);
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImage(image);
				int art_No = boardService.addReply(articleVO);
				if (image != null && image.length() != 0) {
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + image);
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + art_No);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('추가완료');" + " location.href='" + request.getContextPath()
						+ "/board/viewArticle.do?art_No="+art_No+"';" + "</script>");
				return;
			
			}else {
				nextPage = "/board/listArticles.jsp";
			}
			
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> articleMap = new HashMap<String, String>();
		String encoding = "utf-8";
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				if (fileItem.isFormField()) {
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				} else {
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						
						String fileName = fileItem.getName().substring(idx + 1);
						articleMap.put(fileItem.getFieldName(), fileName);  
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						fileItem.write(uploadFile);
					} 
				} 
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleMap;
	}

}
