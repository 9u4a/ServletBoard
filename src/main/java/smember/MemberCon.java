package smember;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/member/*")
public class MemberCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO memberDAO;


	public void init() throws ServletException {
		memberDAO = new MemberDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String nextPage= null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		
		if(action == null || action.equals("main")) {
			nextPage = "/view/main.jsp";
		} else if(action.equals("login.do")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			int success = 0;
			success = memberDAO.loginMember(memberVO);
			if(success == 1) {
				request.setAttribute("msg", "loginSuccess");
			} else {
				request.setAttribute("msg", "loginFalse");
			}
			nextPage = "/view/main.jsp";
		
		} else if(action.equals("loginForm.do")) {
			nextPage = "/view/login.jsp";
		
		} else if(action.equals("/createMemberForm.do")) {
			nextPage = "/view/createMember.jsp";
		
		} else if(action.equals("/createMember.do")) {
			String id=request.getParameter("id");
		    String pwd=request.getParameter("pwd");
		    String name= request.getParameter("name");
	        String email= request.getParameter("email");
		    MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.createMember(memberVO);
			request.setAttribute("msg", "createMember");
			nextPage = "/view/main.jsp";
			
			
		} else if(action.equals("/updateMemberForm.do")) {
			String id = request.getParameter("id");
			MemberVO findMem = memberDAO.findMember(id);
			request.setAttribute("findMem", findMem);
			nextPage = "/view/updateMember.jsp";
			
		} else if(action.equals("/updateMember.do")) {
			String id=request.getParameter("id");
		    String pwd=request.getParameter("pwd");
		    String name= request.getParameter("name");
	        String email= request.getParameter("email");
		    MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.createMember(memberVO);
			request.setAttribute("msg", "updateMember");
			nextPage = "/view/main.jsp";
			
		} else if(action.equals("/deleteMember.do")) {
			String id = request.getParameter("id");
			memberDAO.deleteMember(id);
			request.setAttribute("msg", "deleteMember");
			nextPage = "/view/main.jsp";
		} else {
			nextPage = "/view/main.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
