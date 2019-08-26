# Scala

## 1. 특성

1. Hadoop 기반의 데이터 인프라는 자바 언어를 통해 MapReduce 연산 그리고 알고리즘을 구현
   - 자바는 코드가 너무 길어 생산성과 가독성이 떨어진다.

2. 스칼라는 모든 것들이 일관성있게 그리고 간결하게 구현되도록 설계
   - 적은 양의 코드로 방대한 규모의 시스템을 작성할 수 있다.
   - 스칼라에서는 모든 것이 Object이기 때문에 ==로 모든 비교가 가능

3. 스칼라는 객체지향 프로그래밍과 함수형 프로그램을 모두 완벽하게 지원하는 언어
   - 스칼라에서는 모든 것이 객체이며 함수가 first Object 이다.\

```scalar
그 외...
코드의 직관성과 신축성
풍부한 표현식과 연산자
간결함
자바와의 혼용 가능 객체지향 + 함수형 언어
동시성이 강한 언어
expression중심 언어
필요할때 implicit예약어를 사용하면 명시적인 표현을 감춰버릴 수 있다.
```



## 2. Example

```scala
object Ex1 {
    def main(args: Array[String]): Unit = {
        println("Hello, Scala!")
    }
}
```

- 모든 것이 객체, object는 싱글턴 객체
  - object예약어는 클래스 자체를 싱글턴 객체로 만듬
- 함수는 def로 정의
  - 접근 제한자 없음
  - Unit은 자바의 void와 동일
- 문장 끝에 세미콜론이 없음

### 2.1 변수 선언

```scala
scala> 1
scala> var a = 1
a: Int = 1
scala> var b = 1
b: Int = 1
scala> var c:Int = 0
c: Int = 0
scala> 1 to 10
scala> (1 to 10).toList
res3:List[int] = List(1,2,3,4,5,6,7,8,9,10)
scala> 1.toDouble #형변환
res4: Double=1.0
scala> 1.0.toInt #형변환
res5: Int=1
```



### 2.2 접두어

```scala
scala> val name = "David"
scala> println(s"Hello! ${name}")

scala> val name = "David"
scala> val name = "David"
```

- 접두어 s는 ${변수명}을 이용하여 문자열안의 변수를 값으로 치환하여 출력

```scala
scala> val height:Double = 182.3
scala> val name="James"
scala> println(f"$name%s is $height%2.2f meters tall)
James is 182.30 meters tall
```

- 접두어 f는 

```scala
scala> s"가\n나"
scala> raw"가\n나"
```

- 접두어raw는 문자처리하지 않고 원본 문자를 출력

```scala
object Ex_Strings extends App {
    var str1 = "Hello World!"
    println(str1)
    
    var str2 = """Hello
    	World"""
    println(str2)
    
    //s 접두어
    //""" """
    
scala>    var str3 = s"println $str1"
scala>    println(str3)
scala>    println(s"2*3 = ${2 * 3}")
scala>    def minus(x: Int, y: Int) = x - y
scala>    println(s"${Math.pow(2,3)}")
scala>    println(s"${minus(2,3)}")
}
```

### 2.3 Range타입

```scala
scala> type Name = String
scala> type Person = (String, Int)
scala> type Ftype = String => Int
scala> val name: Name = "홍길동"
scala>	val person: Person = ("korea", 24)
scala> val f:Ftype = text => text.toInt
```

- Range타입 - 1 to 10, 1 to 10 by 2, 리스트나 배열 타입으로 형변환 해야 함
- type예약어는 자료형이 복잡한 경우 별칭을 주어 쉽게 쓸 수 있게 함

- 함수도 객체

### 2.4 조건문, 반복문

- 조건문 : if / else
- 반복문 : for, while, do while

```scala
for ( x<- 1 to 10){
    //반복할 실행문
    println(x)
} // 1~10
 
for( x <-1 until 10){
    println(x)
} // 1~9

//조건이 있는 반복문
for(i<-10)  if ( i % 2 == 0){
    println(i)
} // Error
for(i<-1 to 10) if(i%2 == 0){
    println(i)
}
for((num, index)<-lst.zipWithIndex){
    println(s"$index : $num")
}
```

- 이중for문

