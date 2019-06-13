# HTML 1일차

- WWW(World Wide Web)
  - 단순 html서비스, 단순 view
  - 동기방식(응답이 올때까지 아무것도 못하는 것)
  - 전체페이지 갱신방식(전체 페이지가 천~천히 로드되는 것)
  - 정적 서비스(request하는 데이터를 미리 알고 있어야 response가 가능한 서비스(현실적으로 불가능))

- BackEnd

  *<등장순서>*

  - Servlet(Server Applet)
  - JSP(Model1방식)
  - EJB(분산처리) - 망

  - Servlet<Container>, Jsp<View> 방식으로 개발

  - Framework(Struts? Spring)등장

  - 전자정보표준화 - Spring기반

    

- FrontEnd

  *<등장순서>*

  - Html-문서구조
  - css, javascript - 스타일, 동작
  - Rich Client Internet
  - Flash, IE(Active X)
  - W3C - 웹 표준화 
  - Ajax - 비동기방식으로 request하고 text나 xml같은 일부 부분이 response, javascript가 동작 수행하고 다른 업무 수행 가능하게 함
    - 비동기요청, 부분페이지갱신 방식

  ## **<나머진 실습>**

  ```html
  <!D0CTYPE html>
  <html>
  <head>
  	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
  <meta charset='utf-8'>
  <title>HTML 기본 구조 </title>
  <style type="text/css">
  	body {
    background-color: lightyellow;
  }
  
  h1 {
    color: black;
    text-align: left;
  }
  
  p {
    font-family: verdana;
    font-size: 20px;
  }
  	}
  </style>
  </head>
   <body>
  	<p>The <abbr title="World Health Organization">WHO</abbr> was founded in 1948.</p>
  
  <i class="fas fa-file"></i></i><br>
  <i class="fas fa-star">안녕하세요? 처음 만들어보는 html문서인가요?<i class="fas fa-star"></i><br>
  나는 민지입니다.<i class="fas fa-heart"><i class="fas fa-cloud"></i><br></i><br>
  
  <h1>Block태그</h1><!--블락태그-->
  <h2>Block태그</h2>
  <h3>Block태그</h3>
  <h4>Block태그</h4>
  <h5>Block태그</h5>
  <h6>Block태그</h6>
  
  <hr>
  <br>
  <p>레이노병(Raynaud disease)이란, <br>
  862년 프랑스 의사인 모리스 레이노(Maurice Raynaud) 가 <br>
  처음 발견한 질환으로 <br>
  그 의사의 이름을 따서 레이노병이라 불리게 되었습니다....</p>
  <br/>
  <hr/>
  <br>
  <b>Block태그</b><!--블락태그-->
  <i>Block태그</i>
  <small>Block태그</small>
  <sub>Block태그</sub>
  <sup>Block태그</sup>
  <ins>Block태그</ins>
  <del>Block태그</del>
  <br>
  <hr>
  <ruby>
  	<sapn>大韓民國</sapn>
  	<rt>대한민국</rt>
  </ruby>
  <br><hr>
  <h1>ol tag</h1>
  <ol>
  	<li><a href='http://www.facebook.com' target ='_black'> Facebook</a></li>
  	<li><a href='http://www.tweeter.com' target ='_black'> Tweeter</a></li>
  	<li><a href='http://www.linkedin.com' target ='_black'> Linked In</a></li>
  </ol>
  <h1>ul tag</h1>
  <ul>
  	<li>Facebook</li>
  	<li>Tweeter</li>
  	<li>Linked In</li>
  </ul>
  <br><hr>
  <table border=1>
  	<tr><td>A</td><td>A</td><td>A</td></tr>
  	<tr><td>A</td><td>A</td><td>A</td><td>A</td><td>A</td></tr>
  	<tr><td>A</td><td>A</td><td>A</td><td>A</td></tr>
  </table>
  <table border=1>
  	<tr><td colspan="2">Cell</td><td>Cell</td><td>Cell</td><td>Cell</td></tr>
  	<tr><td>Cell</td><td>Cell</td><td>Cell</td><td>Cell</td></tr>
  	<tr><td>Cell</td><td>Cell</td><td>Cell</td><td rowspan="2">Cell</td></tr>
  	<tr><td>Cell</td><td>Cell</td><td>Cell</td><td>Cell</td></tr>
  </table>
  <br><hr>
  <table border=1>
  	<tr><td colspan="3">picture</td><td rowspan="2">성명</td><td>한글</td><td colspan="3"></td><td>나이(만)</td><td>&nbsp;&nbsp;&nbsp;</td><td rowspan="2">성별</td></tr>
  
  	<tr><td colspan="3">picture</td><td>영문</td><td colspan="3">&nbsp;&nbsp;&nbsp;</td><td>주민등록번호</td><td></td></tr>
  
  	<tr><td colspan="3">picture</td><td>현주소</td><td colspan="7">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
  
  
  	<tr><td colspan="3">picture</td><td>e-mail</td><td colspan="7">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
  
  <tr><td colspan="3">picture</td><td>신장</td><td colspan="2">&nbsp;cm</td><td>몸무게</td><td colspan="2">&nbsp;kg</td><td>혈액형</td><td colspan="2">&nbsp;형</td></tr>
  
  <tr><td colspan="3">picture</td><td>전화번호</td><td colspan="5">&nbsp;&nbsp;</td><td>휴대폰</td><td colspan="5">&nbsp;&nbsp;</td></tr>
  
  </table>>
  <h2><수국></h2>
  
  <img src='Hydrangeas.jpg' width='200' height='200' title='냠냠'>
  <img src='Hydrangeas.jpg' width='150' height='150'>
  <img src='Hydrangeas.jpg' width='350' height='200'>
  
  <h2><오디오 재생하기></h2>
  <audio src='Kalimba.mp3'></audio>
  
  
  <h1>회원가입</h1>
  <form mehtod="post" action="login.jsp" name="" id="">
  	아 이 디 : <input type='text' name ="userid" id="userid" required><br>
  	비 밀 번 호 : <input type='password' name ="useripwd" id="userpwd" required><br>
  
  	URL : <input type="url" name="url"><br>
  	EMAIL : <input type="email" name="email"><br>
  	연락처 : <input type="tel" name="phone" pattern="\d{3}-\d{4}-\d{4}" placeholder="000-0000-0000"><br>
  number : <input type='number' max=100 min=0 step=5
  title='0~100사이의 값만 허용합니다.'><br>
  	 
  	취미 : <input type="checkbox" name="hobby" value="등산		"> 등산 <br>
  	 		<input type="checkbox" name="hobby" value="수영"> 수영 <br>
  	 		<input type="checkbox" name="hobby" value="코딩"> 코딩 <br>
  	기술 : <input type="radio" name="skill" value="java"> 		JAVA <br>
  			<input type="radio" name="skill" value="oracle"> ORACLE <br>
  			<input type="radio" name="skill" value="R"> R <br>
  
  	date : <input type='date'><br>
  	time : <input type='time'><br>
  	week : <input type='week'><br>
  	month : <input type='month'><br>
  	color : <input type='color'><br>
  	range : <input type='range' max=100 min=0 step=5><br>
  	암호화 키<keygen name="key"><br />
  	search : <input type="search"><br>
  	<textarea rows="5" col="30">
  	</textarea><br>
  	<input type="file"><br>
  	<select>
  		<option>서울</option>
  		<option>경기도</option>
  		<option>강원도</option>
  		<option>경상도</option>
  		<option>전라도</option>
  	</select>
  
  	<input type="button" value="버튼"><br>
  
  	<input type="text" name ="fruit" list="fruits">
  	<datalist id="fruits">
  	<option value="apple" label="사과">
  <option value="orange" label="오렌지">
  <option value="grape" label="포도">
  <option value="lemon" label="레몬">
  <option value="mango" label="망고">
  <option value="mellon" label="멜론">
  </datalist>
  
  <br>
  <details open>
  <summary>복사중
  ...
  <progress  max="375505392" value="375505392"></progress> 100%
  </summary>
  <dl>
  	<dt>초당전송량:</dt> <dd>452KB/s</dd>
  	<dt>복사할파일명:</dt> <dd>/home/rpausch/raycd.m4v</dd>
  	<dt>대상파일명 :</dt> <dd>/var/www/lectures/raycdm4v</dd>
  	<dt>걸린시간:</dt> <dd>01:16:27</dd>
  	<dt>영상크기:</dt> <dd>320x240</dd>
  </dl>
  </details>
  
  
  	<input type="submit" value="로그인">
  	<input type="reset" value="취소"><br>
  
  	
  
  <!--html 주석, 파서가 해석하지 않습니다.-->
  </body>
  </html>   
  ```
  
  ## <웹 페이지 레이아웃>
  
  ```html
  <!D0CTYPE html>
  <html>
  <head>
  <meta charset='utf-8'>
  <title>웹 페이지 레이아웃</title>
  <style type="text/css">
  	* {
  		padding: 0;
  		margin:0;
  	}
  	.clear{
  		clear: both;
  	}
  	header{
  		width: 995px;
  		height: 100px;
  		margin-top: 10px;
  		border: solid 1px #cccccc;
  	}
  
  	#logo{
  		float: left;
  		border: solid 1px red;
  	}
  
  	#top{
  		float: right;
  		margin: 30px 20px 0 0;
  		border: solid 1px red;
  	}
  
  	nav{
  		width: 995px;
  		height: 70px;
  		margin-top: 10px;
  		border: solid 1px green;
  	}
  	section {
  		width: 674px;
  		height: 240px;
  		float: left;
  		margin-top: 10px;
  		border: solid 1px green;
  	}
  
  	aside {
  		width: 290px;
  		height: 240px;
  		float: left;;
  		margin-top: 10px;
  		margin-left: 29px;
  		border: solid 1px green;
  	}
  	footer{
  		width: 995px;
  		height: 130px;
  		margin-top: 10px;
  		border: solid 1px green;
  	}
  
  
  </style>
  </head>
  <body>
  	<header>
  		상단 헤더
  	</header>
  
  	<nav>
  		 네비게이션 메뉴
  	</nav>
  
  	<section>
  		메인 콘텐츠
  	</section>
  
  	<aside>
  		사이드바
  	</aside>
  	<header>
  	<div class="clear"></div>
  
  	<footer>
  		하단 푸터
  	</footer>
  
  	<div id='logo'>
  		<img src="img/logo.png">
  	</div>
  
  	<div id='top'>
  		로그인 | 회원가입 | 사이트 맵 | 회사소개
  	</div>
  </header>
  </body>
  ```
  
  