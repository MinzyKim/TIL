## 0. 순위 관련 함수

- rank() , over() (partition by 컬럼 order by 컬럼 rows(행기준) | range(값기준) unbounded preceding | between found unbounded following | n preceding | n following)
- dense_rank() - 
- row_number()



## 1. 집계관련 window 함수

- sum(), min(), max(), avg(), count()



## 2. 행순서 관련 함수

- first_value()
- last_value()
- lag(컬럼, n, null 대체값)
- lead(컬럼, n, null 대체값)



## 3. DML

 ### 	3.1 새데이터추가

```sql
insert into 테이블명 (컬럼명 리스트) values (컬럼명 리스트의 순서와 타입에 맞는 값);
```

```sql
insert into 테이블명 values (테이블에 선언된 컬럼순서대로 모든 값);
```

- *values절에 null, default, 단일행함수 사용가능*

```sql
insert into 테이블명 subquery;
```

- *컬럼명 리스트는 subquery의 컬럼순서, 개수, 타입과 일치해야 한다. *
- **insert 오류** 
  - 컬럼타입 불일치
  - 컬럼크기 불일치
  - 제약조건 오류



### 	3.2 컬럼 값 변경

```sql
update 테이블명 set 컬럼명=변경할 값 [, 컬럼명=변경할 값,...];
```

```sql
update 테이블명 set 컬럼명=변경할 값 [, 컬럼명=변경할 값,...] where 조건;
```

```sql
update 테이블명 set 컬럼명=(subquery) [, 컬럼명=변경할 값,...] where subquery;
```

- **update오류**
  - 컬럼타입 불일치
  - 컬럼크기 불일치
  - 제약조건 오류
- 변경할 값에 null, default, 단일행함수 사용가능



### 	3.3 테이블의 행 삭제

```sql
delete from 테이블명; -- 모든 행 삭제
```

```sql
delete 테이블명; -- oracle에선 from 삭제
```

```sql
delete from 테이블명 where 조건; --조건을 만족하는 행만 삭제
```

```sql
delete from 테이블명 where (subquery);
```

* 참조무결성제약조건 오류 : 참조하는 자식 레코드가 존재하면 부모 레코드는 삭제 할 수 없다.

  예) 부서테이블의 레코드를 삭제하려면 소속 사원이 없는 부서정보 레코드만 삭제 가능



### 	3.4 Merge문

- ETL작업에 사용되는 하나의 DML로 insert, update, delete수행

```sql
merge into 대상테이블 t
using 소스테이블 s
on ( s.pk컬럼 = t.pk컬럼 )
when mathced then
	update set t.컬럼 = s.컬럼, ...
	delete where 조건
when not mathced then
	insert (t.컬럼, t.컬럼,...)
	values (s.컬럼, s.컬럼,...);
```



### 	3.5 TCL(Transaction Control Language)

- Transaction - Unit or Work, all or nothing, ACID(Atonomity, Continuity, Isolation, D)
- DB에서 Transaction 단위 - 하나 이상의 DML, 하나의 DDL, 하나의 DCL
  -  하나 이상의 DML로 이루어진 트랜잭션은 명시적으로 commit; 또는 rollback; 해야 한다.
- 트랜잭션 수행 중에 DB 연결된 세션 정상 종료(exit;)할 경우 oracle server는 트랜잭션을 commit
- 트랜잭션 수행 중에 DB 연결된 세션 비정상 종료 할 경우 oracle server는 트랜잭션을 rollback
- 긴 트랜잭션 경우 rollback을 일부 할 수 있다.
  - savepoint 식별자;, rollback to savepoint 식별자;



- 읽기 일관성 - 변경 중인 user는 자신이 변경중인 값이 조회되고, 변경중이지 않는 user들은 DB에 이전에 commit되서 저장된 값을 조회
  - Lock(변경 중인 유저가 사용)과 undo data(변경중이지 않은 유저데이터)를 이용해서 읽기 일관성  보장
  - undo data는 트랜잭션을 rollbavk을 하면 변경전값을 undo segment로부터 restore(복원) 해준다.



### 	3.6 데이터베이스의 객체

