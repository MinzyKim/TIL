package lab.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import lab.board.model.BbsDAO;
import lab.board.model.BbsVO;
import lab.board.model.FileInfoVO;


@WebServlet("/write.do")
public class BbsWriteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public BbsWriteAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		if(session.getAttribute("user")==null) {
			    out.print("<script>");
			 	out.print("alert(\"로그인 후 글 작성 가능 합니다. \");");
			 	out.print("location.href=\"./login.do\"");
			 	out.print("</script>");
		}else {
		response.sendRedirect("./bbs_write.jsp");	
	}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		BbsDAO dao = new BbsDAO();
		BbsVO form = null;
		String page = null;
		page = request.getParameter("page");
		form = new BbsVO();
		String fileFlag="N";
		if(request.getParameter("upload")!=null) {
			fileFlag="Y";
			//upload된 파일 저장 처리
		}
		form.setFileYN(fileFlag);
		form.setWriter(request.getParameter("writer"));
		form.setPassword(request.getParameter("password"));
		form.setSubject(request.getParameter("subject"));
		form.setEmail(request.getParameter("email"));
		form.setContents(request.getParameter("contents"));
		form.setIp(request.getRemoteAddr());
		if(dao.insertBbs(form) > 0) {
			response.sendRedirect("./list.do");
			
		}
	}
	
//	public void uploadProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		BbsDAO dao = new BbsDAO();
//		String path = "c:/uploadtest";
//		File isDir = new File(path);
//		String saveFile="";
//		if(!isDir.isDirectory()) {
//			isDir.mkdirs();
//		}
//		Collection<Part> parts = request.getParts();
//		for(Part part : parts) {
//			if(part.getContentType() != null) {
//				String fileName = part.getSubmittedFileName();
//				if(fileName!=null) {
//					saveFile = fileName.substring(0, fileName.lastIndexOf(".")+
//							"_"+System.currentTimeMillis()
//							+ fileName.substring(fileName.lastIndexOf(",")));
//					part.write(saveFile);
//					System.out.println("file 저장");
//					FileInfoVO file = new FileInfoVO();
//					//file.setRbid( );
//					file.setFilename(saveFile);
//					String fileType=fileName.substring(fileName.lastIndexOf("."));
//				
//					file.setFiletype(fileType);
//					//dao.insertUploadFile(file);
//				}
//			}
//		}
//	}
}
