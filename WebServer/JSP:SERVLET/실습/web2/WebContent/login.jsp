<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>login</title>
</head>
<body>
  <h3>MVC���� login </h3>
    <table border="1">
      <tr><td colspan="2" align="center"><font size=15><b>�츮ȸ��</b></font></td></tr>
      <tr>
         <td><form action="Login" method="post">
               <div id="confirmed">
                 <table>
                    <tr>
                      <td>���̵�</td>
                      <td><input type="text" id="userid" name="userid" size="15" maxlength="12"/></td>
                    </tr>
                    <tr>
                      <td>��й�ȣ</td>
                      <td><input type="password" id="userpwd" name="userpwd" size="15" maxlength="12"/></td>
                    </tr>
                    <tr><td colspan="2" align="center">
                        <input type="submit" id="login" value="�α���"/></td>
                    </tr>
                </table>
              </div>
             </form>
         </td>
         <td width="400"><img src="./dragon2.jpg"></td>
      </tr>
      <tr><td colspan="2" align="center">ã�ƿ��ô±� |ȸ��Ұ�|������ȣ��å</td></tr>
    </table>

</body>
</html>