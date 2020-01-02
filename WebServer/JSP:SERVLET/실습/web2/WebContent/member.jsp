<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <style>
   h3 { width: 740px; 
       text-align : center; }
      
  </style>
  <title>회원 가입</title>
 </head>
 <body>
 <h3> 회원가입 정보 입력</h3>
 <form name="write_form_member" method="post" action="Join">
   <table width="740" style="padding:5px 0 5px 0; ">
      <tr height="2" bgcolor="#FFC8C3"><td colspan="2"></td></tr>
      <tr>
         <th> 아이디 </th>
         <td><input type="text" name="userid"></td>
      </tr>
      <tr>
         <th>이 름</th>
         <td><input type="text" name="username">  </td>
       </tr>        
       <tr>
         <th>비밀번호</th>
         <td><input type="password" name="userpwd"> 영문/숫자포함 6자 이상</td>
       </tr>   
      
        <tr>
        </td>
           <th>연락처</th>
           <td><input type='text' name='phone'></td>
        </tr>
        <tr>
          <th>이메일</th>
          <td>
            <input type='text' name="email">@
            <input type='text' name="email_dns">
              <select name="emailaddr">
                 <option value="">직접입력</option>
                 <option value="daum.net">daum.net</option>
                 <option value="empal.com">empal.com</option>
                 <option value="gmail.com">gmail.com</option>
                 <option value="hanmail.net">hanmail.net</option>
                 <option value="msn.com">msn.com</option>
                 <option value="naver.com">naver.com</option>
                 <option value="nate.com">nate.com</option>
              </select>
            </td>
         </tr>
         
         <tr>
           <th>직업</th>
           <td>
           <select name='job' size='1'>
                 <option value=''>선택하세요</option>
                 <option value='39'>학생</option>
                 <option value='40'>컴퓨터/인터넷</option>
                 <option value='41'>언론</option>
                 <option value='42'>공무원</option>
                 <option value='43'>군인</option>
                 <option value='44'>서비스업</option>
                 <option value='45'>교육</option>
                 <option value='46'>금융/증권/보험업</option>
                 <option value='47'>유통업</option>
                 <option value='48'>예술</option>
                 <option value='49'>의료</option>
           </select>
          </td>
        </tr>
       <tr>
         <th>주소 </th>
           <td class="s">
               <input type="text" name="address"  >  
            </td>
         </tr>
         
 
           <tr height="2" bgcolor="#FFC8C3"><td colspan="2"></td></tr>
           <tr>
             <td colspan="2" align="center">
               <input type="submit" value="회원가입" >
               <input type="reset" value="취소">
            </td>
           </tr>
           </table>
          </td>
          </tr>
          </form>
 </body>
</html>
 