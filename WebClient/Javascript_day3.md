# Javascript 3일차

[자바 스크립트 함수를 정의하는 방법 ]

- 함수 선언문으로 정의

```javascript
function square(x) { return x*x }
```

- 함수 리터럴 방식으로 선언

```javascript
var square = function(x) { return x*x }
square(5) //호출
```

- Function 생성자로 정의

```javascript
var square = new function("x", "return x*x");

square(5); //호출
```

- 화살표 함수 표현식(람다식)으로 정의

```javascript
var square = x => x*x

square(5);//호출
```



- 즉시 실행 함수 - 익명 함수를 정의하고 바로 실행하는 함수

한번 실행하므로 초기화 작업할때 사용하며, 전역 유효 범위를 오염시키지 않기 위해 namaspace에 추가

(function(x) { return x*x }) (5);

(function(x) { return x*x }) (5));

(function square(x) { return x*x})(5);



- 모든 함수의 인수는 가변 길이를 가집니다.

  - 선언된 인수보다 적으면 인수를 참조할 때 undefined
  - 선언된 인수보다 많으면 무시
  - 모든 함수가 생성될 때 전달되는 인수가 저장되는 함수의 property는 Arguments객체의 arguments
    - arguments.length, arguments[index]

- 자바 스크립트에서 재귀함수를 정의하고 사용할 수 있다.

  - function fact(n){

     	if(n<=1) return n;

    return n*fact(n-1);

    }

    fact(5);

- 함수가 호출되어 실행되는 시점에 this 값이 결정된다.

  - 최상위 레벨의 코드에서 this는 Window객체의 참조변수가 window

  - 이벤트 핸들러 함수 내부에서 this는이벤트가 발생한 소스객체

    - window.onload=이벤트핸들러 함수(){};

    - window.onload=function(){

      ​		this.......//?

      };

      button.onclick = function(){

      ​		this.......// 클릭 이벤트가 발생한 버튼

      }

    - 생성자 함수 안에서 this는 생성자 함수로부터 생성되는 객체 자신

    - 호출된 함수 내부에서 this는 window이다.

      ```javascript
      	function f(){ console.log(this);} //전역 유효 범위의 namespace에 추가되므로
      	f(); //this는 window
      ```

- 객체 정의 방법 :

  1. 객체 리터럴로 정의

     {속성:값, 속성:값, 속성:function(){},...}

  2. 생성자 함수를 정의하고 생성자 함수로부터 객체 생성할 수 있다.

  3. ```javascript
     function Person(name, age){
     var _name = name; //private성격의 속성
     		var _age = age;
     		return{
     				getName : function(){ return _name; }, //클로저
     				getAge : function() { return _age; },
     				setAge : function() { _age=n; }
     		};
     }
     	var p = new Person("kim", 30);
     	console.log(p._name) ; // 오류
     	console.log(p._name) ; // 오류
     	console.log(p.getName); 
     	console.log(p.getAge) ; 
     ```

     

## 1. 자바스크립트 이벤트

- DOM Level 0 이벤트 모델 : on이벤트명 = function(){} => 이벤트당 하나의 이벤트 핸들러만 연결
- DOM Level 2 이벤트 모델 : 이벤트소스(태그객체).addEventListener("이벤트명", function(){}, (이벤트캡처여부) - 이벤트 캡쳐 여부값은 기본이 false
  - 이벤트당 하나 이상의 이벤트 핸들러만 연결
- 이벤트에 대한 이벤트 핸들러가 한번만 수행 후 이벤트 핸들러 취소하려면 : 
  - 이벤트 소스.on이벤트 속성 = null;
- 