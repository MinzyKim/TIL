package lab.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.web.model.EmailDAO;
import lab.web.model.EmailVO;
import lab.web.model.LoginDAO;
import lab.web.model.UserVO;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/list.do")
public class ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int numPerBlock=10;
	public static int numPerPage=10;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		ServletContext sc = getServletContext();
		RequestDispatcher rd = null;
		EmailDAO dao = new EmailDAO();
		ArrayList<EmailVO> headers = null;
		String page = null;
		page = request.getParameter("page");	
		
		int pageNo=1;
		int pageCount=0;
		pageCount = (int) Math.ceil(dao.getTotalCount()/(double)numPerPage);
		pageCount=Math.max(pageCount, 1);
		if(page == null) {
			pageNo = pageCount;
			headers = dao.getList(pageNo,  numPerPage);
		}else {
			pageNo = Integer.parseInt(page);
			headers = dao.getList(pageNo, numPerPage);
		}
		request.setAttribute("headers", headers);
		request.setAttribute("pageNo", new Integer(pageNo));
		request.setAttribute("totalPage", new Integer(pageCount));
		
		rd=sc.getRequestDispatcher("/list-page.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
	}
	
	}
