## 1. Spring Framework 특성

- 경량 컨테이너 지원(제공)
- Factory패턴이 적용된  IoC 컨테이너는 DI(의존하는 객체를 직접 생성하지 않고,  의존 객체를 전달하는 방식) 지원
- AOP(관점지향 프로그래밍) 지원 - 핵심 로직과 공통 로직을 분리해서 핵심 로직수행할때 공통 로직을 적용

- POJO 로 Bean을 정의해서 사용할 수 있도록 지원
- 영속성과 관련된 다양한 API(Hibernate, MyBatis, JDO,...) 지원
- 트랜잭션 처리를 위한 일관된 방법으로 처리, 관리 지원
- 배치 처리, 메시지 처리 , ... 다양한 API 지원
- Framework을 위한 Framework



## 2. Spring 모듈

- Spring Core 모듈 - IoC 기능 지원 (Spring container 클래서 : BeanFactory)

- Spring Context 모듈 - Core에서 지원하는 기능외에 추가적인 기능들 지원(JNDI, EJB를 위한Adapter 지원)

  (ApplicationContext - Spring Container 클래스, BeanFactory를 상속 받아서 )

- Spring AOP 모듈 - 관점 지향 프로그래밍 지원

- Spring DAO 모듈 - JDBC 보다 더 쉽고, 간결하게 개발 가능

- Spring ORM 모듈 - Hibernate, MyBatis, JDO,...와의 결합, 통합을 지원

- Spring Web 모듈 - MVC 패턴이 적용된 Web App 개발 지원, struts, Webwork 같은 프레임워크 통합 지원

- Spring Web MVC 모듈 - 다양한 Web UI, 기술 등의 API 지원

## 3. 의존 객체 생성 주입 방식

1. 생성자를 통해 주입
2. setXXXX 메서드를 이용해서 주입



## 4. Bean 설정 방식

1. xml 설정 방식

​      <bean id="빈이름"

​			name="빈이름"

​			class="" />

​	<constructor-arg ref="빈이름" />

   <property  type=" "  index=" "  value=" " ref="빈이름"(객체주입할 때) />

2. 자바 클래스와 Annotation

@configuration

@빈을 리턴하는 메서드 선언부에 @Bean 선언, 빈의 이름은 메서드이름

* 소스코드에서 빈요청할 때? 
  * 컨테이너객체.getBean("빈이름",  빈타입.class)

- Spring 컨테이너의 default 빈 Scope는 singleton이다.