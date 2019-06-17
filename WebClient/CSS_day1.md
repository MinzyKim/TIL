# CSS 1일차

- 외부파일형태로 만들어놓고, 재사용성을 높인다.

  Selector {

  property : value value value ;

  }

  color:red // css명령

- 태그 : 내용에 대한 구조적 명령어, 브라우저에 parser가 포함되어 있어서, 해석해서 wellformed한 문서인지 체크, 생성결과물은 DOM Tree(Document Object Model) /html/head, body/title/text/ 처럼 트리구조로 객체를 만들어서 수행

- Element : 태그+내용

- <style> 는 브라우저에 있는 Renderer 가 정의 
      
  </style>

- <!-- 가 html 주석

- <link rel:"stytlesheet" tytp="(마임타입) text/css" src="(문서경로 이름) style/css" 

- /* */ css 주석



## 0. border, margin, padding

![1560735356588](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1560735356588.png)

### 0.1 border

- 경계선 스타일
  - solid(실선)
  - double(이중실선)
  - dotted(점선)
  - dashed(줄선)
- 경계선 두께 - px단위
- 경계선 색상 - 색상 이름 혹은 코드



### 0.2 padding

- 글자와 경계선 사이의 간격

```css
#padding1{
			padding: 20px;
		}

#padding2{
			padding : 20px 30px 40px 50px;
		} /*top - right - bottom - left 순*/
```

### 0.3 width/height

- 박스의 너비/ 높이



## 1. 예제

