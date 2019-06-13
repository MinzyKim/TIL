*<지난 시간 복습>*

## 0. SubQuery

- select문 내부에 정의 된 select문(inner query, nested query)
- outer query, main query
- 2번 이상 select를 수행해서 결과 집합을 생성해야 할 때, 하나의 select문으로 정의해서 실행시킴



- single row subquery - scalar subquery
- multiple row subquery - multiple column subquery



- subquery가 main query보다 먼저 수행하고, 1번 수행
- co-related subquery(상관관계 subquery) - subquery가 main query의 행수만큼 subquery가 반복적으로 수행하는 Query



- subquery가 올 수 있는 위치
  - select절
  - from절 --inline view
  - where절 --연산자 오른쪽 (subquery)
  - having절 --연산자 오른쪽 (subquery)
  - order by절

- subquery를 where절이나 having절에 정의할 때 sigle row operator(>, >=, <, <=, !=, <>)
- multiple row operator(in, any>, any<, all<, all>)



- subquery에는 모든 select절, 함수등 제약없이 사용 가능하지만,

  order by절은 from절의 inline view에서만 허용됨



- rownum - 결과행에 순차적인 번호를 발행 내장 컬럼
  - 메모리에서 결과집합 만들어놓고, order by전에 발생됨
  - order by후에 rownum을 순차적인 번호를 발행하려면 subquery를 사용해야 함



- *co-related subquery(상관관계 subquery) 

```sql
select~~
from table a
where column 연산자 (select ~
                 from table2
                 where a.column = column2) -- 후보 row를 가져와서 subquery수행 후 반복
```

*co-related subquery에서 존재 유무를 체크해주는 연산자 - exists, not exists



*긴 query문에서 반복적으로 사용하는 subquery를 먼저 정의해서 재사용하려면

```sql
with
별칭 as (subquery),
별칭 as (subquery),
별칭 as (subquery),
...
별칭 as (subquery)
select ~
from ~
where ~
```



- set operator - 서로 다른 select의 결과를 단일 결과집합으로 만들기 위해 쓰는 연산자
  - 합집합 - union, union all
    - union : 각 select의 결과 행에서 중복된 행을 제외하기 위해 sorting
    - union all : append방식 
  - 교집합 - intersect
    - intersect : 각 select의 결과 행에서 중복된 행만 결과로 생성하기 위해 sorting비교
  - 차집합 - minus
    - minus : 첫번째 select의 결과에만 속한 행을 선택하기 위해 sorting비교

```sql
select ~
from ~
[where ~]
[group by ~]
[having]

union | union all | intersect | minus

select ~
from ~
[where ~]
[group by ~]
[having]
```

**각 select문에서 컬럼개수와 컬럼타입이 일치해야 함*

**결과는 첫번째 컬럼값을 기준으로 정렬된 결과가 리턴되므로 다른 컬럼으로 정렬하려면 order by절은 마지막 select문에만 허용*

​	 

```sql
문> 전체 사원의 급여 평균
	부서별 사원의 급여 평균
	부서와 직무별 사원의 급여 평균
	
select to_number(null), to_char(null), avg(sal)
from emp
union all
select deptno, to_char(null), avg(sal)
from emp
group by deptno
union all
select to_number(null), job, avg(sal)
from emp
group by deptno, job
union all
select deptno, job, avg(sal)
from emp
group by deptno, job;
```

- set
  - rollup으론 부족하고, cube로는 넘칠 때 사용한다. 효율이 rollup과 cube만큼 좋진 않지만 가끔 사용

```sql
select deptno, job, avg(sal)
from emp
group by cube (deptno, job);

select deptno, job, mgr, avg(sal)
from emp
group by grouping sets ((deptno, mgr), (mgr), (job), ());
```



## 1. Insert

- 새로운 데이터를 추가하려면 대상 테이블이 insert권한 또는 테이블의 소유자 

  insert into 테이블명 (컬럼명, 컬럼명,...)

  value (컬럼리스트의 순서대로 값...);

  *새로 추가되는 행의 데이터로 일부 컬럼값만 정의할 경우, 필수 컬럼은 반드시 선언



```sql
insert into 테이블명
values (테이블에 정의된 컬럼 순서대로 모든 값이 선언);

insert into dept (dname, loc)
values('IT', 'Seoul');
```

