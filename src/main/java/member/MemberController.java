package member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static String ARTICLE_IMAGE = "C:\\board\\image";
	private static final long serialVersionUID = 1L;
	MemberDAO memberDAO;


    public MemberController() {
        
    }


	public void init(ServletConfig config) throws ServletException {
		memberDAO = new MemberDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String nextPage= "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session;
		String action = request.getPathInfo();
		MemberVO memberVO = new MemberVO();
		
		try{
			if(action == null || action.equals("main")) {
				nextPage = "/board/main.jsp";
			}
			if(action.equals("/createMemberForm.do")) {
				nextPage = "/member/createMember.jsp";
			}
			if(action.equals("/createMember.do")) {
				memberVO.setUser_ID(request.getParameter("id"));
				memberVO.setUser_Pw(request.getParameter("pw"));
				memberVO.setUser_Name(request.getParameter("name"));
				memberVO.setUser_Email(request.getParameter("email"));
				memberDAO.createMember(memberVO);
				request.setAttribute("msg", "createMember");
				nextPage = "/member/main.jsp";
				
				
			} else if(action.equals("/updateMemberForm.do")) {
				String id = request.getParameter("id");
				MemberVO findMem = memberDAO.findMember(id);
				request.setAttribute("findMem", findMem);
				nextPage = "/member/updateMember.jsp";
				
			} else if(action.equals("/updateMember.do")) {
				memberVO.setUser_ID(request.getParameter("id"));
				memberVO.setUser_Pw(request.getParameter("pw"));
				memberVO.setUser_Name(request.getParameter("name"));
				memberVO.setUser_Email(request.getParameter("email"));
				memberDAO.createMember(memberVO);
				request.setAttribute("msg", "updateMember");
				nextPage = "/board/main.jsp";
				
			} else if(action.equals("/deleteMember.do")) {
				String id = request.getParameter("id");
				memberDAO.deleteMember(id);
				request.setAttribute("msg", "deleteMember");
				nextPage = "/board/main.jsp";
			} 
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
