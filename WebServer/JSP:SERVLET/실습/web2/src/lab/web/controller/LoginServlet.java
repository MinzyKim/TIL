package lab.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.web.model.LoginDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.sendRedirect("./login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				String uid = request.getParameter("userid");
				String upwd = request.getParameter("userpwd");
				LoginDAO dao = new LoginDAO();
				ServletContext sc = request.getServletContext();
				RequestDispatcher rd = null;
				if(dao.loginProc(uid, upwd)) {
					rd=sc.getRequestDispatcher("/loginSuccess.jsp");
					request.setAttribute("userid", uid);
					rd.forward(request, response);
				}else {
					rd=sc.getRequestDispatcher("/loginFail.jsp");
					rd.forward(request, response);
				}
	}

}
