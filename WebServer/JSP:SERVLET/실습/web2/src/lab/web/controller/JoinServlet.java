package lab.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.web.model.LoginDAO;
import lab.web.model.UserVO;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/Join")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("utf-8");
		response.sendRedirect("./member.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		response.setContentType("text/html;charset=utf-8");  
		PrintWriter out = response.getWriter();  
		request.setCharacterEncoding("utf-8");
		UserVO vo = new UserVO();
		LoginDAO dao = new LoginDAO();
		
		vo.setUserid(request.getParameter("userid"));
		vo.setUserpwd(request.getParameter("userpwd"));
		vo.setUsername(request.getParameter("username"));
		vo.setPhone(request.getParameter("phone"));
		vo.setEmail(request.getParameter("email"));
		vo.setAddress(request.getParameter("address"));
		vo.setJob(request.getParameter("job"));
	
		int checkflag=dao.joinProc(vo);

		ServletContext sc=request.getServletContext();
		RequestDispatcher rd = null; // 반응에 따라 띄울 창
		if(checkflag>0){
			rd=sc.getRequestDispatcher("/memberOk3.jsp");
			request.setAttribute("vo",vo);
			rd.forward(request, response);	
		}else {
			out.println("<script>");  
			out.println("alert('회원가입에 실패했습니다.')");  
			out.println("location.href=\"./member.jsp\"");
			out.println("</script>");
		}
	}
}
