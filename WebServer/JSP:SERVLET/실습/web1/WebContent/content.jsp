<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h3>include 지시자 예제</h3>
<table border="1">
<tr>
<td colspan="2" align="center">
<%@ include file="header.jsp" %>
</td></tr>
<tr>
<td>
<%@ include file="menu1.jsp" %>
</td>
<td width="400"><img src="./dragon2.jpg"></td>
</tr>
<tr>
<td colspan="2" align="center">
<%@ include file="footer.jsp" %>
</td></tr>
</table>
</body>
</html>