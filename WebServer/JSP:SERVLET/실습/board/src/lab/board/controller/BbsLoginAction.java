package lab.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lab.board.model.UserMgrDAO;
import lab.board.model.UserVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class BbsLoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BbsLoginAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		ServletContext sc = getServletContext();
		response.sendRedirect("./login.jsp");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				String uid = request.getParameter("userid");
				String upwd = request.getParameter("userpwd");
				String page= request.getParameter("page");
				UserMgrDAO dao = new UserMgrDAO();
				UserVO user = null;
				ServletContext sc = request.getServletContext();
				RequestDispatcher rd = null;
				HttpSession session = null;
				user = dao.loginProc(uid, upwd);
				if(user!=null) {
				session = request.getSession();
				session.setAttribute("user", user);
				if(page!=null) {
					response.sendRedirect("./list.do?page="+page);
				}else {
					response.sendRedirect("./list.do");
				}
				}else {
					 out.print("<script>");
					 	out.print("alert(\"아이디가 존재하지 않거나 \n패스워드 오류\");");
					 	out.print("location.href=\"./login.do\";");
					 	out.print("</script>");
				}
	}
}