- table - 구조, 물리적 data (Record + Column)

   			heap, partition, IOT(Index organized Table), clustered, ...종류

- view - table에 대해서 select로 정의된 table의 window역할

  ​			보안, 간결한 select문 사용을 위해서 

  ​			base가 되는 table이나 view가 있어야 한다.

  ​			예외 ) MeterializedView - 성능향상이 목적인 물리적 data를 가지는 						View

- index - 테이블의 컬럼에 생성

  ​			where절에 검색조건으로 사용되는 컬럼, join 컬럼, order by 절의 컬럼

  ​			내부적으로 oracle server가 select 수행시 사용

  ​			b*tree구조로 저장

- sequence - 순차적으로 숫자값이 저장되어야 하는 컬럼(주문번호, 게시판의 글번호등)의 값을 자동으로 발행해주는 객체

  ​					최소값, 최대값, 증감값 설정

- synonym(동의어) - schema명, 객체@dblink명과 같은 객체이름을 간결하게 사용하기 위한 동의어



## 4. DDL

 ### 	4.1 테이블 생성

```sql
create table 테이블명 (
컬럼명 컬럼타입(크기) 제약조건|default 기본값,
...)
[tablespace 저장소명
storage...];

테이블 생성을 위해 필요한 권한 - create table, tablespace에 대한 quota가 할당되어 있어야 함
```

- 테이블명, 컬럼명 이름규칙

  - 대소문자 구별 안함 - Data Dictionary에는 대문자로 저장됨
  - 첫문자로 영문자, _, $, # 허용
  - 두번째 문자부터 숫자 허용
  - 키워드 허용 불가
  - 동일 schema내에서 같은 이름의 객체 안됨
  - 길이제한 30자 ( 데이터베이스이름 길이제한 8자 )

- schema - 서로 연관된 객체들을 그룹핑, 오라클에서는 user명을 schema명으로 사용함

  ​				user소유의 객체들을 그룹핑해서 다른 user소유의 객체들을 구별하는 namespace역할을 하면서 동일한 이름의 객체를 다른 schema에서 사용가능

  ​				schema명.객체명

-  *컬럼타입*
  - char

  - varchar2

  - number

  - date

  - timestamp

  - timestamp with timezone

  - interval year to month
  - interval day to second
  - Bfile

  - BLOB ( LONG RAW )

  - CLOB ( LONG )

  - RAW

  - rowid - 행주소 (objectid + fileid + blockid + 행순서번호)

```sql
create table 테이블명 (컬럼명 리스트)
	as select ~
	from 
	[where~]
	...;
```

*select절의 컬럼 리스트와 create table 절에 선언된 컬럼명 리스트의 순서 개수, 타입이 일치해야 한다.*



- 테이블의 구조 복제

  ```sql
  create table 테이블명
  	as select ~
  	from 
  	where 1=2; --false조건
  ```

  

- *제약조건(constraint) : DML수행시 컬럼값의 허용 또는 제한규칙*
  - primary key - unique + not null, 테이블에 하나만 정의 가능
  - not null - null허용 안함, 컬럼레벨에서만 제약조건 선언 가능
  - unique - 중복값을 허용하지 않음, oracle은 null은 unique값으로 취급해서 여러개 허용
  - check - 특정값의 허용 범위 
  - foreign key

```sql
create table emp2 (
empno  number(4),
ename varchar2(15) [constraint 이름] not null,  ---컬럼 레벨
hiredate date,
job  varchar2(15),
sal  number(8, 2),
constraint emp2_pk  primary key (empno, ename) ---테이블 레벨
);
```

- 컬럼에 index가 자동 생성되는 제약조건 - primary key, unique key
  - 제약조건 메타 정보 조회 - user_constraints, dba_constraints
  - 테이블의 메타 정보 조회 - user_tables (tab), all_tables, dba_tables
  - 컬럼 메타 정보 조회 - desc 테이블명 / user_tab_columns
  - 인덱스 메타 정보  조회 - user_indexes, user_ind_columns