```scala
object Ex2 {
    def main(args: Array[String]): Unit = {
      for(x<-1 until 5 ; y <- 1 until 5){
          print(x+"*"+y+"="+x*y + "|" )
      }
    }
}
```



### 2.5 함수

```scala
def 함수명([매개변수]): [반환 자료형] = {
    //구현할 로직
}
```

- 변수와 마찬가지로 :을 이용해 반환 자료형을 정의하며 반환 자료형이 함수의 자료형을 결정
- 명시적으로 return을 생략하는 경우, 함수 선언하는 곳에도 똑같이 생략해주어야 함
- return을 사용할 땐 타입을 함께 선언

```scala
scala> def name() = {
    val a= 10
    a
}
scala> name()

scala> def name2() : Int = {
    val a = 10
    return a
}
}
scala> name2()

scala> def name3() = { //error
	val a = 10
    return a
}
On line 3 : error: method name3 has return statement; needs result type

scala> def name3() : Int { 
	val a = 10
    return a
}
}
```

```scala
scala> def addOne(m: Int): Int = m + 1
scala> val three = addOne(2)
scala> def three() = 1+2
scala> three()
scala> three
```

- 인자가 없는 함수의 경우 호출시 괄호를 생략할 수 있음

```scala
scala> (x: Int) => x+1
scala> 인터프리터가 부여한 이름(1)
scala> val addOne = (x:Int) => x+1
scala> addOne(1)
```

- 이름 없는 함수를 만들 수 있음
- 이름 없는 함수를 다른 함수나 식에 넘기거나 val에 저장
- 함수가 여러 식으로 이루어진 경우, {}를 사용해 이를 위한 공간을 만들 수 있음

```scala
scala> def timesTwo(i: Int): Int = {
    println("hello world")
    i * 2
}

scala> { i:Int =>
	println("hello world")
	i * 2
}
```

```scala
scala> def adder(m: Int, n: Int) m+n
scala> val add2 = adder(2, _:Int)

scala> add2(3)
```

- _  : 아무거나 들어갈 수 있는 마법의 문자

```scala
object Ex3 {
    def main(args: Array[String]): Unit = {
        val thisYear=2019
        val fixedValueFunction=go(thisYear, _:String)
        
     	fixedValueFunction("text1")
        fixedValueFunction("text2")
        fixedValueFunction("text3")
    }
    
    def go(thisYear:Int, string: String)={
        println(string + ":" + thisYear)
    }
})
```

```scala
object Ex4 {
    def main(args: Array[String]): Unit = {
        val g = f _
        println(f(1))
    }
    def f(i:Int) = 1
}
```

- 재귀호출

```scala
object Ex5 {
    def main(args: Array[String]): Unit = {
        val result = calc( x=> x*x, 2, 5)
        println(result)
}
    def calc(f: Int => Int, start: Int, end: Int) = {
        def loop(index: Int, sum:Int): Int= {
            if(index > end) sum
            else loop(index +1, f(index)+sum)
        }
        loop(start, 0)
    }
```

- 매개변수가 여러개인 함수

```scala
def printlnStrings(args: String*) = {
    for(arg <- args){
        println(arg);
    }
    
}
	
printlnStrings("st1", "st2", "st3")
printlnStrings( )
```

- 매개변수의 기본값 설정

```scala
def default(a: Int = 4, b: Int = 5) : Int = a+b

println("기본값은 "+default())
println("기본값은 "+default(11,6))
```

- apply

```scala
class SomeClass {
    def apply(m: Int) = method(m)
    def method(i: Int)= {
        println("method(Int) called")
        i+i
    }
    def method2(s: String) = 5
}

val something = new SomeClass
println(something(2))
```

### 2.6 암묵적 형변환

```scala
object Sample {
    def main(args: Array[String]): Unit = {
        case class Person(name:String)
        implicit def stringToPerson(name:String) :Person = Person(name)
        def sayHello(p:Person) : Unit = {
            print("Hello"+p.name)
        }
        sayHello("korea")
    }
}
```

### 2.7 상속과 메소드 Overloading

- 반드시 하위에서 상위 선언

```scala
scala> abstract class Shape {
    def getArea(): Int
}

scala> class Circle(r: Int) extends Shape {
    def getArea():Int = { r*r*3 }
}

scala> val s = new Shape //error
scala> val c = new Circle(2)
```

### 2.8 Trait