```sql
insert into dept
values(55,'ERP',null);

insert into emp ( empno, ename, deptno, hiredate)
values (9000,'Kim',50, sysdate); --단일값 함수 허용

insert into emp ( empno, ename, deptno, hiredate)
values (9001,'Lee',50, '19년3월2일'); --날짜 포맷 오류

insert into emp ( empno, ename, deptno, hiredate)
values (9001,'Lee',50, '19/03/02'); --to_date()함수 사용

create table emp10
as select * 
    from emp
    where 1=2; --테이블 구조만 복제, CTAS(Create as구분을 이용해서 복제)
    
select * from emp10;

insert into emp10
select * from emp where deptno=10;

select * from emp10;
--values 절 대신 subquery를 선언하면 subquery의 결과 행수만큼 추가

insert into emp10 ( empno, ename, deptno, sal)
select empno, job, hiredate, mgr
from emp where deptno =20; --subquery에서 insert에 선언된 컬럼 개수나 타입과 일치하지 않으면 error
```



## 2. Update

- 테이블에 이미 존재하는 행의 데이터를 수정할때 컬럼단위로 수정

```sql
update 테이블명
set 컬럼명=new컬럼값 [, 컬럼명=new컬럼값, ...]; --테이블의 모든 데이터
```

```sql
select empno, ename, deptno, sal from emp10;
update emp10
set sal = 1;
select empno, ename, deptno, sal from emp10;
rollback; -- 생성되고 난 이후 모든 변경사항 취소
```

```sql
select empno, ename, deptno, sal
from emp;

update emp
set sal =1
where deptno = 30;
```

```sql
update emp
set sal = (select sal
            from emp
            where ename='KING')
where ename='SMITH'; --update의 set절, update의 where절에도 subquery사용 가능

```

```sql
create table customer (
custid number(7),
custname varchar2(15),
point  number(5) defalut 1000
);

select * from customer;

insert into customer (custid, custname)
values (990301,'Kim')

select * from customer;
```

```sql
--테이블에 이미 저장되어 있는 레코드를 삭제하려면
delete from 테이블명 ; -- 전체 행 삭제

delete 테이블 명 ; --오라클에선 from절 생략 가능

delete from 테이블명 where 조건 ; -- 조건을 만족하는 행만 삭제

delete from 테이블명 where 컬럼 연산자 (subquery);

select * from emp;
delete from emp;
select * from emp;
```



## 3. Merge

- 운용계 DB 목적 : 트랜젝션 처리

- insert, update, delete를 한 번에 수행 가능
- 취소 할 땐 rollback 한번만 하면 됨
- ETL작업에 많이 사용 된다.

```sql
merge into 대상테이블 t(allias)
using 소스테이블 s
on t.pk컬럼 = s.pk컬럼
when matched then
update set t.컬럼 = s.컬럼,.....
[delete (from 테이블명 생략)  where 조건]
when not matched then
insert (t.컬럼리스트)
values (s.컬럼, s.컬럼, ...);
```



## 4. Transaction

- Unit of Work (분리되어 수행 될 수 있는 작업단위)
- ACID - 원자성, 일관성, 격리성, 영속성
- DB관점의 Transaction은 변경(DML, DDL, DCL)이 포함되면
- select는 Transaction으로 포함되지 않고



- Transaction 단위
  - 1개 이상의 DML들로 구성 - 명시적 commit, rollback
  - 1개의 DDL - auto commit;
  - 1개의 DCL - auto commit;
- 수행중인 DML 트랜잭션의 세션이 비정상종료하면 oracle server는 rollback한다.
- 수행중인 DML 트랜잭션의 세션이 정상종료(exit;)하면 oracle server는 auto Commit한다.

- 읽기 일관성 - select하는 user들이 변경중인 user 작업이 종료될때까지 기다리리지 않고,

  변경 작업하려는 user들은 select하는 user들이 검색을 종료할때까지 기다리지 않고,

  변경 작업중인 user들은 변경 중인 값을 조회 결과로 볼 수 있고, 변경 중이지 않은 user는 DB에 저장된(commit된) 데이터 값을 조회 결과로 볼 수 있도록 함

- 오라클 서버는 읽기 일관성을 위해서 Lock, undo segment등을 지원함.



## 5. Window Function

 ### 	5.1 rank()

: 특정 컬럼에 대한 순위를 구하는 함수로서 동일한 값에 대해서 동일한 순위를 가지며, 동일한 순위의 수만큼 다음 순위는 건너뛴다. 