```css
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<style type="text/css">
		{
			padding:0;
			margin:0;
		}
		p{
			color: #444444;
			font-size: 18px;
			font-family: '바탕';
			line-height: 150%;
			margin-top: 20px
		}
		#button{
			width: 120px;
			height: 25px;
			padding: 8px;
			background-color: blue;
			color: white;
			text-align: center;
		}

		#padding1{
			padding: 20px;
		}

		#padding2{
			padding : 20px 30px 40px 50px;
		}
		
		li{
			list-style-type: square;
		}
		span{
		    font-weight: bold;
		    color: #0e9dbc;
		    text-decoration: underline;
		}
		table, th, td{
			border:solid 1px #000000;
		}
		th{
			width: 80px;
			padding: 6px;
		}

		td{
			padding: 6px;
			text-align: center;
		}
		#day{
			background-color: #adf0f4;
		}
		#title{
			background-color: #adcff4;
		}
		table{
			border-collapse: collapse;
		}
	
		li{
			list-style-type: none;
		}
		#v_menu{
			width: 150px;
		}
		#v_menu li{
			padding: 5px;
			border-bottom: dotted 1px black;
		}
		#h_menu li{
			display: inline;
		}
		.menus{
			margin: 0 20px 0 20px;
			color: green;
		}
		*{
			padding:0;
			margin: 0;
		}
		li{
			list-style-type: none;
		}
		#image{
			float: left;
			border: solid 1px red;
		}
		#desc{
			float: left;
			width: 300px;
			margin-left: 30px;
			border: solid 1px red;
		}
		#menu{
			float: right;
			font-size: 14px
			margin-top: 20px
		}
		#menu li{
			display: inline;
		}
		#head{
			background-color: #f6f5ef;
		}
		#logo{
			float: left;
		}
		#top{
			float: right;
			margin: 30px 20px 0 0;
		}
		.item{
			margin: 0 20px 0 20px;
		}
		#main_image{
			clear:both;
			padding-top: 20px;
		}
		h3{
			font-weight: bold;
		}
		li{
			display: inline; /* 가로로 나오게 하는 거 */
			text-align: top;
		}
* {
    margin:0;
    padding:0;
}
ul {
    list-style-type: none;
}
#main_title {
    font-family:'맑은고딕';
    margin:10px;
    padding-bottom:6px;
    border-bottom:solid 2px #aaaaaa;
}
.list_item {
    clear: both;
    height: 130px;
    margin: 10px;
    border-bottom: solid 1px #cccccc;
}
.image {
    float:left;
    width: 100px;
	height: 100px;
}
.intro {
    float:left;
    width: 300px;
    margin-left:20px;
}
.price {
    float:left;
    width: 150px;
}
.red {
    font-weight: bold;
    color: red;
}
.small {
    font-size: 12px;
    margin-top:5px;
}
.writer {
    float:left;
    width: 100px;
}

.img {
	 width: 100px;
     height: 120px;	 
}

input[type=text] { background: red; }
input[type=password] { background: blue; }

img[src^=pu]{ 
border:3px solid brown;
 }

 img[src^=fl]{ 
border:3px solid yellow;
 }
 img[src^=fo]{ 
border:3px solid green;
 }
/* h1+h2{
 	color: red;
 }
 h1~h2{
 	background-color: orange;
 }*/
h1:hover {
	color: #9999ff;
}
h1:active{
	color: #ffb3b3;
}

input:enabled {
	background-color: #9999ff;
}
input:disabled {
	background-color: #ffb3b3;
}
input:focus {
	background-color:  orange;
}
ul{ overflow: hidden; }

li{
list-style: none;
float: left;
padding: 15px;
}

li:first-child {
	border-radius:  10px 0 0 10px;
}
li:last_child { 
border-radius: 0 10px 10px 0; }

li:nth-child(2n){
	background-color: #9999ff;
}
li:nth-child(2n+1){
	background-color: #ffb3b3;
}
p::first-letter {
	font-size: 3em;
}
p::first-line{
	color: red;
}
p::selection {
	background: black;
	color:yellow;
}
a{ text-decoration: none; }
a:visited {
	color:green;
}
a:link::after{ content:' - ' attr(href); }

h1 { background-color: red }
h2 {background-color: orange}
	</style>
	<title></title>
</head>
<body>
	<h2>축제명 : 제주 마을박람회 축제</h2>
	<ul>
		<li>일시 : 2018년 9월 중</li>
		<li>장소 : 월대천 및 외도동 일대</li>
		<li>주요 프로그램 : 어린이 사생대회, 뜸돌들기 등</li>
	</ul>

<h3>웹이란?</h3>
<p> 웹은 거미줄을 뜻하는 말로 WWW의 약어다<br>
인터넷과 웹<br>
웹과 관련된 직업<br>
</p>


<p id='padding1'>서울동물원은 동, 식물원 2420천미리미터 내 20개 동물사</p>
<p id='padding2'>서울동물원은 동, 식물원 2420천미리미터 내 20개 동물사</p>
	
	<div id='button'>
		자세히 보기 &gt;
	</div>

<h2>고속버스 예매</h2>
<table>
	<tr id='day'>
		<th colspan="4"> 서울 &lt=&gt 대전
		2020.9.6 수</th>
	</tr>
	<tr id='title'>
		<th>출발</th>
		<th>버스회사</th>
		<th>등급</th>
		<th>예약가능</th>
	</tr>
	<tr>
		<td>11:50</td>
		<td>한진고속</td>
		<td>우등</td>
		<td><img src='full.png'></td>
	</tr>
	<tr>
		<td>12:50</td>
		<td>천일고속</td>
		<td>고속</td>
		<td><img src='empty.png'></td>
	</tr>
	<tr>
			<td>13:50</td>
		<td>한진고속</td>
		<td>우등</td>
		<td><img src='full.png'></td>
	</tr>

</table>
<h2>인라인과 블록의 차이점</h2>

<h3>1. 인라인(수평 방향 레이아웃)</h3>
<img src="cheese.jpg">
<span>치즈</span>
<img src="juice.jpg">
<span>오렌지 주스</span>

<h3>2. 블록(수직 방향 레이아웃)</h3>
<p>이것은 단락입니다.</p>
<div>박스 A</div>
<div>박스 B</div>

<h3>1. 세로 메뉴</h3>
<ul id='v_menu'>
	<li>CEO 인사말</li>
	<li>조직도</li>
	<li>전화번호 안내</li>
	<li>찾아오시는 길</li>
</ul>

<h3>2. 가로 메뉴</h3>
<ul id='h_menu'>
	<li class='menus'>회사소개</li>
	<li>|</li>
	<li class='menus'>제품안내</li>
	<li>|</li>
	<li class='menus'>고객센터</li>
	<li>|</li>
	<li class='menus'>매장안내</li>
</ul>
<br>
<br>

<h3>이미지 갤러리<h3>
<ul>
	<li><img src='puppy1.jpg' width="300" height="240"></li>
	<li><img src='puppy2.jpg' width="300" height="240"></li>
	<li><img src='puppy3.jpg' width="300" height="240"></li>
</ul>

	<div id='logo'>
	<img src="logo2.png">
</div>
<ul id='menu'>
<li class="item">수목원소개</li>
<li>|</li>
<li class="item">방문안내</li>
<li>|</li>
<li class="item">고객센터</li>
<li>|</li>
<li class="item">공지사항</li>
</ul>
<div id='main_image'>
<img src="forest.jpg" width="700" height="350">
</div><br><br>

<h3 id='main_title'>판매 도서 목록</h3>
<div class='list_item'>
    <div class='image'><img  class="img" src='book1.jpg'   ></div>
    <div class='intro'>[문학동네]여행의 이유</div>
    <ul class='price'>
        <li class='red'>13,500원</li><br>
        <li class='small'>배송비 2,500원</li>
    </ul>
    <div class='writer'>김영하 저</div>
</div>
<div class='list_item'>
    <div class='image'><img class="img" src='book2.jpg'></div>
    <div class='intro'>[해냄]천년의 질문</div>
    <ul class='price'>
        <li class='red'>14,800원</li><br>
        <li class='small'>배송비 2,500원</li>
    </ul>
    <div class='writer'>조정래 저</div>
</div>     
<form>
	<input type="text" />
	<input type="password" /><br>

	<img src="puppy1.jpg" width="400" height="300">
	<img src="forest.jpg"  width="400" height="300">
	<img src="flower.jpg"  width="400" height="300">
	<br>
	<br>
	<h1>Header - 1</h1>
	<h2>Header - 2</h2>
	<h2>Header - 2</h2>
	<h2>Header - 2</h2>
	<h2>Header - 2</h2>
</form>

	<h1>User Action Selector</h1>

	<h2>Enabled</h2>
	<input />
	<h2>Disabled</h2>
	<input disabled="disabled" />
	<h2>focus</h2>
	<input  type="text" name="firstname" />
<br>
<ul>
	<li>First</li>
	<li>Second</li>
	<li>Third</li>
<li>Fourth</li>
<li>Fifth</li>
<li>Sixth</li>
<li>Seventh</li>
</ul>

<h1>Lorem ipsum dolor sit amet</h1>
<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
<p>Aenean ac erat et massa vehicula laoreet consequat et sem.</p>

<h1><a>Nothing</a></h1>
<h1><a href="http://hanb.co.kr">Hanbit Media</a></h1>
<h1><a href="http://www.w3.org">W3C</a></h1>
<h1><a href="http://github">Github</a></h1>

</body>
</html>
```

```css

<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
    <title>CSS3 Style Property Basic</title>
    <style>
        p:nth-child(1) { }
        p:nth-child(2) { font-size: 100%; }
        p:nth-child(3) { font-size: 150%; }
        p:nth-child(4) { font-size: 200%; }
        
        table{
            visibility: collapse;/*테이블이 보이지 않게 하기 위해서*/
        }
        #box{
            background-color: black;
            color: white;

            opacity: 0.4;
        }
        .box{
            width: 100px;
            height: 100px;
            position: absolute;
        }
        .box:nth-child(1){
            background-color: red;
            left: 10px;
            top: 10px;

            z-index: 100;
        }

        .box:nth-child(2){
            background-color: green;
            left:50px;
            top:50px;

            z-index: 10;
        }
        .box:nth-child(3){
            background-color: blue;
            left:90px;
            top: 90px;

            z-index: 1;
        }
          body > div {
            width: 400px; height: 100px;
            border: 3px solid black;

            position: relative;
            overflow: hidden;
        }

    </style>
</head>
<body>
    <div>
    <div class="box">  </div>
     <div class="box">  </div>
       <div class="box">  </div>
   </div>

</body>
</html>
```