- 제약조건 예

  ```sql
  create table category (
  cid   number(5),
  cname   varchar2(20) 
  );
  insert into category values (10000, 'BOOK');
  insert into category values (20000, 'Music');
  insert into category values (30000, 'Game');
  insert into category values (40000, 'Movie');
  
  select * from category;
  
  create table product (
  prodid   number(5),
  pname    varchar2(50),
  price    number(6),
  cid      number(5) constraint product_fk references category(cid)
  );   --error
  
  foreign key제약조건이 참조하는 부모 컬럼에는 primary key 또는 unique key 제약조건이 선언
  
  alter table category add constraint category_pk  primary key (cid);
  
  create table product (
  prodid   number(5),
  pname    varchar2(50),
  price    number(6),
  cid      number(5) constraint product_fk references category(cid)
  );
  
  select constraint_name, constraint_type
  from user_constraints
  where table_name = 'PRODUCT';
  
  insert into product values (1, 'java', 5000, 10000);
  insert into product values (2, 'oracle', 5000, 50000);  --error
  insert into product values (3, 'BTS', 15000, 20000);
  update product 
  set cid = 2222 where prodid = 3;   ---error
  
  delete from category where cid = 40000;    
  delete from category where cid = 10000;  ---error
  update category set cid = 15000 where cid = 10000;  ---?
  
  
  
  create table product (
  prodid   number(5),
  pname    varchar2(50),
  price    number(6),
  cid      number(5) ,
  constraint product_fk foreign key (cid) references category(cid)  -- on delete cascade 또는 on delete set null
  );
  ```

- PK와 UK에 index 자동 생성 목적 - 정합성 체크, 중복값 체크를 빠르게 수행

- index 생성에 적합한 조건

  - ```
    where 조건에 사용되는 컬럼 
    join 컬럼
    order by 컬럼
    컬럼중에서 distinct value(선택도)값이 많아야 함 
    where절의 = 연산조건의 결과 행이 5%이내 
    인덱스 생성 컬럼으로 조회 결과 행수가 10%를 초과하면 손익분기점으로 table full scan이 더 유리
    거의 update가 발생하지 않는 컬럼 - 자주 update되는 컬럼은 인덱스 생성하면 성능 저하
    4개 블럭이상에 데이터가 저장된 테이블
    ```

  - 단일 컬럼 인덱스

  - 복합 컬럼 인덱스

  - unique 인덱스

  - non unique 인덱스

  - funcation-based 인덱스 (컬럼값의 내림차순으로 생성, 컬럼표현식)



### 	4.2 테이블 변경

 ```sql
alter table 테이블명 modify (컬럼 컬럼타입(크기) );
 ```

- 컬럼 타입 변경할 때 컬럼값이 존재하더라도 char5 -> varchar2(10) 변경은 가능
- 컬럼 타입 변경할 때 호환되지 않는 컬럼타입으로 변경할 때는 컬럼값을 null로 변경한 후에 컬럼타입을 변경할 수 있음
- 컬럼 크기를 변경할 때 크기 증가는 항상 가능, 컬럼값이 존재할 때 컬럼 크기를 줄이려면 저장된 컬럼값의 최대 길이보다 작게 줄일 수 없음



```sql
alter table 테이블명 add constraint~;
alter table 테이블명 drop constraint~;
alter table 테이블명  add (컬럼 컬럼타입(크기), 컬럼 컬럼타입(크기),..);
alter table 테이블명 drop (컬럼 컬럼타입(크기), 컬럼 컬럼타입(크기),..);
alter table 테이블명 drop column 컬럼명;
alter table 테이블명 rename column old명 to new명;
alter table 테이블명 enable constraint~;
alter table 테이블명 disable constraint~;
```



	### 	4.3 테이블 제거

```sql
delete table 테이블명; --테이블이름 rename되어 recyclebin에 저장됨... 저장 공간이 부족할 때 oracle server가 제거

delete table 테이블명 purge; --recyclebin을 물리적으로 완전 삭제
purge recyclebin;

truncate table 테이블명[reuse storage]; -- 구조만 남겨두고, date는 완전 삭제(recyclebin에도, undo data도 남기지 않음)

drop table ~ ; --table메타정보, data, 제약조건, index도 함께 삭제
```



