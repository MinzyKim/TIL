<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=utf-8"  %>
<%@ page import="lab.web.controller.ListAction" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%!
	int numPerPage = ListAction.numPerPage;  //페이지 그룹내에 페이지 개수
	int numPerBlock = ListAction.numPerBlock;  //페이지내에 글 개수
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style>
table  {};
</style>
<title>list-page.jsp</title>
</head>
<body>
<h3> Email  등록 목록 </h3>
<table border=1 cellpadding=5 cellspacing=0>
<tr><td width=50>번호</td><td width=125>이름</td><td width=125>email</td></tr>

 
<!-- 이메일 목록 -->
 
<c:forEach var="vo" items="${headers}">
<tr><td width=50>${vo.no}</td>
<td >${vo.username }</td>
<td >${vo.email }</td></tr>
</c:forEach>
 
</table>

<!-- 페이지 번호 -->
<div style="text-align:center">	
<%	
	Integer p = (Integer) request.getAttribute("pageNo");
	int mypage = p.intValue();
	//출력될 페이지가 속한 페이지 그룹 계산
    int currentBlock = (int)Math.ceil(mypage / (double)numPerBlock);
	
	//전체 페이지 개수
	Integer tp = (Integer) request.getAttribute("totalPage");
	double totalPage = tp.intValue();
	
	//전체 페이지 블록 개수
	int totalBlock = (int)Math.ceil(totalPage / numPerBlock);
	
	if(totalBlock > currentBlock) { 
		int togo = (currentBlock + 1) * numPerBlock;
		if(togo > totalPage)
			togo = (int) totalPage; %>
		<a href=./list.do?page=<%=togo%>> << </a>
<%	}
	//현재 페이지 그룹의 페이지 번호 리스트
	for(int i = numPerBlock; i > 0; i--) {
		int pn = numPerBlock * (currentBlock-1) + i;
		if(pn > totalPage)
			continue;
		if(pn == mypage) { %>
		<a href=./list.do?page=<%=pn%>>
		<span style="text-decoration:underline"><%=pn%></span></a>&nbsp;
<%		} else { %>
		<a href=./list.do?page=<%=pn%>><%=pn%></a>&nbsp		
<%		}
	}
	if(currentBlock > 1) { %>
		<a href=./list.do?page=<%= (currentBlock-1)*numPerBlock %>> >> </a>
<%	} %> </div> 
</div>
</body>
</html>