<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>login</title>
</head>
<body>
  <h3>MVC구조 login </h3>
    <table border="1">
      <tr><td colspan="2" align="center"><font size=15><b>우리회사</b></font></td></tr>
      <tr>
         <td><form action="Login" method="post">
               <div id="confirmed">
                 <table>
                    <tr>
                      <td>아이디</td>
                      <td><input type="text" id="userid" name="userid" size="15" maxlength="12"/></td>
                    </tr>
                    <tr>
                      <td>비밀번호</td>
                      <td><input type="password" id="userpwd" name="userpwd" size="15" maxlength="12"/></td>
                    </tr>
                    <tr><td colspan="2" align="center">
                        <input type="submit" id="login" value="로그인"/></td>
                    </tr>
                </table>
              </div>
             </form>
         </td>
         <td width="400"><img src="./dragon2.jpg"></td>
      </tr>
      <tr><td colspan="2" align="center">찾아오시는길 |회사소개|정보보호정책</td></tr>
    </table>

</body>
</html>