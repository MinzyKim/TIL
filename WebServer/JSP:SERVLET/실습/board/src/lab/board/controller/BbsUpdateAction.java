package lab.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lab.board.model.BbsDAO;
import lab.board.model.BbsVO;

/**
 * Servlet implementation class BbsUpdateAction
 */
@WebServlet("/update.do")
public class BbsUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BbsUpdateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		BbsDAO dao = new BbsDAO();
		BbsVO form = null;
		String page = null;
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null) {
		    out.print("<script>");
		 	out.print("alert(\"로그인 후 수정 할 수 있습니다. \");");
		 	out.print("location.href=\"./login.do\"");
		 	out.print("</script>");
	}else {
		page = request.getParameter("page"); //페이지 파라미터 못넘어오면 out.print로 넘어오는지 찍어보기
		//bbs_edit에서 hidden 으로 page 파라미터 안넘어와서 발생한 오류임.
		int bid=Integer.parseInt(request.getParameter("bid"));
		form = new BbsVO();
		form.setBid(bid);
		form.setSubject(request.getParameter("subject"));
		form.setContents(request.getParameter("contents"));
		
		if(dao.updateBbs(form)>0) {
			response.sendRedirect("./view.do?bid="+bid+"&page="+page);
		}
	}
}
}