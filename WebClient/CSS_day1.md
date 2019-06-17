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

