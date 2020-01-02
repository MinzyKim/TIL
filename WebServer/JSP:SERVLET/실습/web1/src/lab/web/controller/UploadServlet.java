package lab.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UploadServlet")
@MultipartConfig (location = "C:/uploadtest",
maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head><title>파일 업로드 테스트용 HTML</title>");
		out.print("</head>\r\n" + 
				"<body>");
		out.print("");
		out.print("");
	}

}
