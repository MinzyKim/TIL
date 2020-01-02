<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h3> send2.jsp </h3>
메시지 1: ${param.msg1}<br>
메시지 2: ${param["msg2"]}<br>
컨텍스트 파라미터 값 : <%= application.getInitParameter("db_driver") %>


</body>
</html>