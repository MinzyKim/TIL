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
  ```
  
  

