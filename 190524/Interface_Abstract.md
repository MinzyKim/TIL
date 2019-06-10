### 0. Interface

* 용도 - 사용자(User)와 제공자(Provider)사이에서 매개체(연결) 역할

* 설계시, 서로 다른 시스템을 통합할 때 표준화를 위해서 활용

* 구성요소 - public static final 상수속성, abstract 메서드(구현 body없음), 

  static 메서드, default 메서드

  

* 인터페이스는 이원화된 구조 

  - 인터페이스(선언)는 반드시 인터페이스 **구현 클래스**가 있어야만 인터페이스에서 선언한 서비스 제공이 가능하다.
  - (클래스는 일원화된 구조(선언+구현)라고 볼 수 있다.)

```java
public interface 이름 [extends 인터페이스, 인터페이스,...] {...}
```

```java
public class 이름 [implements 인터페이스, 인터페이스,...] {...}
```

- *인터페이스는 reference 변수(객체명) 타입으로 선언 가능 하다.*

- *인터페이스는 new를 사용해서 객체 생성 불가능하다.*



### 1. Abstract

- 추상, 구현이 없고 선언만 존재함(클래스, 메서드 생성)
- 일반적으로 abstract 메서드는 클래스 설계시 모든 자식 클래스의 공통 기능을 선언하는 **부모 클래스**에 정의
- 부모 클래스에 선언 된 abstract 메서드는 상속 받은 **자식 클래스**에서 반드시 **override**해서 구현 body를 정의해야만 객체 생성이 가능
- *abstract 클래스는 new 사용해서 인스턴스(객체) 생성 불가능하다.*
  - abstract메서드가 선언되어 있는 클래스 또는 객체 생성 못하게 클래스 설계할 때 사용
- *abstract 메서드가 정의되어 있지 않아도 클래스를 abstract라고 선언할 수 있다.*
  - 반대의 경우는 불가능하다.

```java
public abstract class 이름 {...}
```

```java
public abstract 타입 이름() {...}
```



