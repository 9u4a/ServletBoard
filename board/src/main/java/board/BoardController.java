package board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static String ARTICLE_IMAGE = "C:\\board\\image";
	private static final long serialVersionUID = 1L;
	BoardService boardService;
	ArticleVO articleVO;
	MemberDAO memberDAO;
	

    public BoardController() {
        
    }


	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService();
		memberDAO = new MemberDAO();
		articleVO = new ArticleVO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String nextPage="";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session;
		String action = request.getPathInfo();
		
		try {
				//임시 createMember
			if(action.equals("/sign.do")) {
				MemberVO memberVO = new MemberVO();
				memberVO.setUser_ID(request.getParameter("id"));
				memberVO.setUser_Pw(request.getParameter("pw"));
				memberVO.setUser_Name(request.getParameter("name"));
				memberVO.setUser_Email(request.getParameter("email"));
				memberDAO.createMember(memberVO);
				request.setAttribute("createMember", "createMember");
				nextPage = "/board/main.do";
				
				//임시 updateMemberForm
			} else if(action.equals("/updateMemberForm.do")) {
				String id = request.getParameter("id");
				MemberVO findMem = memberDAO.findMember(id);
				request.setAttribute("findMem", findMem);
				
				//임시 updateMember
			} else if(action.equals("/updateMember.do")) {
				MemberVO memberVO = new MemberVO();
				memberVO.setUser_ID(request.getParameter("id"));
				memberVO.setUser_Pw(request.getParameter("pw"));
				memberVO.setUser_Name(request.getParameter("name"));
				memberVO.setUser_Email(request.getParameter("email"));
				memberDAO.createMember(memberVO);
				request.setAttribute("createMember", "createMember");
				nextPage = "/board/main.do";
				
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
