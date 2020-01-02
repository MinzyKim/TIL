<%@page import="java.util.Vector"%> 
<%@ page contentType="text/html;charset=utf-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%!
	//int numPerPage = BbsListAction.numPerPage;
	//int numPerBlock = BbsListAction.numPerBlock;
	int numPerPage = 10;
	int numPerBlock = 10;
	 
%>
<html><head><title>게시판</title>
<script>
 
</script>
</head>
<link href="mystyle.css" rel="stylesheet" type="text/css">
<body>
<c:if test='${empty userid}'>
<div style='text-align:right'>
<a href="./login.do" style="font-size:small">로그인</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="./member.do" style="font-size:small">회원가입</a> &nbsp;&nbsp;&nbsp;&nbsp;
</div>
</c:if>

<c:if test='${not empty user}'>
<div style='text-align:right'>
${user.username}(${user.userid})님 로그인중 &nbsp;&nbsp;&nbsp;&nbsp;
<a href="">회원 정보 수정</a>
</div>
</c:if>


<body>
<h3 id="header">게시판</h3>

<div id='menu'>
<%@ include file="mymenu.jsp" %>
</div>

<div id="main">
<table width=100%>
	<tr><th>번호</th><th>제목</th><th>작성자</th>
		<th>작성일</th><th>조회수</th></tr>
<c:forEach var="row" items="${headers}">
	<tr><td colspan=5 height=1 background=./image/dotline.gif></td></tr>
	<tr><td>${row.bid} </td><td>		 
		<a href="./view.do?bid=${row.bid}&page=${pageNo}">${row.subject}</a>
		</td>
		<td>${row.writer}</td>
		<td>${row.idate}</td>
		<td style='text-align:right'>${row.rcount}</td>
		<!-- 답글 -->
 
</c:forEach>
<tr><td colspan=5 height=1 background=./image/dotline.gif></td></tr>
</table>
<div style='text-align:right'><br><br>
	<a href=./write.do>글작성</a>
</div>
<div style='text-align:right'><br><br>    
	<a href=./write.do><input type="button" value="글작성"></a>	 
</div>
<div style='text-align:center'> 
	 <form method='post' action='./search.do'  ">
	 <input type="hidden" name="page" value="${pageNo}">
	 <select name='searchKey'>
   <option value='' selected>&nbsp;&nbsp;&nbsp;-- 선택 --&nbsp;&nbsp;&nbsp;</option>
   <option value='writer'>작성자</option>
   <option value='subject'>제목</option>   
   </select>
   <input type="search" name="searchWord" >
   <input type="submit" value="검색">
	 </form>
</div>

<!-- 페이지 번호 -->
	<div style="text-align:center">	
<%	
	Integer p = (Integer) request.getAttribute("pageNo");
	int mypage = p.intValue();
    int currentBlock = (int)Math.ceil(mypage / (double)numPerBlock);
	Integer tp = (Integer) request.getAttribute("totalPage");
	double totalPage = tp.intValue();
	int totalBlock = (int)Math.ceil(totalPage / numPerBlock);
	if(totalBlock > currentBlock) { 
		int togo = (currentBlock + 1) * numPerBlock;
		if(togo > totalPage)
			togo = (int) totalPage; %>
		<a href=./list.do?page=<%=togo%>> << </a>
<%	}
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