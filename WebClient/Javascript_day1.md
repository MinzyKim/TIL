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

## 1. 산술 연산

### 1.1 산술 연산자와 특성

- 산술 이항 연산자 : + - * / %

  - 특징

    - 정수끼리 나누어도 결과가 부동소수점

    - %의 피연산자는 부동소수점

    - '+'연산자는 피연산자 중 하나가 문자열이면 나머지 피연산자를 문자열로 만든다.

    - 기타

      - 0/0 -> NaN
      - "one"+1 -> NaN
      - true + true -> 2
      - 1 + null -> 1
      - 1 + undefined -> NaN

    - ++a와 a++

      - ```javascript
         a=1;
        	    b=++a;
        	    console.log("b="+b);
        	    console.log("a="+a);
        
        	    c=a++ + 2;
        	    console.log("c="+c);
        	    console.log("a="+a);
        
        ```

        **출력값*

        ![1560833066441](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1560833066441.png)

- Math 객체의 프로퍼티

  - Random 객체 사용해보기

  - ```javascript
      function dice (){
    	    	var num = Math.round(Math.random()*5+1);
    	    	return num;
    	    }
    	    document.write(dice());
    ```

## 2. 문자열 연산

- 문자열 제어하기

  - 문자열 연결

    ```javascript
    "Hello" + "World!"
    10 + " little indians"
    ```

    **출력값*

    ```javascript
    Hello World!
    10 little indians
    ```

  - 여러가지 문자열 제어 함수

    ```javascript
    	    var msgObj = new String("Everything is practice");
    //1
    	    document.write("msgObj.length : "+ msgObj.length + "<br>");
    //2
    	    document.write("msgObj.charAt(3) : "+ msgObj.charAt(3)+ "<br>");
    //3
    	    document.write("msgObj.substring(7, 10) : "+msgObj.substring(7,10)+ "<br>");
    //4
    	    document.write("msgObj.slice(7,10) : "+ msgObj.slice(7,10)+ "<br>");
    //5
    	    document.write("msgObj.slice(-3) : "+msgObj.slice(-3)+ "<br>");
    //6
    	    document.write("msgObj.slice(-9, -6) : "+msgObj.slice(-9, -6)+ "<br>");
    //7
    	    document.write('msgObj.indexOf("t") : '+msgObj.indexOf("t")+ "<br>");
    //8
    	    document.write("msgObj.indexOf('i',10) : "+msgObj.indexOf('i', 10)+ "<br>");
    //9
    	    document.write("msgObj.replace('p','P') : "+ msgObj.replace('p','P'));
    //10
    	    document.write("msgObj.includes('thing') : "+msgObj.includes("thing")+"<br>");
    //11
    	    document.write("msgObj.charCodeAt(0) : "+ msgObj.charCodeAt(0)+"<br>");
    //12
    	    document.write("msgObj.codePointAt(0) : "+msgObj.codePointAt(0)+"<br>");
    ```

    **출력값*

    ```javascript
    msgObj.length : 22
    msgObj.charAt(3) : r
    msgObj.substring(7, 10) : ing
    msgObj.slice(7,10) : ing
    msgObj.slice(-3) : ice
    msgObj.slice(-9, -6) : pr
    msgObj.indexOf("t") : 5
    msgObj.indexOf('i',10) : 11
    msgObj.split('') : E,v,e,r,y,t,h,i,n,g, ,i,s, ,p,r,a,c,t,i,c,e
    msgObj.replace('p','P') : Everything is PracticemsgObj.includes('thing') : true
    msgObj.charCodeAt(0) : 69
    msgObj.codePointAt(0) : 69
    ```

    

### 3. 논리 연산자

```javascript
// == 는 값만 비교
	    //1
	    document.write("null == undefined : " + (null==undefined)+"<br>");
	    //2
	    document.write("1 == '1' :" + (1 == '1')+"<br>");
	    //3
	    document.write("255 == '0xff : "+(255 == '0xff')+"<br>");
	    //4
	    document.write("true == 1 : "+ (true == 1)+"<br>");
	    //5
	    document.write("true == '1': "+ (true == '1')+"<br>");
	    //6
	    document.write("new String('a') == 'a' : " + (new String('a') =='a')+"<br>");
	    //7
	    document.write("new Number(2) == 2 : " + (new Number(2) ==2)+"<br>");

// === 는 값과 타입도 비교
	    //1
	    document.write("null === undefined : "+(null === undefined )+"<br>");
	    //2
	    document.write(" 1 === '1' : "+ ( 1 === '1')+"<br>");
	    //3
	    document.write(" 255 === '0xff' : "+ ( 255 === '0xff' )+"<br>");
	    //4
	    document.write("true === 1 : "+ (true === 1)+"<br>");
	    //5
 		document.write("true === '1': "+ (true === '1')+"<br>");
	    //6
	    document.write("new String('a') === 'a' : " + (new String('a') ==='a')+"<br>");
	    //7
	    document.write("new Number(2) === 2 : " + (new Number(2) ===2)+"<br>");
		//8
	    document.write("10>20>30 : "+(10>20>30)+"<br>")
```

​		**출력값*

```javascript
null == undefined : true
1 == '1' :true
255 == '0xff : true
true == 1 : true
true == '1': true
new String('a') == 'a' : true
new Number(2) == 2 : true
null === undefined : false
1 === '1' : false
255 === '0xff' : false
true === 1 : false
true === '1': false
new String('a') === 'a' : false
new Number(2) === 2 : false
10>20>30 : false
```

