<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"
    import="java.util.ArrayList"
    import="lab.web.model.Product" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
    request.setCharacterEncoding("utf-8");
    Product p1= new Product("수박", 10000, "과일");
    Product p2= new Product("손선풍기", 5000, "전자");
    Product p3= new Product("브라보콘", 1500, "빙과");
    ArrayList<Product> alist=new ArrayList();
    alist.add(p1);
    alist.add(p2);
    alist.add(p3);
    request.setAttribute("products", alist);
    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>jstlTest.jsp</title>
</head>
<body>
<c:set var ="username" value="korea" scope="request" />
<c:if test="${username != null}">
username 변수 값 :<c:out value="${username}"/><br>
</c:if>

<c:remove var="username" />
<c:out value="${username}"/> <!--  null pointer exception 발생 안시켜줘 -->
<c:if test="${username == null}">
<c:out value="username 변수가 삭제되었습니다."/><br>
</c:if>

<c:set var="jumsu" value="${param.jumsu}" scope="request" />
<c:out value="${jumsu+=\"점은\"}" />
<c:choose>
<c:when test="${jumsu>=90}">
	<c:out value="A" />
</c:when>
<c:when test="${jumsu>=80}">
	<c:out value="B" />
</c:when>
<c:when test="${jumsu>=70}">
	<c:out value="C" />
</c:when>
<c:when test="${jumsu>=60}">
	<c:out value="B" />
</c:when>
<c:otherwise>
<c:out value="F"/>
</c:otherwise>
</c:choose>

#forEach 태그를 사용한 홀수 출력<br>
<c:forEach var="count" begin="1" end="10" step="2">
 ${count }<br>
</c:forEach>

# 상품 정보 리스트<br>
<table border="1px solid black">
<tr><th>상품명</th><th>가격</th><th>분류</th></tr>
<c:forEach var="product" items="${products}">
<tr><td>${product.name }</td><td>${product.price}</td><td>${product.category}</td></tr>
</c:forEach>
</table>
</body>
</html>