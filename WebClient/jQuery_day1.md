## 1. jQuery

- jQuery - 모든 브라우저에서 동작하는(클라언트 side에서 실행) 자바스크립트로 만들어진 라이브러리

- html의 문서요소를 간결하게 처리할 수 있다. 

```javascript
<head>내에 <script src="로컬경로/jquery-3.x.x.js"></script>
           <script src="CDN서버 경로 "></script>
jQuery(문서 요소  | 함수)
$(문서 요소  | 함수)
```

- 일관된 이벤트 핸들러 등록 - on(), off()

```javascript
on이벤트명 = function(){}
addEventListener("이벤트명", function(){}, false);
removeEventListener("이벤트명", 핸들러);
attachEvent()
detachEvent()
```

- 효과

- ajax처리 간결하면서 쉽게

- load이벤트와 유사한 jquery의 이벤트는 ready 이벤트

```javascript
$(document).ready(이벤트 핸들러 함수);
   $("css select문법")

$("태그명")
$("#id값")
$("태그.class속성값")
$("부모태그> 자식태그")
$("부모태그  자손태그")
$("태그, 태그, 태그")
$("태그[속성명=속성값]")
```

- jquery는 메서드 체인형태로 사용합니다.

- ### **each()**

  ```javascript
  <style type="text/css">
  	.high_light_0 { background:Yellow; }
  	.high_light_1 { background:Orange; }
  	.high_light_2 { background:Blue; }
  	.high_light_3 { background:Green; }
  	.high_light_4 { background:Red; }
  </style>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  
  <script>
  	$(document).ready(function(){
  		$("h1").each(function(index,item){ //jQuery에서는 이게 for문
  			$(item).addClass("high_light"+(index+1));
  			console.log(index+", "+item);
  			//$(this).addClass("high_light"+(index+1));
  		});
  	});
  
  
  
  
  </script>
  </head>
  <body>
  	jQuery: jquery 배열 관리
  	<h1 class="high_light_0">item - 1</h1>
  	<h1 class="high_light_1">item - 2</h1>
  	<h1 class="high_light_2">item - 3</h1>
  	<h1 class="high_light_3">item - 4</h1>
  	<h1 class="high_light_0">item - 5</h1>
  </body>
  ```

  

- ### **$.noConflict();**

  ```javascript
  <style type="text/css">
  	.high_light_0 { background:Yellow; }
  	.high_light_1 { background:Orange; }
  	.high_light_2 { background:Blue; }
  	.high_light_3 { background:Green; }
  	.high_light_4 { background:Red; }
  </style>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  
  <script>
  	$.noConflict();
  	var J=jQuery;
  	J(document).ready(function(){
  		J("h1").each(function(index,item){
  			J(item).removeClass("high_light_"+index);
  		});
  	});
  
  
  </script>
  </head>
  <body>
  	jQuery: jquery 배열 관리
  	<h1 class="high_light_0">item - 0</h1>
  	<h1 class="high_light_1">item - 1</h1>
  	<h1 class="high_light_2">item - 2</h1>
  	<h1 class="high_light_3">item - 3</h1>
  	<h1 class="high_light_4">item - 4</h1>
  </body>
  ```

- ### **filter()와 end()**

  ```javascript
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>  
    $(document).ready(function(){	 
  	  $('h3').css('background', 'Orange').filter(':even').css('color', 'Green').end().filter(':odd').css('color', 'Blue');
  	 
  });
    </script>
   </head>
   <body>
   <h3>item - 0</h3>
  <h3>item - 1</h3>
  <h3>item - 2</h3>
  <h3>item - 3</h3>
  <h3>item - 4</h3>
  <h3>item - 5</h3>
  
   
   </body>
  </html>
  
  ```

  - 체이닝할 때 추가한 filter() 메서드 제거하려면 end() 메서드 사용

- 

