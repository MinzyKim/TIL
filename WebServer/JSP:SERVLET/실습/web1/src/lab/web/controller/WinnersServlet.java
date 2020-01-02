package lab.web.controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WinnersServlet
 */
@WebServlet("/WinnersServlet")
public class WinnersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int arr[]=new int[5];
		Random random=new Random();
		for(int cnt=0;cnt<arr.length;cnt++) 
			arr[cnt]=random.nextInt(100000000);
		request.setAttribute("ARR", arr);
		RequestDispatcher rd = request.getRequestDispatcher("/lecture/Winners.jsp");
		
		rd.forward(request, response);
	}
}
