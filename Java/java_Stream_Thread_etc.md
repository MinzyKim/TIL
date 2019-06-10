# JAVA 12일차 

# *<저번시간 복습>*

### 0. Calendar Calss

- 날짜 데이터처리하면 날짜 데이터 표현

```java
Date d = new Date();
d = new Date(long millisecond)
    
Calendar cal = Calendar.getInstance(); //java.util 패키지에 있음
```

- 현재 월 => cal.get(Calendar.MONTH) + 1;



- 5월 30일 설정 => cal.set(2019, 4, 31);

- 6월 1일 만들땐 cal.add(Calendar.DATE, 1);

- 날짜 데이터를 특정 형식으로 문자열화하려면 :  yyyy-mm-dd hh:mm:ss

  ```java
  java.text.SimpleDateFormat
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
  Date d = new Date();
  sdf.format(d);
  ```



### 1. Text Pakage



- 숫자 데이터를 특정 형식으로 문자열화하려면

  ```java
  java.text.DecimalFormat
  double won = 12345.678
  DecimalFormat df = new DecimalFormat("\u00A4##")  
  ```

- 특정 형식으로 문자열화된 데이터를 숫자로 변환하려면

  df.parse(s)



- jdk 8 java.time 패키지가 추가
- LocalDate today = LocalDate.now()
- get()으로 Month값 반환받을 때 1~12값 반환
- LocalTime = LocalTime.now()



## 2. Collection (데이터 집합, 자료 구조)

### Framework(표준화된 설계)

배열 - 생성시에 배열의 크기를 반드시 설정해야 하고, 저장될 요소의 크기가 정적이다.

Collection - 생성시에 저장될 요소의 크기를 설정하지 않아도 되고, 동적으로 할당이 가능하다.



##### *Collection - List, Set,Map

- List<Book> - 저장한 순서 보장, 중복된 객체 저장, 인덱스(offset)으로 저장된 요소
  - 종류 - ArrayList, Vector, LinkedList, Stack
  - add(객체), add(index, 객체)
  - clear(), removeAll()
  - remove(객체), remove(index)
  - size()
  - contains()
  - get(index)

```java
iterateor - iterator()
while(iterator.hasNext()){
Book b = iterator.next()
}
Enumeration - hasMoreElement().nextElement()
```

​        *Stack - LIFO구조, push(객체), pop(), peek()*

   	*Queue인터페이스 (1.5) - FIFO*



- Set 

  중복 객체 저장 불가, 순서 보장 안됨

  - HashSet
  - TreeSet
  - 사용하는 Method()
    -  add()
    - remove(), removeAll()
    - constains()
    - toArray()
    - size()

  ```java
  Iterator로 요소 접근 -Iterateor = iterator()
  while(iterator.hasNext()){
  	Book b = iterator.next()
  }
  ```

  

- Map - key객체와 value객체를 매핑해서 저장
  - put(key객체, value객체)

  - keySet()-Set타입

  - Map.Entry타입(인터페이스 타입) - Map에 저장되어 있는 key와 value의 쌍으로 리턴

  - Map의 요소를 꺼내서 처리하는 방법

    - 키 집합을 리턴받고 - keySet()

    - 키 집합에 대한 Iterateor 생성

    - Iterateor로 키를 꺼내서 map에 저장된 Value객체를 꺼냄. 

    - get(Key)

         values()



## *<오늘 수업>*

## 0. I.O 입출력

### 	0.1 Stream(스트림)

 #### 0.1.1 Stream이란?

Stream이란 데이터를 운반하는데 사용되는 연결통로이다. 입력과 출력을 동시에 수행하려면 입력스트림(Input Stream)과 출력스트림(OuputStream), 2개의 스트림이 필요하다. 연속적으로 데이터를 주고 받으며 Queue같은 FIFO구조로 되어있다.

```java
InputStream is = new InputStream(); X
InputStream is = new FileInputStream(); O
InputStream is = System.in; O

OutputStream os = new OutputStream(); X
OutputStream os = new FileOutputStream(); O
OutputStream os = System.out; //PrintStream
```

#### 0.1.2 바이트기반 스트림(InputStream, OutputStream)

	#### 	0.1.2.1 종류

1. FileInputStream - 파일

2. ByteArrayInputStream - 메모리(byte배열)

3. PipedInputStream - 프로세스(프로세스간의 통신)

4. AudioInputStream - 오디오장치

   

#### 	0.1.2.2 InputStream과 OutputStream

- 모든 바이트기반의 스트림의 조상이다.



	####     0.1.2.3 FileInputStream과  FileOutputStream

- 파일에 입출력을 하기 위한 스트림

  ```java
  <FileOutputStream 예제> 
  public class FileOutputStream { 
  	public static void main(String[] args) {
  		FileInputStream fis = null;
  		DataInputStream dis = null;
  		FileOutputStream fos = null;
  		DataOutputStream dos = null;
  		try {
  			File f = new File("c:/test/dataOut.txt");
  			if(!f.exists())
  				f.mkdirs();
  			fos = new FileOutputStream("c:/test/fileout.txt");
  			String message = "Hello FileOutputStream!!";
  			fos.write(message.getBytes());
  			System.out.println("file 쓰기 완료!!!");
  		}catch(FileNotFoundException fnfe) {
  			fnfe.printStackTrace();
  		}catch(IOException ie) {
  			ie.printStackTrace();
  		}finally {
  			try {
  				if(fos != null) fos.close();
  			}catch(IOException ioe) {
  				ioe.printStackTrace();
  			}
  		}
  	}
  }
  ```

