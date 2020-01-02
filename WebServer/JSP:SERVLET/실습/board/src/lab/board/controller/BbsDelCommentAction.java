package lab.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.board.model.BbsDAO;
import lab.board.model.BbsVO;
import lab.board.model.CommentVO;


@WebServlet("/delete_comment.do")
public class BbsDelCommentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public BbsDelCommentAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		BbsDAO dao = new BbsDAO();
		BbsVO form = null;
		CommentVO comment = new CommentVO();
		String page = null;
		System.out.println(page);
		page = request.getParameter("page");
		int bid = Integer.parseInt(request.getParameter("bid"));
		int cmid = Integer.parseInt(request.getParameter("cmid"));

		comment.setCmid(cmid);

		if(dao.deleteBbsComment(cmid) >0) {
			response.sendRedirect("./view.do?bid="+bid+"&page="+page);
			
		}else {
			out.print("<script>");
		 	out.print("alert(\"패스워드 오류!!\");");
		 	out.print("location.href=\"./view.do?bid="+bid+"&page="+page+"\";");
		 	out.print("</script>");
		}
	}
}
