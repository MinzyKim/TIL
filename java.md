## Java 10일차

## *<저번 시간 복습>*

## 0. Error

- Compile error - 문법적 문제, 언어 규칙에 맞지 않는 문제
- Runtime eroor - 실행시에 발생되는 오류 - 논리 오류, 로직 오류
- 자바의 Runtime error - XXXError
  -  (프로그램적으로 수정할 수 없음, 무겁고 RuntimeException의 하위 Exception은 프로그램적으로 수정하면 정상적으로 프로그램 흐름을 유도 가능)

## 1. Exception

### 1.1 종류

- checked exception - 실행 범위가 JRE를 벗어나는 경우의 코드에 대해서 컴파일시에 check를 해줌 (IO Exception, Socket Exception, SQLException...)
- unchecked exception - 실행 범위가 JRE를 벗어나지 않고, 사용자 부주의 또는 논리 오류에 의해서 발생 될 수 있는 Exception (NullPointerException, ArrayIndexOutOfBounds, NumberFormat)

### 1.2 처리 방식

- declare

  - throws 예외클래스이름을 메서드 선언부에 선언.
  - 예외처리 대신에 메서드를 호출한 곳으로 예외처리를 떠넘김.

- handle

  - try ~ catch ~ finally

    ``` java
    try{ 
    예외 발생 될 가능성이 있는 문장;
    
    문장;
    
    }catch(예외클래스 타입 객체){
    
    예외 처리 문장;
    
    catch(예외클래스 타입 객체)
    
    예외 처리 문장;
    
    }finally{
    
    예외 발생과 무관한 반드시 수행해야 할 코드 문장;
    
    ex) 사용했었던 자원 정리  .close() => checked exception
    
    try~catch~finally 사용 가능
    
    }
    ```

    

  - catch를 여러개 정의할 경우 하위 Exception클래스로부터 - 상위 Exception클래스 순으로 정리.

  - 프로그램 구현시 의도적으로 예외를 발생시켜서 호출한 쪽(caller)에게 메시지를 전달해서 호출한 쪽(caller)에서 흐름을 제어할 수 있도록 throw new 예외클래스(메시지) 처리.

-  API

  - java.lang.Throwable - java.lang.Error

    ​                                       java.lang.Exception

- 사용자 정의 예외클래스

  - AccessModifier class XXXException extends 구체적 Exception(API) {

    //속성

    //생성자

    //기능

    }

### *Today*

### 0. Boxing과 UnBoxing

```java
class BoxingTest{
    public static void main(String args[]){
        ing num = 5;
        //Boxing
        Integer i5 = num;
        Integer i5 = new Integer("3");
        num=i3; 
        //UnBoxing
    }
}
```

### 1. ShallowCopy와 DeepCopy

```java
class Circle implements Cloneable {
	Point p;
	double r;

	Circle(Point p, double r) {
		this.p = p;
		this.r = r;
	}

public Circle shallowCopy() {
		Object obj = null;

		try {
			obj = super.clone();
		} catch (CloneNotSupportedException e) {
		}

		return (Circle) obj;
	}

public Circle deepCopy () {
		Object obj = null;

		try {
			obj = super.clone();
		} catch (CloneNotSupportedException e) {
		}

		Circle c = (Circle) obj;
		c.p = new Point(this.p.x, this.p.y);

		return c;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[p=" + p + ", r=" + r + "]";
	}
}

class Point {
	int x, y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + x + ", " + y + ")";
	}

}

public class ShallowDeepCopy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Circle c1 = new Circle(new Point(1,1), 2.0);
		Circle c2 = c1.shallowCopy();
		Circle c3 = c1.deepCopy();
		
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		
		c1.p.x =9;
		c2.p.y=9;
		System.out.println("--------------------");
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		

	}

}
```

```
<출력 결과>
c1= [p=(1, 1), r=2.0]
c2= [p=(1, 1), r=2.0]
c3= [p=(1, 1), r=2.0]
--------------------
c1= [p=(9, 9), r=2.0]
c2= [p=(9, 9), r=2.0]
c3= [p=(1, 1), r=2.0]
```



	
