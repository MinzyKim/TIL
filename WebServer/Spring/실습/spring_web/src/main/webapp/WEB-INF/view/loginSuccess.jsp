<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȯ���մϴ�.</title>
</head>
<body>
<c:if test="${!empty authInfo}">
<font color="blue">${authInfo.userid}�� ȯ���մϴ�.</font><br>
<a href="<c:url value='/view.do?userid=${authInfo.userid}'/>">�� ���� ���� </a><Br>
<a href="<c:url value='/list.do'/>">�� ���� ����Ʈ</a><br>
<a href="<c:url value='/logout.do'/>">�α׾ƿ�</a><br>

��ȭ��ȣ :${user.phone }<Br>
�̸���: ${user.email}<Br>
�ּ�:${user.address }<Br>
����:${user.job }<Br>
</c:if>
</body>
</html>