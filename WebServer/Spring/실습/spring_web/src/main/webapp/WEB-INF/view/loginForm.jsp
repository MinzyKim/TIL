<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<center>
<h3> 로그인</h3>
<hr>

<form method='post' action='./login.do'  >
 <table >
     <tr>
       <td style="width:100;text-align:left;">아이디</td>
       <td><input type="text" id="userid" name="userid" size="20" maxlength="15"/></td>
      </tr>
     <tr>
      <td style="width:100;text-align:left;">비밀번호</td>
        <td><input type="password" id="userpwd" name="userpwd" size="20" maxlength="15"/></td>
        </tr>
        <tr><td colspan="2" align="center">
        <input type="submit" id="login" value="로그인" />&nbsp;&nbsp;
        <a href="./add.do"  ><span style="font-size:small">회원가입</span></a>&nbsp;&nbsp;<a href=""><span style="font-size:small;">아이디/비밀번호 찾기</span></a></td>
        </tr>
      </table>
</form>
</center>


</body>
</html>