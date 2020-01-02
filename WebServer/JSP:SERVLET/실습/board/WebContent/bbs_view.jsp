<%@ page contentType="text/html;charset=utf-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<html><head><title>게시판 읽기</title></head>
<link href="mystyle.css" rel="stylesheet" type="text/css">
<script>
  function show(cmd, url) {
	var d = document.getElementById('enter');
	d.style.display = 'block';
	var form = document.forms.my;	
	form.attributes.action.value = url;
	form.action.value = cmd;
	if(cmd == 'modify') {
		form.submit.value = '글 수정';
	} else if(cmd == 'delete') {
		form.submit.value = '글 삭제';
	}
  }
  
  function hide() {
	var d = document.getElementById('enter');
	d.style.display = 'none';  
  }
  
  var init = false;
  function edit() {
  	var d = document.getElementById('comment');
	if(init == false) {
		d.contents.value='';
		init = true;
	}
  }
  
  function vote() {
  	var win = open('vote.html','w','width=200,height=200');
  }
  
  function mydelete(id) {
  	var d = document.getElementById(id);
	d.style.display = 'block';
	var bttn = 'b' + id;
	var b = document.getElementById(bttn);
	b.style.display = 'none';
  }
</script>
<body>
<c:if test='${not empty user}'>
<div style='text-align:right; font-size:small;'>
${user.username}(${user.userid})님 로그인중 &nbsp;&nbsp;&nbsp;&nbsp;
<a href="" font-size:small;>회원 정보 수정</a>&nbsp;&nbsp;&nbsp;
</div>
</c:if>

<h1>${pageNo}</h1>
<h3 id="header">글 내용 읽기 (게시판)</h3>
<div id='menu'>
<%@ include file="mymenu.jsp" %>
</div>
<div id="main">
<table width=100%> 
<!--게시물-->
	<tr><td>
	<b>제목 : ${article.subject} </b><br>
	작성자 : <a href=mailto:${article.email}>${article.writer}</a> 
			 &nbsp;<span style='font-size : 80%'>
			 <a href="mailto:"+${article.email}>${article.email}</a>
			 </span> <br>
	작성일 : ${article.idate}<br>
	조회수 : ${article.rcount} &nbsp; &nbsp;  
	추천수 : ${article.vcount} &nbsp;
	<span style='font-size:70%'>	
	<a href=./vote.do?bid=${article.bid} 
		onClick='vote()' target='w'>추천하기</a>
	</span>
	<br>
	</td></tr>
	<tr><td colspan=2 height=1 background=./image/dotline.gif></td></tr>
	<tr><td colspan=2><br>	
	<c:set var="contents1" value="${article.contents}" />
	<c:set var="newLine" value="\n" />
	<c:set var = "contents2" value = "${fn:replace(contents1, newLine , '<br>')}" />	 
     ${contents2 }
	 
	 <br><br>
	</td></tr>
	<tr><td colspan=2 height=1 background=./image/dotline.gif></td></tr>
</table>

<table width='100%'> 
<!-- 메뉴 -->
<tr><td><div class='button'><a href=./list.do>목록보기</a></div></td>
 
	 
	<td><div class='button'>
	<a href="javascript:show('modify',
		'./modify.do?bid=${article.bid}&page=${page}')">수정하기</a>
	</div></td>
	 	
	<td><div class='button'>
	<a href="javascript:show('delete',
		'./delete.do?bid=${article.bid}&page=${page}')">삭제하기</a>
	</div></td>
 
 
	<td><div class='button'>
	<a href="./write.do">글쓰기</a>
	</div></td></tr>
</table>

<!-- 글 수정 및 삭제를 위한 암호 입력 -->
<div id='enter' style='display:none'>
	<form method=post action='' name='my'>
	암호 <input type=password name=password size=5>
	<input type=hidden  name='bid' value='${article.bid}'>
	<input type='hidden'  name='page' value='${pageNo}'>
	<input type=submit name='submit' value=''>
	<input type=reset name='reset' value='숨기기' onClick='hide()'>
	</form>
</div>

<!-- 댓글 읽기 -->
<table width='99%'>
<c:forEach var="comment" items="${article.comments}">
	<tr><td colspan=2 height=1 background=./image/dotline.gif></td></tr>
	<tr><td width=100>
	 <b>${comment.writer}</b><br>
	 <span style='font-size:70%'>${comment.idate}<br>
	 ${comment.ip} &nbsp; &nbsp;	
	 <button id='b${comment.cmid}' 
	 	onClick='mydelete(${comment.cmid})'>x</button>	
	 </span>
	 <!-- 댓글 삭제 폼 -->
	 <span id='${comment.cmid}' style='display:none'>
	 <form method=post action=./delete_comment.do>	 
	 암호 <input type=password name=password size=5>
		<input type=hidden name='num' value='${comment.cmid}'>
		<input type=hidden name='read_num' value='${article.bid}'>
		<input type='hidden'  name='page' value='${page}'>
		<input type=submit value='삭제'>
		</form>	 
	 </span>
	 </td><td>${comment.contents}</td></tr>
</c:forEach>
<tr><td colspan=2 height=1 background=./image/dotline.gif></td></tr>
</table>

<!-- 댓글쓰기 -->
<form id='comment' action='./comment.do' method='post'>
<input type='hidden' name='bid' value='${article.bid}'>
<input type='hidden'  name='page' value='${page}'>
<div style='text-align:center'>
<table width='95%'>
	<tr><td align=center>
	<textarea name='contents' cols='65' rows='5' onFocus='edit()'>
댓글을 작성해주세요
	</textarea>
	</td></tr>
	<tr><td align=right>
	이름<input type='text' name='writer' size='10'> 
	암호<input type='password' name='password' size='10'>
	<input type='submit' value='저장'></td></tr>
</table>
</div>  
</form>
</div>
</body>
</html>