#### 

#### 0.1.3 보조스트림

- 스트림의 기능을 보완하기 위해서 제공된다.

- 실제 데이터를 주고받는 스트림이 아니라 데이터를 입출력하는 기능은 없지만, 스트림의 기능을 향상시키거나 새로운 기능을 추가할 수 있다.

  #### 0.1.3.1 FilterInputStream과 FilterOutputStream

  - InputStream과 OutputStream의 자손이며 모든 보조스트림의 조상이다. 

  #### 0.1.3.2 BufferedInputStream과 BufferedOutputStream

  - 입출력의 효율을 높이기 위해 버퍼를 사용하는 보조스트림.
  - 한 바이트씩 출력하는 것 보다 버퍼단위로 한 번에 여러 바이트를 입출력하기 위해서 사용한다.

  #### 0.1.3.3 DataInputStream과 DataOutputStream

  - 8가지 기본 자료형의 단위로 읽고 쓸 수 있다.

  - 예를 들어 int값을 출력한다면 4byte의 16진수로 출력된다.

    ```java
    <DataOutputStream 예제>
    public class DataOutputStreamEx {
    	public static void main(String[] args) {
    	FileInputStream fis = null;
    	DataInputStream dis = null;
    	FileOutputStream fos = null;
    	DataOutputStream dos = null;
    	
    	try {
    		fos = new FileOutputStream("c:/test/fileout.txt");
    		dos = new DataOutputStream(fos);
    		dos.writeBoolean(false);
    		dos.writeInt(20000);
    		dos.writeChar('T');
    		dos.writeDouble(290.45);
    		System.out.println("자바 기본값을 바이트 코드로 저장!!");
    		fis = new fileInputStream("c:/test/fileout.txt");
    		dis = new DataInputStream(fis);
    		System.out.println(dis.readBoolean());
    		System.out.println(dis.readInt());
    		System.out.println(dis.readChar());
    		System.out.println(dis.readDouble());
    	}catch (IOException ioe) {
    		ioe.printStackTrace();
    	}finally {try {
    		if(fos != null) fos.close();
    	}catch(IOException ioe) {
    		ioe.printStackTrace();
    	}
    }
    }
    }
    ```

  #### 1.1.3.4 그 밖의 보조스트림

  - SequenceInputStream - 여러 개의 입력스트림을 연속적으로 연결해서 하나의 스트림으로부터 데이터를 읽는 것과 같이 처리할 수 있게 한다.

  - PrintStream - print, println, printf와 같은 메서드를 오버로딩하여 제공한다.

    

#### 0.1.4 문자기반 스트림(Reader, Writer)

	#### 	0.1.4.1 Reader와 Writer

  - 바이트기반 스트림의 InputStream과 OutputStream의 역할을 한다.

  - byte배열 대신 char배열을 사용한다.

  - 898p에 다양한 메소드가 있다.

    

#### 	0.1.4.2 FileReader와 FileWriter

- 파일로부터 텍스트데이터를 읽고, 파일에 쓰는데 사용된다.

  

#### 	0.1.4.3 PipedReader와 PipedWriter

- 쓰레드 간에 데이터를 주고받을 때 사용한다.

- 입력, 출력스트림을 하나의 스트림으로 연결(connect)해서 데이터를 주고 받는다.

  ```java
  public class PipedReaderWriter {
  
  	public static void main(String[] args) {
  		// TODO Auto-generated method stub
  		InputThread inThread = new InputThread("InputThread");
  		OutputThread outThread = new OutputThread("OutputThead");
  
  		inThread.connect(outThread.getOutput());
  
  		inThread.start();
  		outThread.start();
  	}
  }
  
  class InputThread extends Thread {
  	PipedReader input = new PipedReader();
  	StringWriter sw = new StringWriter();
  
  	InputThread(String name) {
  		super(name);
  	}
  
  	@Override
  	public void run() {
  		try {
  			int data = 0;
  
  			while ((data = input.read()) != -1) {
  				sw.write(data);
  			}
  			System.out.println(getName() + " received : " + sw.toString());
  		} catch (IOException e) {
  		}
  	}
  
  	public PipedReader getInput() {
  		return input;
  	}
  
  	public void connect(PipedWriter output) {
  		try {
  			input.connect(output);
  		} catch (IOException e) {
  		}
  	}
  }
  
  class OutputThread extends Thread {
  	PipedWriter output = new PipedWriter();
  
  	OutputThread(String name) {
  		super(name);
  	}
  
  	@Override
  	public void run() {
  		try {
  			String msg = "Hello";
  			System.out.println(getName() + " sent :" + msg);
  			output.write(msg);
  			output.close();
  		} catch (IOException e) {
  		}
  	}
  
  	public PipedWriter getOutput() {
  		return output;
  	}
  
  	public void connect(PipedReader input) {
  		try {
  			output.connect(input);
  		} catch (IOException e) {
  		}
  	}
  }
  ```

  

## 1. Thread(쓰레드)

	### 	1.1 프로세스와 쓰레드

#### 1.1.1 멀티태스킹과 멀티쓰레딩

- 다중작업을 지원하여 하나의 프로세스 내에서 여러 쓰레드가 동시에 작업을 수행하는 것.