```sql
select ename, job, rank() over (order by sal desc) sal_rank
                               ,rank() over(partition by job order by sal desc) job_rank
from emp;
```



### 	5.2 dense_rank()

: 특정 컬럼에 대한 순위를 구하는 함수로서 동일한 순위 다음의 순위는 동일 순위의 수와 상관없이 1 증가된 값을 돌려준다**.**

```sql
select ename, job, dense_rank() over (order by sal desc) sal_rank
                               ,dense_rank() over(partition by job order by sal desc) job_rank
from emp;
```



### 	5.3 Row_Numer()

: 특정 컬럼에 대한 순위를 구하는 함수로서 동일한 값이라도 고유한 순위를 부여한다 (동일한 순위를 배제하기 위해 unique한 순위를 oracle의 경우 rowid가 적은 행이 먼저 나온다.)  PARTITION내의 ROW들에 순서대로 UNIQUE한 일련번호를 부여한다.

```sql
select  ename, job, sal, 
        dense_rank( ) over ( order by sal desc ) sal_rank
        ,  rank( ) over ( order by sal desc ) sal_rank2
        ,  row_number( ) over ( order by sal desc ) sal_rank2
from emp; 
```



### 	5.4 집계함수 관련 window함수

```sql
문> emp 테이블에서 관리자로 파티셔닝된 사원이름, 오름차순 정렬된 급여 데이터 누적 합 출력
select  ename, mgr, sal, sum(sal) over (partition by mgr order by sal) 
from emp;


문> emp 테이블에서 관리자로 파티셔닝된 사원이름, 오름차순 정렬된 급여 데이터 누적 합 출력
select  ename, mgr, sal, 
        sum(sal) over (partition by mgr order by sal
                       range  unbounded preceding) 
from emp;
```



### 	5.5 **ROWS  BETWEEN 1 PRECEDING AND 1 FOLLOWING** 

: 현재 행을 기준으로 파티션 내에서 앞의 한 건, 현재 행, 뒤의 한 건을 범위로 지정한다.

```sql
문> emp 테이블에서 관리자로 파티셔닝된 사원이름, 오름차순 정렬된 급여 데이터의 행 기준 누적 합 출력
select  ename, mgr, sal, 
        sum(sal) over (partition by mgr order by sal
                       rows between unbounded preceding and current row   ) 
from emp;


문> emp 테이블에서 관리자로 파티셔닝된 사원이름, 오름차순 정렬된 급여 데이터의 행 기준으로 현재행의 앞에 한행, 뒤에 한 행의 누적 합 출력
select  ename, mgr, sal, 
        sum(sal) over (partition by mgr order by sal
                       rows between 1 preceding and 1 following   ) 
from emp;
```



### 	5.6 RANGE  BETWEEN 50 PRECEDING AND 150 FOLLOWING 

: 현재 행을 기준으로 급여가 -50에서 +150의 범위 내에 포함된 모든 행이 대상이 된다.(RANGE는 현재 행의 데이터 값을 기준으로 앞뒤 데이터 값의 범위를 표시)

```sql
문> emp 테이블에서 관리자로 파티셔닝된 사원이름, 오름차순 정렬된 급여 데이터의 행 기준으로 급여의 -200~+200 범위의 급여자 수 출력
select  ename, mgr, sal, 
        count(sal) over (order by sal
                         range between 200 preceding and 200 following   ) 
from emp;
```



### 	5.7 그룹 내 행 순서 함수

- FIRST_VALUE 와 LAST_VALUE

  - FIRST_VALUE 

    : 파티션별 윈도우에서 가장 먼저 나온 값을 구한다. 
    다른 함수와 달리 공공 등수를 인정하지 않고 처음 나온 행만을 처리한다.
    PARTION BY에 의해 분류된 범위 내에서 ORDER BY 에 의해 정렬을 한 후 ROWS 또는 RANGE에 의해 범위가 지정되면 그 중 제일 앞에 위치하는 ROW의 값들을 읽어 올때 사용

  - LAST_VALUE

    : 파티션별 윈도우에서 가장 나중에 나온 값을 구한다. 
     다른 함수와 달리 공공 등수를 인정하지 않고 가장 나중에 나온 행만을 처리한다.
     PARTION BY에 의해 분류된 범위 내에서 ORDER BY 에 의해 정렬을 한후 ROWS 또는 RANGE에 의해 범위가 지정되면 그 중 제일 뒤에 위치하는 ROW의 값들을 읽어 올때 사용

    **FIRST_VALUE**와 **LAST_VALUE**는 정렬된 **ROW의 순서**에 의해 값이 결정된다.

  ```sql
  select  ename, mgr, sal, 
          first_value(sal) over (partition by mgr order by sal ) ,
          last_value(sal) over (partition by mgr order by sal ) 
  from emp;
  
  
  select  ename, mgr, sal, 
          first_value(sal) over (partition by mgr order by sal ) ,
          last_value(sal) over (partition by mgr order by sal 
          range between current row and  unbounded following ) 
  from emp; 
  ```

  