- 하나의 완성된 기능이라기보다는 어떠한 객체에 추가될 수 있는 부가적인 하나의 특징
- 클래스의 부가적인 특성으로 동작, 자체로 인스턴스화는 가능하지 않다

```scala
scala> class Car(brand: String)

scala> class Shiny(shineRefraction: Int)

scala> class BMW extends Car {
    val brand="BMW"
}

scala> class BMW extends Car, Shiny { //다중상속 nonerror
    val brand="BMW"
}

////////////////////////////////////////////////////////////////////////////////////////

scala> trait Car {
    val brand: String
}

scala> trait Shiny {
    val shineRefraction: Int
}

scala> class BMW extends Car {
    val brand="BMW"
}

scala> class BMW extends Car, Shiny { //다중상속 error
    val brand="BMW"
}

scala> class BMW extends with Shiny{
scala> val brand = "BMW"
scala>  val shineRefraction = 12
} 
```

- 추상클래스 대신 trait을 사용해야 하는 이유
  - 추상클래스와 다르게 다중상속 가능
  - 동일한 메서드를 지닌 트레이트가 모두 상속이 되어 충돌이 발생했을 때, 구현 방식에 따라 한가지를 선택하거나 트레이트 쌓기를 통해 동일 이름의 메서드들의 우선순위를 결정해 쌓아두고 하나씩 실행하기도 함



```scala
abstract class Robot {
    def shoot="뿅뿅"
}

trait M16 extends Robot {
    override def shoot = "빵야"
}

trait Bazooka extends Robot {
    override def shoot="뿌왕뿌왕"
}

trait Daepodong extends Robot {
    override def shoot = "콰르르르릉"
}

class Mazinga extends Robot with M16 with Bazooka with Daepodong
val robot = new Mazinga
println(robot.shoot)
```

- 최종적으로 상속받는 메소드가 수행되도록 함



```scala
abstract class AnotherRobot {
    def shoot="뿅뿅"
}

trait AnotherM16 extends AnotherRobot {
    override def shoot = super.shoot + "빵야"
}

trait AnotherBazooka extends AnotherRobot {
    override def shoot = super.shoot + "뿌왕뿌왕"
}

trait AnotherDaepodong extends AnotherRobot {
    override def shoot = super.shoot + "콰르르르릉"
}

class SuperMazinga extends AnotherRobot with AnotherM16 with AnotherBazooka with AnotherDaepodong

val robot = new SuperMazinga
println(robot.shoot)
```

### 2.9 Any

```scala
def matchFunction(input: Any): Any = input match {
    case 100 => "hundred"
    case "hundred" => 100
    case etcNumber: Int => "입력값은 100이 아닌 int형 정수입니다."
    case _ => "기타"
}

println(matchFunction(100))
println(matchFunction("hundred"))
println(matchFunction(1000))
println(matchFunction(1000.5))
println(matchFunction("thousand"))
```



```scala
case class Person(name: String, age: Int)
val alice = new Person("Alice", 25)
val bob = new Person("Bob", 32)
val charlie = new Person("Charlie", 32)

for(person <- List(alice, bob, charlie)){
    person match {
        case Person("Alice", 25) => println("Hi Alice!")
        case Person("bob", 32) => println("Hi Bob!")
        case Person(name, age) => println("Age:"+age+" year, name: "+name + "?")
    }
}
```

### 2.10 Extractor로 패턴 매칭

```scala
object Emergency{
    def unapply(number: String): Boolean = {
        if (number.length == 3 && number.forall(_.isDigit)) true
        else false
    }
}

object Normal {
    def unapply(number: String): Option[Int] = {
        try {
            var o = number.replaceAll("-", "")
            Some(number.replaceAll("-","").toInt)
        }catch{
            case _: Throwable => None
        }
    }
}
```

```scala
val number1 = "010-222-2222"
val number2 = "119"
val number3 = "포도 먹은 돼지"
val numberList = List(number1, number2, number3)
for(number <- numberList){
    number match {
        case Emergency() => println("긴급전화입니다.")
        case Normal(number) => println("일반전화입니다 -"+number)
        case _ => println("판단 할 수 없습니다.")
    }
}
```

### 2.11 컬렉션



## 3. scala 컴파일하기

![1566785024248](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1566785024248.png)