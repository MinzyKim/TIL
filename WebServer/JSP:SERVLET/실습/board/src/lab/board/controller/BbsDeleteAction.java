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


@WebServlet("/delete.do")
public class BbsDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public BbsDeleteAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		BbsDAO dao = new BbsDAO();
		BbsVO form = null;
		String page = null;
		page = request.getParameter("page");
		int bid = Integer.parseInt(request.getParameter("bid"));
		String password = request.getParameter("password");
		if(password.equals(dao.getBbsPassword(bid))) {
			if(dao.deleteBbs(bid) > 0) {
				response.sendRedirect("./list.do?page="+page);
			}
		}else {
			out.print("<script>");
		 	out.print("alert(\"패스워드 오류!!\");");
		 	out.print("location.href=\"./view.do?bid="+bid+"&page="+page+"\";");
		 	out.print("</script>");
		}
	}
}
