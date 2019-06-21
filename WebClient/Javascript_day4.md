# Javascript Day 4

*<지난 시간 복습>*

- 브라우저 객체 중 문서의 URL을 관리하기 위해 사용 - Location(location)

  location.href

  assign(url)

  replasce(url)

  reload

  

- 요청을 보낸 클라이언트의 브라우저 정보를 얻을 수 있는 객체 - Navigator

  html문서에 포함된 자바스크립트는 클라이언트에 보내져서 클라이언트의 브라우저에 실행되므로 자바스크립트가 실행되는 브라우저 정보등을 얻기 위해 수행

  geolocation

  appName

  onLine

  platform

  

- 화면의 크기와 색상 정보를 관리 객체 - Screen

  width, height, orientation, colorDepth, pixelDepth



- 웹 페이지의 이력을 관리 객체 - history

  length, back(), forward(), go(n|-n)

  

  

- 이벤트 처리

   이벤트소스객체.on이벤트 = function(){}//이벤트 핸들러 함수

   이벤트소스객체.addEventListener("이벤트", 이벤트 핸들러 함수, 캡쳐링 여부);



​		등록된 이벤트 제거

​	 	 이벤트소스객체.on이벤트 = null;

​		 이벤트소스객체.removeEventListener("이벤트", 이벤트 핸들러 함수);



​	 	브라우저에서 처리해주는 기본 이벤트 취소 : 

​	예) <a href=""></a>의 클릭이벤트

​	예) form태그의 submit이벤트

​	1. 이벤트소스객체.on이벤트 = function(){ // 이벤트 핸들러 함수 override해서

​													....

​													return false;

​																		}

​	2. event.preventDefault();

- 이벤트 전파 방식
  - 버블링 ( 대부분의 브라우저에서 기본으로 지원 ) : 자식 객체 -> 부모 객체
  - 캡처링

- 이벤트 버블링을 중단시키려면 event.stopPropagation();

- 뷰포트(윈도우 좌표계) - 웹 브라우저에서 문서의 내용을 표시하는 영역

- 문서의 요소 객체는 박스모델이 적용되며, 왼쪽 X좌표는 left속성, 왼쪽 상단의 Y좌표는 top속성

  오른쪽 아래의 X좌표 right속성, 오른쪽 아래의 Y좌표 bottom속성

  너비는 width속성, 높이는 height속성

- 뷰포트의 너비 속성은 clientWidth, innerWidth(스크롤 막대 포함)
- 뷰포트의 높이 속성은 clientHeight, innerHeight (스크롤 막대 포함)

```javascript
- 문서의 요소 객체.innerHTML="<strong>강조체</strong>"
- 문서의 요소 객체.textContent = "<strong>강조체</strong>"
- 문서의 요소 객체.innerText = "<strong>강조체</strong>"
```



*<이번시간>*

## 1. Iterator

```javascript
Iterator <= Collection.iterator()
Symbol.iterator()
```

