<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>환영합니다.</title>
</head>
<body>
<c:if test="${!empty authInfo}">
<font color="blue">${authInfo.userid}님 환영합니다.</font><br>
<a href="<c:url value='/view.do?userid=${authInfo.userid}'/>">고객 정보 수정 </a><Br>
<a href="<c:url value='/list.do'/>">고객 정보 리스트</a><br>
<a href="<c:url value='/logout.do'/>">로그아웃</a><br>

전화번호 :${user.phone }<Br>
이메일: ${user.email}<Br>
주소:${user.address }<Br>
업무:${user.job }<Br>
</c:if>
</body>
</html>