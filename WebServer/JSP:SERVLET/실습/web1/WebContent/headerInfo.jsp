<%@ page language="java" contentType="text/html; charset=EUC-KR"
errorPage="error.jsp"
import = "java.util.Enumeration"
    pageEncoding="EUC-KR"%>
<%
Enumeration<String> headerName = request.getHeaderNames();
while(headerName.hasMoreElements()){
	String name = headerName.nextElement();
	out.print("<li> "+name+":");
	Enumeration<String> values = request.getHeaders(name);
	while(values.hasMoreElements()){
		out.print( values.nextElement()+", ");
	}
	out.print("</li>");
}
%>
<li> ��û �޼ҵ� : <%= request.getMethod() %></li>
<li> ��û�� client�� IP : <%= request.getRemoteAddr() %></li>
<li> ContextPath : <%= request.getContextPath() %></li>
<li> RequestURI : <%= request.getRequestURI() %></li>
<li> RequestURL : <%= request.getRequestURL() %></li>
<li> ServletPath : <%= request.getServletPath() %></li>