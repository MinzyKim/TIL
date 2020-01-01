<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta   charset="utf-8">
<TITLE>개인 정보 입력 화면</TITLE>
<link rel=stylesheet href="../css/user.css" type="text/css">
<script type="text/javascript">
function userCreate(){		
	f.action="./add.do";
	f.submit();	
}
function userList(){
	f.action="./list.do";
	f.submit();
}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<table width=780 border=0 cellpadding=0 cellspacing=0>
	<tr>
	  <td width="20"></td>
	  <td>
  <!--contents-->
	  <table width=590 border=0 cellpadding=0 cellspacing=0>
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>사용자 관리 - 개인 정보 입력</b></td>
		  </tr>
	  </table>  
	  <br>
	  
	  
 <form:errors path="user" />
 
	  <!-- write Form  -->
	  <form name="f" method="post" action="">
	  
	  <table border="0" cellpadding="0" cellspacing="1" width="590" bgcolor="BBBBBB">
		  <tr>
			<td width=100 align=center bgcolor="E6ECDE" height="22">사용자 아이디</td>
			<td width=490 bgcolor="ffffff" style="padding-left:20">
				<input type="text" style="width:150" name="userid" value="">
				<font color="red"><form:errors path="user.userid"/></font>
			</td>
		  </tr>
		  <tr>
			<td width=100 align=center bgcolor="E6ECDE" height="22">비밀번호</td>
			<td width=490 bgcolor="ffffff" style="padding-left:20">
				<input type="password" style="width:150" name="userpwd" value="">
				 <font color="red"><form:errors path="user.userpwd"/></font>
			</td>
		  </tr>
		   
		  <tr>
			<td width=100 align=center bgcolor="E6ECDE" height="22">이름</td>
			<td width=490 bgcolor="ffffff" style="padding-left:20">
				<input type="text" style="width:200" name="username" value="">
		 
			</td><font color="red"><form:errors path="user.username"/></font>
		  </tr>
		  
		  <tr>
			<td width=100 align=center bgcolor="E6ECDE" height="22">이메일 주소</td>
			<td width=490 bgcolor="ffffff" style="padding-left:20">
				<input type="text" style="width:340px" name="email" value="">
			</td>
		  </tr>		
		  <tr>
			<td width=100 align=center bgcolor="E6ECDE" height="22">전화 번호</td>
			<td width=490 bgcolor="ffffff" style="padding-left:20">
				<input type="text" style="width:150px" name="phone" value="">
			</td>
		  </tr>		
		  <tr>
			<td width=100 align=center bgcolor="E6ECDE" height="22">주    소</td>
			<td width=490 bgcolor="ffffff" style="padding-left:20">
				<input type="text" style="width:340px" name="address" value="">
			</td>
		  </tr>		
		   <tr>
			<td width=100 align=center bgcolor="E6ECDE" height="22">직    업</td>
			<td width=490 bgcolor="ffffff" style="padding-left:20">
				<input type="text" style="width:340px" name="job" value="">
			</td>
		  </tr>		
	  </table>
	  
	  <br>
	  
	  <table width=590 border=0 cellpadding=0 cellspacing=0>
		  <tr>
			<td align=center>
			<input type="button" value="회원 가입" onClick="userCreate()"> &nbsp;
			<input type="button" value="목록" onClick="userList()">
			</td>
		  </tr>
	  </table>

	  </td>
	</tr>
</table>  
 </form>
</body>
</html>