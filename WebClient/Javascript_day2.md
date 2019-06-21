# 자바스크립트 Day2

## 0. 자바스크립트는?

- 변수?

  - 처리해야 할 값을 메모리에 저장하고 값을 참조하기 위해 사용하는 이름
  - 변수 선언 : var
  - 변수명 naming 규칙
    - _, $, 영문자로 시작
    - 두번째 문자부터는 숫자도 허용
    - 길이 제한 없음
    - 키워드 X, 내장함수명, 내장객체명 X

- 자바 스크립트의 데이터 유형

  - Primitive type : number, string, boolean, null, undefined, symbol 
  - Reference type : function, object, interface, enum, ... 배열 Array는 객체. object유형으로 나옴

- 선언되지 않은 변수를 참조하면 반환되는 값은? ReferenceError

- 초기값이 할당되지 않고 선언만 된 변수를 참조하면 반환되는 값은? undefined

- 객체를 메모리에서 검색을 했는데, 검색되지 않으면 반환되는 값은? null

- 자바 스크립트 출력 방법

  - document.write(), document.writeln() - html 문서의 body 영역 출력
  - console.log(), console.dir()(내부 구조를 계층구조로 볼 때) - 브라우저 또는 node같은 자바스크립트 실행환경에서 제공하는 콘솔창에 출력
  - window.alert("메세지");

- 자바 스크립트 입력 방법

  - window.prompt("메시지", 기본값) - 반환타입은 문자열
  - window.confirm("메시지") - 반환타입은 boolean

- 자바 스크립트 연산자

  - 산술 연산자 - *, / , %, +, -
  - 단항 연산자 - ~, !, +, -, ++, --
  - 비교 연산자 - <, >, <=, >=, !=, ==, ===, !==
  - 비트 연산자 - &, |, ^
  - 논리 연산자 - &&, ||
  - shift 연산자 - <<, >>, >>>
  - 삼항 연산자 - 조건?항1:항2
  - 기타 연산자 - typeof, in,insatanceof, ...

- 제어문

  - if(조건){ 문장; }

    if(조건){

    문장;

    ....

    }else{

    문장;

    ....

    }

  - 다중 if문

    else if

    

  - Switch문

    switch(표현식){

    case 값: 문장; break;}

    

    

    

- 자바 스크립트 객체 생성 방법

  1. 객체 리터럴 - JSON, 하나의 객체만 생성해서 사용하는 경우
  2. 생성자 함수 정의 - new 사용, 필요할 때마다 생성자함수로부터 객체 생성

  

```javascript
var employee = {}; //빈 객체 생성, var emp = new Object();
employee.ename='Scott';
employee.job='Developer';
employee.salary= 5000;
employee.hiredate='2013/01/01';
employee.address='삼성동';

document.write("employee.ename="+employee.ename+"<br>");
document.write("employee['job']="+employee["job"]+"<br>");
document.write("<br>");

for(var key in employee){
	document.write(key+" : "+employee[key]+"<br>");
}
```

**출력값*

```javascript
employee.ename=Scott
employee['job']=Developer

ename : Scott
job : Developer
salary : 5000
hiredate : 2013/01/01
address : 삼성동
```

```javascript
document.write("employee instanceof Object =>"+(employee instanceof Object)+"<br>"); //내장 객체 중 최상위 Object 상속 확인
console.dir(Object);

employee.toString=function(){
	var output="";
	for(var key in this){
		if(key != 'toString'){
			output+=key+" : "+this[key]+"\n";
		}
	}
	return output;
}
document.write(employee+"<br>");
document.write(employee.toString()+"<br>");
```

**출력값*

```javascript
employee instanceof Object =>true
ename : Scott job : Developer salary : 5000 hiredate : 2013/01/01 address : 삼성동 
ename : Scott job : Developer salary : 5000 hiredate : 2013/01/01 address : 삼성동 
```

```javascript
# for in 반복문을 객체의 속성에 접근할때 사용 가능
# 객체에 대해서 사용하는 in키워드는 속성 존재 여부를 체크할 때 사용할 수 있다.
# 객체의 속성을 객체, 속성 대신 속성명으로만 사용할때 with(객체 { } 사용
# 객체 리터럴 방식으로 정의되는 객체는 동적으로 속성, 메소드를 추가하거나 제거할 수 있다.
var student = { 이름 : '홍길동', 영어:88, 국어:90, 수학:77, 과학:75};
document.write(student.이름+"의 총점 : "+(student.영어+student.국어+student.수학+student.과학)+"<br>");

with(student){
	document.write(이름+"의 평균 : "+((영어+국어+수학+과학)/4)+"<br>"); 
} //객체.속성 하기 번거로우면 with 함수 사용하기
	

```

**출력값*

```javascript
홍길동의 총점 : 330
홍길동의 평균 : 82.5
```



## 1. Document객체

```javascript
<!D0TYPE>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
<script type="text/javascript">
	window.onload=function(){
	var h1 = document.createElement("h1");
	var text1 = document.createTextNode("새 요소 추가");
	h1.appendChild(text1);
	document.body.appendChild(h1);

	var img1= document.createElement("img");
	img1.src = "./puppy1.jpg";
	img1.width= 300;
	img1.height=500;
	document.body.appendChild(img1);

	var img2= document.createElement("img");
	img2.setAttribute('src', "./puppy3.jpg");
	img2.setAttribute('width', 400);
	img2.setAttribute('height', 500);
	console.log(img2.getAttribute("src"));
	document.body.appendChild(img2);
}
</script>
</head>
	<body>
		<h3>Document객체를 이용한 문서 구조 변경</h3>
	</body>
	</html>
```

**출력값*

![1560930104634](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1560930104634.png)