- LAG 

  - 파티션별 윈도우에서 이전 몇 번째 행의 값을 가져올 수 있다.

  ```sql
  select  ename, hiredate, sal, 
          lag(sal) over (order by hiredate ) ,
          lag(sal, 2, 0) over (order by hiredate ) 
  from emp;
  
  ```

- LEAD

  - 파티션별 윈도우에서 이후 몇 번째 행의 값을 가져올 수 있다.

  ```sql
  select  ename, hiredate, sal, 
          lead(sal) over (order by hiredate ) ,
          lead(sal, 2, 0) over (order by hiredate ) 
  from emp;
  ```

  

## 6. 객체 종류

- 테이블을 생성하려면 create table 시스템 권한이 있어야 한다.

  tablespace (data container)저장소에 quota가 할당되어 있어야 한다.



- **table 또는 컬럼 이름 규칙** : 
  - 영문자 또는 _, $, #로 시작,
  - 두번째 문자부터 숫자 허용
  - 키워드 안됨

- **schema** - 서로 연관된 table, index등의 객체를 그룹핑하는 논리적 개념

  ​				객체 명을 재사용할 수 있는 namespace역할을 한다.

  - shema내에서 중복된 이름 사용 불가
  - 길이 제한 30자
  - DB이름 길이 제한 8자

- **컬럼타입**

  - char 고정길이 문자열 ~2000byte
  - varchar2 가변길이 문자열 ~4000byte
  - number(p, s)
  - date : 7byte값으로 numeric값을 저장
    - _ _세기 _ _년 _ _월 _ _일 _ _시 _ _분 _ _초
  - timestamp : date타입 확장, 1/10^9의 정밀한 초값 저장
  - timestamp with timezone
  - interval year to month
  - interval day to second

  - rowid
  - CLOB(character large object) ~4G
  - raw - binary 형식의 값 저장 예) 지문, 증명사진 ~2000byte
  - BLOB(binary large object) ~4G
  - BFILE - read only 가능한 file을 DB외부에 운영체제의 폴더에 저장, TX처리

- ```sql
  create table 테이블명 (
  컬럼명 컬럼타입(size),
  컬럼명 컬럼타입(size)  [default 값],
  컬럼명 컬럼타입(size)  [제약조건],
  .......
  [제약조건]
  )
  [..................];
  ```

- 

- **CTAS**(테이블 구조만 복제한다던가, 일부row만 가져오거나,...)

  ```sql
  create table 테이블이름
  as
  	(subquery);
  	
  create table emp20
  as select empno, ename, deptno, sal*12
  	from emp
  	where deptno = 20; -- error
  	
  create table emp20
  as select empno, ename, deptno, sal*12 salary
  	from emp
  	where deptno = 20; --allias 지정해주기
  	
  drop table emp20 purge;
  	
  create table emp20 (empid, name, deptid, salary)
  as select empno, ename, deptno, sal*12
  	from emp
  	where deptno = 20; 
  ```

  

