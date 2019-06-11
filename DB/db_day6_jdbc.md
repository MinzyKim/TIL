*<지난시간 복습>*

## 0. DDL - Alter

### 	0.1 테이블 컬럼 추가

```sql
alter table ~ add (컬럼 컬럼타입 [제약조건]);
```

### 	0.2 테이블 컬럼 삭제

```sql
alter table ~ drop colunm 컬럼명;
alter table ~ drop (컬럼, 컬럼);
```

### 	0.3 테이블 컬럼 이름 변경

```sql
alter table ~ rename column old to new;
```

### 	0.4 테이블 컬럼타입 또는 size 변경

```sql
alter table ~ modify (컬럼 컬럼타입(size))
```

### 	0.5 제약조건을 컬럼 추가

```sql
alter table ~ add constraint 이름 타입 ;
```

### 	0.6 컬럼에 정의되어 있는 제약조건 삭제

```sql
alter table ~ drop constraint 이름;
```

### 	0.7 제약조건을 활성화

```sql
alter table ~ enable constraint 이름;
```

### 	0.8 제약조건을 비활성화

```sql
alter table ~ disable constraint 이름;
```



## 1. DDL - Drop

### 	1.1 테이블 삭제

```sql
drop table ~ ;
drop table ~ purge;
```

### 	1.2 recyclebin으로부터 drop된 테이블 복원

```sql
flashback table ~ to before drop;
```

### 	1.3 테이블의 구조는 남겨두고 데이터만 물리적으	로 완전시키고, 사용했던 공간 할당 해제하지 않기 	위해 사용하는 명령어

```sql
truncate table ~;
truncate table ~ reuse storage;
```



## 2. View

- **정의** : 논리적 테이블, table에 대한 window

- **종류**

  - **Simple View** : DML이 가능한 뷰(base table의 not null 컬럼 모두 포함, 표현식 X, 그룹핑 X, rowid X, rownum X)
  - **Complex View** : DML이 불가능한 뷰(2개 이상의 테이블로부터 join포함, 그룹핑포함, 함수 표현식 등이 포함된 경우) 

- **사용목적**

  : 보안, 간결한 sql 사용

- **생성방법**

  ```sql
  create [or replace] [force | noforce] view 뷰이름
  as select ~
  	from ~
  	[where ~]
  	[group by ~]
  	[having ~]
  	[order by ~]
  	[with check option] -- 체크 제약 조건
  	[with read only] --read only 제약조건
  ```

- **특징**

  - user_views, all_views, dba_views - text컬럼
  - alter view 구문 X
  - drop view 뷰이름 -- 테이블에 영향을 주지 않는다.

  - 테이블을 삭제하면 - 구조, 데이터, 제약조건, 인덱스 함께 삭제
  - 테이블에 대한 view가 존재하는데, 테이블이 drop되면 뷰의 status는 invalid상태로 변경되어 사용 불가

  

## 3. Index

- **정의** : 검색속도를 향상(select 수행 성능향상)을 위해서 사용하는 객체

- **종류**

  - **b*tree index** : root node - branch node - leaf node(컬럼값 rowid형태로 인덱스 엔트리들이 저장되고 컬럼값의 오름차순)
  - **b*map index**
  - **단일 컬럼 인덱스**
  - **복합 컬럼 인덱스**
  - **function based index** : 컬럼표현식의 결과값으로 인덱스 생성

- **사용방법**

  ```sql
  create index ~ on 테이블(컬럼 [desc]);
  alter index ~ on 테이블(컬럼...);
  drop index ~ ;
  ```



## 4. Sequence

- **정의** : 최소값~최대값 범위내에서 설정된 증감값에 따라 정수를 생성 객체

- **사용방법**

  ```sql
  create sequence ~
  [start with ~]
  [increment by ~]
  [minvalue ~ | nominvalue]
  [maxvalue ~ | nomaxvalue]
  [cache n | nocache]
  [cycle | nocycle];
  
  alter sequence ~ ; 로 변경 못하는 속성은 start with
  drop sequence ~;
  ```



## 5. Synonym

- **정의** : 'schema.객체@dblink명' 처럼 긴 객체이름을 간결하게 줄여서 쓰기 위한 객체

