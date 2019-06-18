# Javascript 1일차

## 0. 데이터 타입

### 0.1 자바 스크립트의 데이터 유형

- 원시(primitive type) - string, number, boolean, undefined, null
- 객체(reference type) - function, object

```javascript
<script type="text/javascript">

//1	
    var a = 1; //정수와 실수 구분?
		document.write("a변수의 타입 : "+typeof(a)+"<br>");
//2		
var b=0.5;
		document.write("b변수의 타입 : "+typeof(b)+"<br>");
//3
		a="javascript";//동적타입 언어
		document.write("a변수의 타입 : "+typeof(a)+"<br>");
//4		
b='ECMASScropt6';
		document.write("b변수의 타입 : "+typeof(b)+"<br>");
//5 	
a=function(){};
		document.write("a변수의 타입 : "+typeof(a)+"<br>");
//6		
b=[]; //배열을 객체 취급한다.
		document.write("b변수의 타입 : "+typeof(b)+"<br>");
//7	
a={}; //Json(Javascript Object Notation)객체 정의할 때
		document.write("a변수의 타입 : "+typeof(a)+"<br>");
//8		
b=new Object();
		document.write("b변수의 타입 : "+typeof(b)+"<br>");
//9
a=true;
		document.write("a변수의 타입 : "+typeof(a)+"<br>");
//10		
a=0x2a;
		document.write("0x2a의 10진수 값 :"+a+"<br>");
//11		
a=0o73;
		document.write("0o73의 10진수 값 :"+a+"<br>");
//12		
a=0b101;
		document.write("0b101의 10진수 값 :"+a+"<br>");
//13		
a=1.161425E-11;
		document.write("1.161425E-11의 10진수 값 :"+a+"<br>");
	</script>
```

 **출력값*

```javascript
a변수의 타입 : number
b변수의 타입 : number
a변수의 타입 : string
b변수의 타입 : string
a변수의 타입 : function
b변수의 타입 : object
a변수의 타입 : object
b변수의 타입 : object
a변수의 타입 : boolean
0x2a의 10진수 값 :42
0o73의 10진수 값 :59
0b101의 10진수 값 :5
1.161425E-11의 10진수 값 :1.161425e-11
```

### 0.2 데이터 타입에 따른 다양한 예

```javascript
//1
a='"javascript"' 
		document.write(a+"<br>");
//2		
var c=[];
		//이땐 undefined라고 나옴.
	    document.write(c[0]+"<br>");
//3	   
a=function(){};
	    //아무것도 반환하지 않는 함수가 반환되는 값은 undefined? 라고 나오지 않음!!
	    document.write(a+"<br>");

//4
		document.write(a()+"<br>");
//5
 a=function(d){
	    	alert(d);
	    }
	    a();
```

**출력값*

```javascript
"javascript"
undefined
function(){}
undefined
undefined
```

