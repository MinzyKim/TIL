<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α��� ����</title>
</head>
<body>

<c:if test="${empty authInfo}">
<font color="red">${authInfo.userid}�� ���̵� �������� �ʰų�, ��й�ȣ �����Դϴ�.</font><br>

<a href="<c:url value='/login.do'/>">�α���</a><br>
<a href="<c:url value='/add.do'/>">ȸ������</a><br>
</c:if>
</body>
</html>