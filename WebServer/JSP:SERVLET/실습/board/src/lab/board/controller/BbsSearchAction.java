package lab.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.board.model.BbsDAO;
import lab.board.model.BbsVO;

/**
 * Servlet implementation class BbsListAction
 */
@WebServlet("/search.do")
public class BbsSearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int numPerBlock=10;
	public static int numPerPage=10;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BbsSearchAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		
		ServletContext sc = getServletContext();
		RequestDispatcher rd = null;
		BbsDAO dao = new BbsDAO();
		ArrayList<BbsVO> headers=null;
		String page=null, option=null, search=null;
		page=request.getParameter("pageNo");
		option=request.getParameter("searchKey");
		search=request.getParameter("searchWord");
		
		 System.out.println(page +option+" "+search);
	      int pageNo=1;
	      if(page==null) {
	         pageNo=dao.getPageCount(numPerPage);
	         headers=dao.searchBbs(pageNo, numPerPage, option, search);
	      }else {
	         pageNo=Integer.parseInt(page);
	         headers=dao.searchBbs(pageNo, numPerPage, option, search);
	      }
	      //System.out.println(headers.size());
	      Integer totalPage=new Integer(dao.getPageCount(numPerPage));
	      request.setAttribute("headers", headers);
	      request.setAttribute("pageNo", new Integer(pageNo));
	      request.setAttribute("totalPage", totalPage);
	      rd=sc.getRequestDispatcher("/bbs_list.jsp");
	      rd.forward(request, response);
	      
	   }

}