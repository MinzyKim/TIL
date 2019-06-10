# 08. Exception Handling



- 예외처리 
  - declare, handle
- 예외발생
- 사용자정의 Exception 정의, 생성, 사용



### 8.1 예외처리(declare)

- throws

```java
public class 이름 throws 예외클래스
```

*ex)*

```java
public class ThrowsTest {
	public void methodA() throws IOException {
		methodB();
		System.out.println("methodA processed");
	}
	public void methodB() throws IOException {
		methodC();
		System.out.println("methodB processed");
	}
	public void methodC() throws IOException {
		//processing 코드....
		/*if(특정조건체크) 더 이상 processing이 어려운 조건이라서 
		  예외를 던져서 호출한 쪽에 메세지를 줘서 예외처리하도록 하려 함*/
		if(true) throw new IOException("오류원인인....");
		System.out.println("methodC processed");
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		ThrowsTest test = new ThrowsTest();
		test.methodA();

	}
}

```



### 8.2 예외처리(handling)			

- try ~ catch~finally
- try~finally
- try~catch(0 or M)
  - catch가 여러번 선언될 경우, 예외클래스의 상속 계층구조의 역순으로 예외클래스 타입부터 선언해준다.
- 예외클래스의 상속 계층구조

```jade
try {
   예외 발생 가능성 문장;
   문장;
}catch(예외클래스타입 객체){
  	예외 처리 문장;
}catch(예외클래스타입 객체){
  	예외 처리 문장;
}finally{
	예외 발생과 무관한 반드시 수행해야 할 문장;
	ex) 사용했었던 resource들의 정리(clolse()) 코드문장
}
```

*ex) try 수행 -> 예외 발생 無 -> finally -> output*

*ex) try수행 -> 예외 발생 有 -> 해당 되는 예외 프린트 출력*

*ex)*

```java
public class EvenTest {
	public static void main(String[] args) {
		System.out.println("main start");
		int num = -1; //try문 안에 있으면 변수선언이 갇혀서 밖으로 꺼냄
	
		try {
		  num = Integer.parseInt(args[0]);
		  System.out.println("other statement processing...");
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("배열관련 예외 처리");
		}catch(NumberFormatException e) {
			System.out.println("숫자 형식관련 예외 처리");
		}catch(Exception e) {
			e.printStackTrace();// 개발할 땐 필요, 마지막엔 제거
			//System.out.println(e.getMessage());
		}finally {
			System.out.println("resource 정리");
		}
		if (num % 2 != 0 && num>0) 
			System.out.println(args[0] + "은(는) 홀수입니다.");
		 else if(num % 2 == 0 && num>0)
			System.out.println(args[0] + "은(는) 짝수입니다.");
		
		System.out.println("main end");
	}
	
}
```

```
main start
other statement processing...
resource 정리
33은(는) 홀수입니다.
main end
```

or

```java
main start
배열관련 예외 처리
resource 정리
main end
```



### 8.3 사용자정의 예외 클래스

- 정의할 때는 구체적인 예외 처리 관련 *API의 Exception을 상속받아서 필요한 속성과 메서드를 추가해서 만든다.
  - 또는 Exception을 상속 받아서 예외처리에 필요한 속성과 메서드를 추가해서 만든다.

​	*ex*)

```java
public class XXXException extends Exception {
    //속성
    //생성자
    //멤버 메서드
}
```

```java
public class AbnormalValueException extends Exception {
	private double oldTall = 161.2;
	
	public AbnormalValueException(String message) {
		super(message);
	}
	
	public double getOldTal() {
		return oldTall;
	}
}
```

> API (Application Programming Interface)



#### *HashMap*

- **import.java.util.HashMap**에서 불러옴.
- 객체를 저장하고 저장 될 객체의 이름과 함께 저장한다.

*ex)*

```java
HashMap<String, product> cart = new HashMap();
cart.put("상품1", new Product("사과", 5, 1000)); //객체 저장
```

- HashMap에 저장될 객체의 이름은 중복 X, null X
- 데이터를 꺼낼 때는 키 목록을 먼저 받고, 하나 키를 꺼내서 Map으로부터 키로 저장된 객체를 꺼낸다.

*ex)*

```java
set<String> keys = cart.keySet();
Iterator<String> iterator = keys.iterator();
while(iterator.hasNext()){
    String key = iterator.next();
    Product p = cart.get(key);
}
```



#### ArrayList

```java
public static void main(String[] args){
    ArrayList<Product> alist = new ArrayList();
    alist.add(new Product("사과", 1500));
    alist.add(new Product("라면", 1000));
     for(int i=0; i<alist.size(); i++){
         System.out.println(alist.get(i).getName);
     }
    for(Product p : alist){
        System.out.println(alist.getName);
    }
    Iterator<Product> iterator=alist.iterator();
    while(iterator.hasNext()){
        System.out.println(iterator.next.getCame());
    }
}
```