- **사용방법**

  ```sql
  create synonym ~ for schema.객체@dblink명;
  ```



## 6. 사용자 권한

- 데이터베이스에 connect하려면 대상 데이터베이스에 user명이 등록되어 있어야 하며, 인증방식도 정의되어 있어야 함

- create session권한이 있어야 함

- **사용방법**

  ```sql
  create user ~ -- 권한은 DBA(sys, system, drop user~로 삭제없음)
  identifide by 비밀번호
  [default tablespace ~]
  [temp tablespace ~]
  [tablespace quota XXM]
  [profile ~]
  [consumer group ~]
  ```

- **권한** : 시스템권한 -- DBA

  ​			객체권한 -- 객체의 소유자, DBA

  ```sql
  grant 권한,... to user명,...,role명,...public;
  *role? 특정 업무, 직무와 연관된 권한들을 그룹핑한 것
  
  revoke 권한,... from user명,...,role명,...public;
  
  *객체 권한 부여
  grant 권한,... on 객체[(컬럼,...)] to user명,...,role명,...public;
  *객체 권한 회수
  revoke 권한,...  on 객체[(컬럼,...)] from user명,...,role명,...public;
  
  roll 생성 권한 - DBA
  1. create role ~;
  2. grant 권한,.... to 롤
  3. grant 롤,..to user명,...,role명,...public;
  ```

  

## 7. JDBC 프로그래밍 단계

- 연결할 데이터베이스의 driver class 클래스 (~.jar)를

  - 운영체제의 환경변수 classpath에 추가

  - JDK또는 JRE의 라이브러리 검색 위치중에 외부 확장 라이브러리 저장 위치 (%JAVA_HOME%jre/ext)
  - 이클립스(IDE)에서 프로젝트의 build path>configure build path>library> add external jar......

- import java.sql.*; -- JDBC API import한다

- DriverClass 로딩

  ```java
  try{
  Calss.forName(" "); //oracle.jdbc.OracleDriver
  }catch(ClassNotFoundException e){
  }
  ```

- Connection 객체 생성

- 로딩된 드라이버 클래스의 static 멤버 객체의DriverManager.getConnection(dburl, user, pwd) 이용해서
  DB에 connect한다.

  - DB에 세션 생성되고, 세션이 리턴된다 => java.sql.Connection객체로 받는다.

  - **Connection 인터페이스에 주요 메소드** : close(), createStatement(), prepareStatement(), callableStatement(), setAutoCommit(false), commit(), rollback(), setSavepoint(),...

- SQL 실행 대행 객체 Statement 객체 생성

  **Statement** - 완전한 sql문장을 문자열로 전송하므로 매번 hard parsing을 수행

  **PreparedStatement** - sql문장 중에서 변경되는 부분들을 ? (index 파라미터)로 설정해서 미리 sql을 전송하고, 실행할 때마다 값만 전송해서 실행(soft parsing으로 수행될 확률이 높다.)

  **CallableStatement** - DB에 저장되어 있는 procedure, function을 호출해서 결과를 받을 때

- SQL문 실행

  executeQuery() - select문장, ResultSet 객체 리턴

  executeUpdate() - DML문은 int(변경된 행수) 리턴, DDL문, DCL문

  execute() - select문장, DML문, boolean 리턴(true일때는 select수행, false일때는 DML수행)

- select 수행 결과 처리

  ```
  while(rs.next()) {
       rs.getInt(컬럼position | "컬럼명"),
       rs.getDouble(컬럼position | "컬럼명"),...
       rs.getString(컬럼position | "컬럼명"),
       rs.getDate(컬럼position | "컬럼명"),
        ...
     }
  ```

- SQLException 예외 처리

- 사용 자원 (Connection, Statement, ResultSet)들 반납 close() ---> 예외처리 필요함

  소스코드에 db연결정보를 hard coding하는것은 보안상..문제가 되므로
  보안 폴더에 properties파일에 key=value형태로 저장

  ```java
  Properties prop = new Properties();
  prop.load(new FileInputStream("경로/이름.."));
  String value = prop.getProperty("key");
  ```

  
