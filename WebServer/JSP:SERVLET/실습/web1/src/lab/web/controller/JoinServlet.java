package lab.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/join")
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<html>");
			out.print("<head>\r\n" +  
					"<title>Insert title here</title>");
			out.print("<body>");
			out.print("<ul>");
			out.print("<li> userid:"+request.getParameter("userid")+"</li>");
			out.print("<li> password:"+request.getParameter("userpwd")+"</li>");
			out.print("<li> address:"+request.getParameter("address")+"</li>");
			String interest[] = request.getParameterValues("interest");
			out.print("<li> 관심사항 :");
			for(String inter : interest) {
				out.print(inter+", ");
			}
			out.print("</li>");
			out.print("</ul>");
			out.print("</body>");
			out.print("</html>");
			
			
			
	}
}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
