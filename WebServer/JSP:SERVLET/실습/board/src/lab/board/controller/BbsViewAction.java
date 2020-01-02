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

import lab.board.model.BbsDAO;
import lab.board.model.BbsVO;

/**
 * Servlet implementation class BbsViewAction
 */
@WebServlet("/view.do")
public class BbsViewAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BbsViewAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if(session.getAttribute("user")==null) {
			    out.print("<script>");
			 	out.print("alert(\"로그인 후 글 내용 볼 수 있습니다. \");");
			 	out.print("location.href=\"./login.do\"");
			 	out.print("</script>");
		}else {
		doPost(request, response);
	}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		BbsDAO dao = new BbsDAO();
		ServletContext sc = getServletContext();
		RequestDispatcher rd = null;
		int num = Integer.parseInt(request.getParameter("bid"));
		String page = request.getParameter("page");
		BbsVO article = null;
		article = dao.getArticle(num);
		
		if(article!=null) {
			request.setAttribute("article", article);
			request.setAttribute("bid", new Integer(num));
			request.setAttribute("page", new Integer(page));
			rd=sc.getRequestDispatcher("/bbs_view.jsp");
			rd.forward(request, response);
		}else {
		 out.print("<script>");
		 	out.print("alert(\"글 내용 조회 실패\");");
		 	out.print("location.href=\"./list.do\"");
		 	out.print("</script>");
		 	
		}
		
	}

}
