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
�޽��� 1: ${param.msg1}<br>
�޽��� 2: ${param["msg2"]}<br>
���ؽ�Ʈ �Ķ���� �� : <%= application.getInitParameter("db_driver") %>


</body>
</html>