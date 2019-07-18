## Spring Framework

### Java EE 애플리케이션 플랫폼

- web 서버와 관련된 기술 표준 사양을 정의
  - web 서버는 무엇이 있는가?

    - Web+Application Server

      ​	Tomcat

      ​	Weblogic

      ​	AS

      ​	Webshpere ...

- 트랜잭션관리, 보안, 상태관리, 프로세스와 스레드, 등등의 서비스 제공

- Database 데이터 소스 객체는 db 연결 정보 포함(connection 객체라고도 함) 이런 것들을 캐싱해준다.

- web container와 ejb container ejb는 사용 잘 안한다.





## 웹 애플리케이션 아키텍처

- 모델 1 - JDBC와 JSP만 사용하는 웹페이지 구현
- 모델 2 - MVC(Model, View, Controller) 방식, Servlet활용
- 여러 Client들에 대해서 단순하게 WebServer가 여러개 구성되면 road balancing해서 서비스할 수 있음.
- multicare 운영 시에 spring 프레임워크가 사용 된다.



## Maven

- 의존성, 라이브러리, 등을 관리하는 프레임워크
- 라이브러리들 일괄 관리 해줌
- Component=Bean - Java Client Bean 단순 작업 object가 bean이다.
- Framework - 시스템구조 + 성능검증 기반 component제공, 실행환경 controller 제공,
  -  "Application 구현의 뼈대"

- Spring Caontext = Spring IOC container => 핵심기능 VI



## Spring Framework

- 결합도와 유지보수
  - 결합도는 코드의 한 요소가 다른 것과 얼마나 강력하게 연결되어 있는지를 나타내는 말
    - 느슨하게 하는 방법엔 interface가 있다.
    - factory패턴을 만든다 - spring iocContainer가 수행해준다.
    - 의존 bean생성 -> Message Bean / HelloServlet Bean