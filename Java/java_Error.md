# Day 10 (190527) 

## 1. ERROR

compile error - 문법적 문제, 언어 규칙에 맞지 않는 문제

runtime error - 실행시에 발생되는 오류 - 논리 오류, 로직 오류



## 1.2 RUNTIME ERROR

XXXerror (프로그램적으로 수정할 수 없음, 무겁고, 치명적)

RuntimeException의 하위 Exception은 프로그램적으로 수정하면 정상적으로 프로그램을 가동할 수 있다.



자바의 Exception 

- checked exception -  실행 범위가 JRE를 벗어나는 경우의 코드에 대해서 컴파일시에 check를 해주므로 (IOException,Socket, SQLException..)

- unchecked exception  - 실행 범위가 JRE를 벗어나지 않고, 사용자 부주의 또는 논리 오류에 의해서 발생될 수 있는 Exception (NullPointerException, ArrayIndexOutOfBoundException, NumberFormatException)

예외 처리 방식 - declare, handle 



declare 방식 - throws 예외클래스 이름을 메소드 선언부에 선언합니다.

​						예외처리 대신에 메서드를 호출한 곳으로 예외처리를 떠넘긴다.

Java API에서 

java.lang.Throwable 

- java.lang.Error
- java.lang.Exception



## 사용자 정의 예외 클래스

```java
AccessModifier class XXXException extends 구체적 Exception(API){
	//속성
    //생성자
    //기능   
}
```



## Java.lang

- 얕은 복사
  - s1 = s2
- 깊은 복사 
  - java.lang.Object.clone()  메서드
  - 공장에서 새로 생성



# Cloneable

Cloneable 인터페이스를 구현한 클래스만 clone() 메소드를 사용하여 복제할 수 있다.

그런데 Cloneable 인터페이스는 까보면 아무것도 없는 **Mark Interface**이다.

사실 Clonable 인터페이스 자체로는 아무 일도 하지 않지만 **JVM**에서 이런 Mark를 확인해서 복제가 가능한지 불가능한지 파악해서 처리한다. 



이러한 Mark Interface는 Cloneable 이외에도 Serializable 등이 있다.



- Mark Interface 이용한 예제

  <https://blog.naver.com/anna6678/221102699697>



# 박싱 언박싱

boolean -> Boolean

byte -> Byte

**char -> Character**

**int -> Integer**

... 나머진 대문자로만 치환

```java
class BoxingTest {
    public static void main(String[] args){
        int num = 5;
        Integer i5 = num;//Boxing. jdk 1.5이전에선 오류 발생
       //Intege i5 = new Integer(num); // 이게 jdk 1.5이전
        Integer i3 = new Integer("3");
        num = i3; //UnBoxing
    }
}
```



