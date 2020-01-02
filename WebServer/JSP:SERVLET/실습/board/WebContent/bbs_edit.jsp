<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html><head><title>게시판 글 수정</title></head>
<link href="mystyle.css" rel="stylesheet" type="text/css">
<body><h3 id="header">게시판 글 수정</h3>
<div id='menu'>
<%@ include file="mymenu.jsp" %>
</div>
<div id="main">
<form method='post' action='update.do'>
 
 
<table width='100%'>
 
 
<tr><td>제목</td><td>
   <input type='text' name='subject' value='${article.subject}'>
</td></tr>
 
  <tr><td>이름</td><td>
	  <input type='text' name='writer' value='${article.writer}' readonly>
  </td></tr>
  <tr><td>암호</td><td>
    <input type='password' name='password' value='${article.password}' readonly>
  </td></tr>
  <tr><td>Email</td><td>
   <input type='text' name='email' value='${article.email}' readonly></td>
  </tr>
  
 
<tr><td>내용</td><td>
  <textarea cols='65' name='contents' 
            rows='20'>${article.contents}</textarea>
</td></tr>
<tr><td colspan='2' align='center'>
<input type='hidden' name='bid' value='${article.bid}'>
<input type='hidden' name='page' value='${page}'>
<input type='submit' value="글 수정 저장"> <input type='reset' value="글 수정 취소">
</td></tr>
</table>
</form>
</div>
</body>
</html>
