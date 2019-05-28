# DAY 11 JAVA

## 유용한 클래스

### 0. java.lang.Object

- 객체.clone() - 객체 복제
  - 얕은 복사 - 객체의 생성된 주소값을 할당하는 방식 유사(동일한 객체를 참조)
  - 깊은 복사 - 객체의 모든 속성을 새로 생성해서 메모리에 새로운 동일한 객체를 사용자 정의 클래스를 복제가능도록 하려면 Clonable 구현



- Object의 equal() - 객체 생성 주소의 hash값을 비교

- hashCode() - 객체 생성 주소의 hash값 리턴



- Object의 obj = new Double();

- 실제 생성 된 객체의 타입을 리턴받으려면 getClass()

- Object의 toString - 클래스 FullName@hash값



- 일반 메서드에서는 notify(), notifyAll(), wait() 호출 불가, synchronized가 선언된 메서드에서 호출 가능 - 멀티스레드 환경에서 가능



### 1. java.lang.String - 문자열 표현, 불변객체

```java


String s = new String(new byte[]{65,66,67});

System.out.println(s);//System.out.println(s.toString())와 동일, ABC출력



String s2 = "java";

byte[] bytes = s2.getBytes();

s2.charAt(0) //문자 하나 출력 j

s2.subString(1, 3); // a,v 출력, 3안쓰면 끝에 다 출력

String newStr = s.concat(s2); -> s객체 출력하면 ABC

contains() //
```



- 문자열객체.equals(비교할 문자열객체)- 문자열 내용을 비교

- equalsIgnoreCase // 비교하려는 문자열의 대소문자 관계없이 비교하고 싶을 때

  ```java
  s2.length()
  
  String s3 = "JackAndJue";
  
  String s4 = s3.replace("J", "BI"); =>s3객체 출력하면 "JackAndJue"
                                       =>s4객체 출력하면 "BlackAndJue"
  
  String s3 = "   Jack  Jue   ";
  
    s3.trim().length => 결과는 9
  ```

  

- primitive data type을 문자열로 변환하려면 : String.valueOf() 또는 값+"문자열"결합

- split(구분자 또는 정규표현식) - 문자열을 구분자로 쪼개어 문자열 배열로 

- join(결합문자, 문자열배열) - 문자열 배열의 요소를 하나씩 결합 문자를 사용해서 하나의 문자열로 리턴



- 가변문자열은 StringBuffer

- equals()

  ```java
  StringBuffer sb1 = new StringBuffer("java");
  StringBuffer sb2 = new StringBuffer("java");
  System.out.println(sb1.equals(s2));
  
  결과는 false
      
  sb1.append("& sql"); //sb1의 출력내용은 "java& sql"
  sb1.insert(4, " web ");
  remove();
  length();
  substring()
      .
      .
      .
  ```

### 2. java.lang.Math



*<수학계산에 유용한 메서드를 가지고 있는 클래스>*

생성자 private 이므로 new Math() 불가	

모든 속성과 메서드는 static

```java
abs()
max()
min()
log()
power()
sqrt()
round()
ceil()
floor()
cos()
  .
  .
  .
```

- 모두 객체로 구현해야 할 경우, primitive data type을 객체로 wrapping

  - ```
    boolean -> Boolean -> booleanValue()  문자열 "true" -> Boolean.parseBoolean()
    byte -> Byte          byteValue()     문자열 "100" -> Byte.parseByte()
    short -> Short        shortValue()
    char -> Character     charValue()
    int -> Integer        intValue()
    long -> Long          longValue()
    float -> Float        floatValue()
    double -> Double      doubleValue()
    ```

### 3. java.util.Objects



*<객체가 null 또는 null이 아닌지 체크하거나 반드시 null이 아니어야 함을 메서드로 제공해주는 클래스>*

- 시스템의 현재 시간을 utc기준 milli second로 리턴하는 메서드 : System.currnetTimeMillis();



- 난수 생성 : Math.random()

  ​                    Random r = new Random()

  ```java
  (int)(Math.random()*100+1) // 1~100까지
  Random r = new Random()
  r.nextInt(100)+1 // 1~100까지
  Random r = new Random(seed값)
  ```

  

- 정규 표현식을 이용해서 데이터 처리해야 할 경우, 특정 패턴을 객체로 생성
  1. java.util.regex.Pattern.compile("패턴") => Pattern 인스턴스 생성
  2. Matcher m = Pattern 인스턴스.matcher(처리할 대상 데이터) -> Matcher 인스턴스 생성
  3. m.matches() //매치하는지 안하는지 여부를 boolean값으로 리턴
- . 하나의 모든 문자
- [a-zA-Z0-9] 범위
- en$ // en으로 끝나야 한다.
- ^ab // ab로 시작해야 한다.
- [^0-9] //not의 의미, 숫자로 시작하면 안된다.
- [0-9]? // 0 or one
- [0-9]+ // one or more
- [0-9]* // zero or more
- [2, 5] //최소횟수, 최대횟수
- {3}
- \\\d 숫자가 와야 한다.
- \\\D 



### 4. 표준출력

1.5버전 이전에 한글을 포함한 키보드 입력을 받으려면 

java.io.InputStream 바이트 최상위 스트림은 추상클래스

System.in 운영체제에 맞게 InputStream 구현 객체

문자 스트림은 XXXReader, XXXWriter

```java
try{

BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );

String s = br.readLine();

//Integer.parseIns(s);

}catch(IOException e){

finally{

	try{

br.close();

}catch(IOException e);

}
```



```java
Scanner scan = new Scanner(System.in)

scan.Next)()

scan.nextLine()

scan.nextInt()

....
```



1.6버전에서는 System.console()

=======================================================================================