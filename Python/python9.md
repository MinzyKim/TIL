# 파이썬 9일차

## 1. Subway CRUD

### 	1. Model

- Subway
  - name
  - address
  - phone
  - Menu
  - bread
  - vegetable
  - sauce
  - Drink
  - Created_at
  - Updated_at



 ### 	2. Index Page

- 주문자명 : 메뉴명 주문일자가 list로 보여짐
- 클릭하면 detail 페이지로 이동
- Nav bar에 홈으로 버튼 / 새로 주문하기



### 	3. Detail Page

- 주문 정보에 대한 내용 전부 출력
- 하단에 수정하기 /삭제 버튼



### 	4. 수정하기 Page

- 주문 내용을 수정



### 	5. 삭제하기 Page

- 주문 내용을 삭제





## 2. REST

- Representational State Transfer

- Roy Fielding 논문으로 아키텍쳐 발표

  - http 설계의 우수성에 비해 제대로 활용하고 있지 않아 발표

- HTTP

  - Request/Response로 서버와 클라이언트간에 Http로 통신

- 웹 서버는 웹 리소스를 관리하고 제공을 함

  - 미디어 타입 : 종류가 수천가지 데이터 타입이 존재
    - MIME(Multipurpose Internet Mail Extensions)

  - Html : text/html
  - Jpeg : image/tpeg
  - ASCII : text/plain

- URI(URL + URN)

  - URL : 리소스의 위치 (스킴://서버위치/경로)
    - 스킴 : 리소스에 접근하기 위한 프로토콜
  - URN : 위치에 독립적임



- REST의 구성

  - 자원 - URI
  - 행위 - HTTP Method
  - 표현

- REST 디자인 가이드

  - '/'는 계층관계를 나타내는데 사용
  - '_' 대신 '-'를 활용
  - 정보의 자원을 표현해야 함

- ex

  - GET /boards/show/1. - show라는 행위가 있기 때문에 REST하지 않음
  - GET /boards/1

  