- **제약조건 constraint - table의 DML 수행시 규칙**

  Primary Key

  not null

  Unique Key

  Foreign Key

  check

  ```sql
  create table userinfo
  (userid varchar2(10) not null,
   username varchar2(15) constraint userinfo_nn not null,
  age number(30)
  );
  
  desc userinfo
  insert into userinfo
  values ('tester1', '테스터1', 20);
  
  insert into userinfo (username, age)
  values ('테스터1', 20); --null값을 허용하지 않는데, null값
  						--not null 제약조건 에러
          
  select * from userinfo;
  
  select constraint_name, constraint_type
  from user_constraints
  where table_name = 'USERINFO';
  
  alter table userinfo disable constraint userinfo_nn;
  
  insert into userinfo (userid, age)
  values ('tester2', 30);
  
  select * from userinfo;
  
  drop table userinfo ;
  desc userinfo;
  
  select constraint_name, constraint_type
  from user_constraints
  where table_name = 'USERINFO';
  
  select * from recyclebin;
  
  flashback table userinfo to before drop;
  select * from userinfo;
  select constraint_name, constraint_type
  from user_constraints
  where table_name = 'USERINFO';
  
  drop table userinfo purge;
  ```

  =========================================================

  ```sql
  * Unique 제약조건
  
  create table userinfo 
  (userid  varchar2(10)  constraint userinfo_uk  unique,
   username  varchar2(15)  ,
   age  number(30)
  );
  
  desc userinfo
  insert into userinfo 
  values ('tester1', '테스터1', 20);
  
  insert into userinfo  (username, age)
  values ( '테스터2', 25);   --userid에 null? 허용
  
  insert into userinfo  (username, age)
  values ( '테스터3', 30);   --userid에 null? 허용
  
  insert into userinfo
  values ('tester1', '테스터5', 35);   --중복값 허용X error
  
  select * from userinfo;
  
  select constraint_name, constraint_type
  from user_constraints
  where table_name = 'USERINFO';
  
  select index_name, uniqueness
  from user_indexs
  where table_name = 'USERINFO';
  
  --oracle server는 unique제약조건이 선언된 컬럼에 자동으로 unique index 생성
  
  alter table userinfo disable constraint userinfo_uk;
  
  select index_name, uniqueness
  from user_indexs
  where table_name = 'USERINFO';
  --제약조건 비활성화하면 인덱스 자동 삭제
  alter table userinfo enable constraint userinfo_uk;
  
  select index_name, uniqueness
  from user_indexes
  where table_name = 'USERINFO';
  --index 다시 자동 생성
  
  ```

  =========================================================

  ```sql
  * Primary Key 제약조건
  
  #primary key = not null+unique
  # 다른 제약조건은 하나의 테이블에 여러개 선언가능하지만
  primary key 제약조건은 하나만 선언 가능
  
  create table userinfo 
  (userid  varchar2(10)  constraint userinfo_pk primary key,
   username  varchar2(15)  ,
   age  number(30)
  );
  
  desc userinfo
  insert into userinfo 
  values ('tester1', '테스터1', 20);  ---?
  
  insert into userinfo  (username, age)
  values ( '테스터2', 25);     ---? error
  
  insert into userinfo 
  values ('tester1', '테스터5', 35); ---? error
  
  select * from userinfo;
  
  select constraint_name, constraint_type
  from user_constraints
  where table_name = 'USERINFO';
  
  select index_name, uniqueness
  from user_indexes
  where table_name = 'USERINFO';
  ```

  =========================================================

  ```sql
  *check 제약조건
  
  create table userinfo(
  userid  varchar2(10),
  username  varchar2(15),
  gender   char(1) constraint userinfo_ck  check (gender in ('F', 'M')),
  age  number(2) check (age > 0 and age < 100)
  );
  
  select constraint_name, constraint_type, search_condition
  from user_constraints
  where table_name='USERINFO';
  
  insert into userinfo  values ('a001', 'an', 'f', 20);  
  --error 컬럼값은 대소문자 구분 함. gender 대소문자
  insert into userinfo  values ('a001', 'an', 'w', 20); 
  --error gender 대소문자
  **insert into userinfo  values ('a001', 'an', null, 20);   --정상
  insert into userinfo  values ('a002', 'choi', 'M', 0);
  --error age범위 0보다 커야함
  insert into userinfo  values ('a002', 'choi', 'M', 100);
  --error age범위 100보다 작아야함
  **insert into userinfo  values ('a002', 'choi', 'M', 25);  --정상
  
  drop table user_info purge;
  ```

  

- **데이터 복구**

  ```sql
  
  create table copy_dept
  as select * from dept;
  desc copy_dept
  select * from copy_dept;
  
  drop table copy_dept;
  desc copy_dept
  select * from copy_dept;
  select tname from tab;  ---BIN$~~~~~~~이름의 테이블
  select * from user_recyclebins;
  select * from  recyclebin ;
  select * from "BIN$~~~~~~~";
  
  flashback table copy_dept to before drop;
  select * from  recyclebin ;
  select tname from tab;
  desc copy_dept
  select * from copy_dept;
  
  ```