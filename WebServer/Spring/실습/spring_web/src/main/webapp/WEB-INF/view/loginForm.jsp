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
<h3> �α���</h3>
<hr>

<form method='post' action='./login.do'  >
 <table >
     <tr>
       <td style="width:100;text-align:left;">���̵�</td>
       <td><input type="text" id="userid" name="userid" size="20" maxlength="15"/></td>
      </tr>
     <tr>
      <td style="width:100;text-align:left;">��й�ȣ</td>
        <td><input type="password" id="userpwd" name="userpwd" size="20" maxlength="15"/></td>
        </tr>
        <tr><td colspan="2" align="center">
        <input type="submit" id="login" value="�α���" />&nbsp;&nbsp;
        <a href="./add.do"  ><span style="font-size:small">ȸ������</span></a>&nbsp;&nbsp;<a href=""><span style="font-size:small;">���̵�/��й�ȣ ã��</span></a></td>
        </tr>
      </table>
</form>
</center>


</body>
</html>