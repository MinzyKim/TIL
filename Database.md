# DAY13 Database

## 0. DBMS

### 	0.1 DBMS의 정의

- 특정 기업이나 조직 또는 개인이 필요에 의해 논리적으로 연관된 데이터를 모아 일정한 형태로 저장해 놓은 것

  ### 0.2 DBMS의 장점

- DBMS를 이용하여 데이터를 공유하기 때문에 중복 가능성이 낮음
- 중복제거로 데이터의 일관성이 유지됨
- 데이터의 정의와 프로그램의 독립성 유지 가능
- 데이터 복구, 보안, 동시성 제어, 데이터 관리 기능 등을 수행
- 짧은 시간에 큰 프로그램을 개발할 수 있음
- 데이터 무결성 유지, 데이터 표준 준수 용이



## 1. Database

### 	1.1 특징

*- <기존의 파일 시스템>*

​     -  데이터를 파일 단위로 저장하여 중복 가능

- ```
  <기존의 파일 시스템>
  - 데이터를 파일 단위로 저장하여 중복 가능
  - 데이터의 중복 저장으로 일관성이 결여됨
  - 데이터 정의와 프로그램의 독립성 유지 불가능
  - 관리 기능 보통
  - 프로그램 개발 생산성 나쁨
  ```

- 통합된 데이터 - 데이터의 중복을 최소화하여 중복으로 인한 데이터 불일치 현상을 제거

- 저장된 데이터 - 디스크, 테이프 같은 컴퓨터 저장장치에 저장된 데이터

- 운영 데이터 - 업무를 위한 검색을 할 목적으로 저장된 데이터

- 공용 데이터 - 동시 공유

- 실시간 접근성

- 지속적인 변화

- 내용에 의한 참조

  

  ### 1.2 사용자 그룹

- 일반 사용자

- 응용 프로그래머

- SQL 사용자

- DBA

  

  ### 1.3 정의, 조작, 제어 언어	

- DDL - 구조:  Table, View, Index, Sequence

  - 생성 create
  - 변경 alter
  - 제거 drop
  - 테이블관리 저장 명령 truncate

- DML

  - 검색 select
  - 추가 Insert
  - 수정 Update
  - 삭제 Delete

- DCL

  - 인증

  - 권한 	

    - 부여 Grant

    - 회수 revoke

      

- RDBMS의 주요 목적

  - Transaction처리

- TCL
  - Commit
  - Rollback



## 2. SQL

### 	2.1 SQL 정의

관계형 데이터베이스에서 데이터 조작과 데이터 정의를 하기 위해 사용하는 언어 표준 언어

- 선언적 언어

- 결과 중심 언어

  

  ### 2.2 cmd창에서 SQL 사용하기

```
# sqlplus를 실행시키고 관리자 계정으로 접속해서 sample계정 비밀번호 설정하고, 잠긴 계정 풀기
C:\Users\student>sqlplus / as sysdba

SQL*Plus: Release 11.2.0.1.0 Production on 목 5월 30 10:20:16 2019

Copyright (c) 1982, 2010, Oracle.  All rights reserved.


다음에 접속됨:
Oracle Database 11g Enterprise Edition Release 11.2.0.1.0 - 64bit Pro
With the Partitioning, OLAP, Data Mining and Real Application Testing

SQL> select user from dual;

USER
------------------------------
SYS

SQL> alter user scott
    identified by oracle
     account unlock;

사용자가 변경되었습니다.

SQL> alter user hr
     identified by oracle
    account unlock;

사용자가 변경되었습니다.

SQL> conn hr/oracle

SQL> select * from employees

SQL> conn scott/oracle

SQL> select tname from tab; ---메타정보로 테이블 목록 확인

SQL> select * from emp;  --테이블의 모든 데이터 조회
```



### 	2.3 SQL 언어

- DDL - 구조:  Table, View, Index, Sequence
  - 생성 create
  - 변경 alter
  - 제거 drop
  - 테이블관리 저장 명령 truncate

- DML

  - 검색 select
  - 추가 Insert
  - 수정 Update
  - 삭제 Delete

- DCL

  - 인증

  - 권한 	

    - 부여 Grant

    - 회수 revoke

      

- RDBMS의 주요 목적

  - Transaction처리

- TCL
  - Commit
  - Rollback



### 	2.4 특징

- sqlplus 툴 - sql 실행, 결과 보여주는 환경 제공
- sqlplus 툴 명령어 - 세미콜론(;) 없이 사용 가능, 명령어 축약 사용 가능
- sql문은 명령어 축약 불가, 반드시 한 문장은 세미콜론(;) 으로 종료



- char(1)~2000byte
- varchar2(1) ~4000byte
- number 타입 binary형식으로 정수, 실수
- date 날짜를 7byte를 사용해서 수치값으로 저장 (_ _ 세기 , _ _ 년도, _ _월, _ _ 일, _ _시, _ _분, _ _초)



- 시스템 현재 시간을 리턴하는 함수

  ```sql
  select sysdate from dual;
  
  ```

- 현재 시간 포맷을 바꾸는 함수

  ```sql
  alter session set nls_date_format ='YYYY/MM/DD HH24:MI:SS';
  ```

  

- meta정보가 저장된 oracle data dictionary view는

  - user_tables - 특정 user 소유의 테이블 목록 확인

  - all_tables - 특정 user 소유 + 권한을 받은 테이블 목록 확인

  - dba_tables - DB의 모든 테이블 목록 확인 (DBA 권한으로만 확인 가능)

    ```sql
    desc user_tables;
    select table_name from user_tables;
    desc tab
    select tname from tab;
    
    select table_name from all_tables;
    select table_name from dba_tables; -- 오류 발생
    
    conn / as sysdba
    select table_name from dba_tables;
    
    conn scott/oracle
    
    ※sql문장의 키워드와 테이블명, 컬럼명등은 대소문자 구별 안함
    ※컬럼값은 대소문자 구별 함
    ```

- SQL은 조건처리 하는 언어라서 table행 단위로 반복처리한다.

  - 명시적 for나 while문 사용은 불가
  - exception 처리도 없다
  - 변수선언도 불가

- 제공자에 따른 함수의 분류

  - predefine - DB 벤더가 제공(nvl, sysdate, user, ...)
  - custom(PL/SQL)

- 행개수에 따른 함수의 분류

  - 단일행함수 input -> 처리 -> output **반드시 1개의 결과 리턴**
  - 복수행 함수 (그룹함수) grouping된 row가 colunm값으로 들어가 결과 출력
  - 분석 함수(Window 함수) 부분적으로 특정 항목만 봐야할 때 유용(순위,비율 등)

- 데이터타입별 함수

  - Character

    - ```sql
      select initcap(ename), lower(ename), upper(ename)
      from emp;
      
      select length('korea') length('대한민국')
      from dual;
      
      select lengthb('korea') lengthb('대한민국')
      from dual;
      
      --함수 안에 함수를 nested하면  nested된 함수부터 처리
      select concat(concat(ename, ' is '), job)
      from emp;
      
      ```

    - ```sql
      ## substr, instr 함수
      select substr('today is 2015년 4월 26일', 1, 5), 
      	substr('today is 2015년 4월 26일', 10, 5),
      	substr('today is 2015년 4월 26일', 15),
      	substr('today is 2015년 4월 26일', -3, 2)
      	from dual;
      -- 글자수 세고 위치에 있는 글자 리턴
      select instr('korea is wonderful', 'o'),
      	instr('korea is wonderful', 'o',1,2),
      	instr('korea is wonderful', 'o',9),
      	instr('korea is wonderful', 'x')
      	from dual;
      -- o가 들어간 문자 출력, 두번째는 2번째로 등장하는 o출력, 9번째 글자이후의 o 출력, 없는 문자 출력시 0출력
      ```

    - ```sql
      #lpad : left padding,  
      #rpad : right padding
      #문자열로 변환, 문자열 전체 길이내에 왼쪽 공백에 특정 문자를 padding
      
      select ename, sal, lpad(sal, 10, '*')
      from emp;
      
      select ename, sal, rpad(sal, 10, '*')
      from emp;
      ```

    - ```sql
      #trim, ltrim, rtrim 함수
      select length('  hello  '),  length(trim('  hello  '))
      from dual;
      
      select trim('H' from 'Hello wonderful'), trim('l' from 'Hello wonderful')
      from dual;
      
      select ltrim('Hello wonderful', 'He' ), rtrim( 'Hello wonderful' , 'ful')
      from dual;
      
      select replace('Jack AND Jue', 'J', 'BL')
      from dual;
      
      #translate
      ```

  

  - Number

    - ```sql
      select round(12.345, 2), round(12.345, 0), round(12.345, -1)
      from dual;
      
      select trunc(12.345, 2), trunc(12.345), trunc(12.345, -1)
      from dual;
      
      select mod(99, 4)
      from dual;
      
      select ceil(12.345), floor(12.345) from dual;
      
      select power(3, 2), power(5, 2)
      from dual;
      ```

  - Date

    - timestamp컬럼 타입
    - timestamp(3) #6이 default
    - timestamp with time zone

    ```sql
    select sessiontimezone from dual; -- time zone값 확인
    alter session set time_zone='+3:00';
    select sessiontimezone from dual;
    ```

    - sysdate 시스템의 현재 리턴
    - current _date
      - timezone기반 현재시간을 date타입으로 리턴
    - current_timestamp
      - 세션의 timezone기반 현재시간을 timestamp타입으로 리턴

    ```sql
    add_months(date, n) -- 개월 수를 더한 날짜가 리던
    months_between(date, date) --기간이 리턴
    ```

    ```sql
    
    select add_months(sysdate, 6)
    from dual; -- n개월 후 날짜 출력
    
    select hiredate, add_months(hiredate, 6)
    from emp;
    
    select months_between(sysdate, hiredate)
    from emp;
    
    
    # next_day(date, '요일명')
    select next_day(sysdate, '목')
    from emp; -- 그 주의 목요일 출력
    
    
    --trunc, round 
    -- month는 16일을 기준으로 반올림, year는 7월을 기준으로 반올림
    select trunc(to_date('14/02/14'), 'MONTH'), 
           trunc(to_date('14/02/14'), 'YEAR')
    from dual;
    
    
    select round(to_date('14/02/14'), 'MONTH'), 
           round(to_date('14/02/14'), 'YEAR')
    from dual; -- month는 16일을 기준으로 반올림, year는 7월을 기준으로 반올림
    
    last_day(date)
    select last_day(to_date('14/02/14')), last_day(to_date('2000/02/14')), last_day(to_date('2100/02/14'))
    from dual; -- 해당 달의 마지막 날을 출력
    
    --데이터타입 바꿔주는 함수
    to_date(숫자형날짜, 통일형식)
    to_char(number, "문자열")
    to_number('문자열', 'format')
    <Ex>
    select to_char(123456.789, '$9,999,999.9999')
    from dual;
    
    select to_number('$1,234,567.999', '$99,999,999.9999')
    from dual; --$의 영향으로 에러. 정확하게 일치해야 한다.
    
    select sysdate, to_char(sysdate, 'YYYY"년" MM"월" DD"일" DY')
    from dual;
    
    select '2019-05-30 5:43 오후', to_date('2019-05-30 5:43','YYYY-MM-DD HH12:MI')
    from dual;
    ```

    

  - null처리 및 기타

    - 

  - Conversion함수

    - 

   ### 2.5 SQL 예제기본

- 호출

 ```sql
select ename, sal, job, deptno from emp;
 ```

- 중복값 없이 출력

```sql
select distinct deptno from emp;
```

- As - 별칭

```sql
select sal, comm, (sal+nvl(comm,0))*2 as "Salary"
from emp;
```

- Number타입 컬럼은 산술연산 가능 : +, -, *, /
- char/varchar2 타입 컬럼은 문자열 결합 가능 : ||

- date 타입 컬럼 : date±n, date-date, date±1/n
- Null
  - 데이터가 추가될 때 입력되지 않는 컬럼값은 null
  - null은 아직 값이 없다는 의미. (0이나 ''이 아님)
  - null은 산술연산 수행 결과가 항상 null
  - null을 포함하는 컬럼들은 null아닌 값으로 변환 후에 산술연산 수행해야 함
  - 모든 DBMS에서는 null아닌 값으로 변환 후에 산술연산을 수행해야 함
  - nvl(colunm, null일때 리턴값)
  - null은 비교연산, 논리연산 모두 null이 결과

```sql
select sal, comm, (sal+comm)*2
from emp;
```

출력값에 null.

- 문자, 날짜 데이터는 반드시 ' '를 사용해서 표현, 처리
- 날짜 데이터 세션에 설정된 포맷 형식하고 일치해야 함 ('RR/MM/DD')

```sql
select sysdate+1, sysdate-1 --1은 1day
from dual;
```

```sql
select sysdate, sysdate+1/24, sysdate+5/1440
-- 원래시간, 1시간 후, 5분 후
from dual;
```



*Quiz> 'A'를 결과로 출력하려면?

```sql
select q'['A']'
from daul;

select '''A'''
from dual;
```

*Quiz> select 10||10 from dual; -- oracle server가 정수10을 문자열로 자동 형변환

*Quiz> select '10'+'10' from dual; --oracle server이 정수로 자동 형변환



- 오라클에선 select~ from절이 필수
- 단순계산 결과, 함수 결과, 문자 데이터 출력등은 dual테이블을 사용한다.

```sql
desc dual
select * from dual;
```



### 	2.6 Select문

문> 부서 번호가 30번인 사원 검색

```sql
select ename, deptno
from emp
where deptno = 30;
```

문> 직무가 ANALYST인 사원 검색

```sql
select ename, job
from emp
where job = 'ANALYST';
```

문> 급여가 3000이상인 사원 검색

```sql
select ename, sal
from emp
where sal >= 3000
```

```sql
select 검색 컬럼 리스트, 표현식
from 테이블
where 조건 ; => 컬럼 비교연산자 값
```

- Null값 비교

  문제>커미션을 받지 않은 사원 / 커미션을 받은 사원

```sql
select ename, comm
from emp
where comm is not null;

select ename, comm
from emp
where comm is null;
```

- Between A and B

  문제>월급이 3000이상 5000이하인 사원 검색

```sql
select ename, sal
from emp
where sal>=3000 and sal<=5000; --between A(하한값) and B(상한값)
```

- 포함관계

   문제>직무가 clerk 또는 analyst인 사원 검색

```sql
select ename, job
from emp
where job IN('CLERK', 'ANALYST');

select ename, job
from emp
where job='CLERK' or job='ANALYST';
```

- 포함문자 검색

  문제>사원 이름 중에서 두번째 문자가 'D'인 사원 검색

```sql
select ename
from emp
where ename LIKE('_D%');

_는 임의의 문자 1개, %는 0개 이상의 문자열
```

  	문제>사원 이름 중에서 첫번째 문자가 'S'인 사원 검색

```sql
select ename
from emp
where ename LIKE('S%');
```

​	문제>사원 이름 중에서 끝에 문자가 'N'인 사원 검색

```sql
select ename
from emp
where ename LIKE('%N');
```

 	문제>81년도에 입사한 사원 검색

```sql
select ename, hiredate
from emp
where hiredate like '81%';

select ename, hiredate
from emp
where hiredate between '81/01/01' and '81/12/31';
```

​	문제>업무가 PRESIDENT이고 급여가 1500 이상이거나 업무가 SALESMAN인 사원의 사원번호, 이름, 업무, 급여를 출력하여라.

```sql
select empno, ename, job, sal
from emp
where (job = 'PRESIDENT' and sal >= 1500) or job = 'SALESMAN';
```

​	문제>급여가 1500 이상이고, 업무가 PRESIDENT이거나 업무가 SALESMAN인 사원의 사원번호, 이름, 업무, 급여를 출력하여라.

```sqlite
select empno, ename, job, sal
from emp
where sal >= 1500 and (job = 'PRESIDENT' or job = 'SALESMAN');

select empno, ename, job, sal
from emp
where sal >= 1500 and job IN ('PRESIDENT',  'SALESMAN');

```

- Group by , having, order by

```sql
select ~
from ~
[where 필터 조건]
[group by 컬럼]
[having 조건]
[order by 정렬기준컬럼 정렬방식] -- asc 오름차순 default, desc 내림차순
```

 	문제>월급의 오름차순으로 사원 정보 출력

```sql
select ename, job, sal
from emp
order by sal;

select ename, job, sal
from emp
order by sal desc;
```

​	문제>사원들의 사번, 이름, 부서번호, 월급, 커미션, 연봉(sal+comm *12)결과 출력, 연봉의 내림차순으로

```sql
select empno, ename, deptno, sal, comm, (sal+nvl(comm, 0))*12 as 연봉
from emp
order by 연봉 desc;
```



##### 			2.6.1  문제풀이

  ```sql
문제1)  EMP Table의 모든 자료를 출력하여라.
select * from emp; --1번

문제2)  EMP Table에서 사원 번호, 이름, 급여, 담당업무를 출력하여라.
select empno, ename, sal, job --2번
from emp;

문제3) 모든 사원의 급여를 $300 증가시키기 위해 덧셈 연산자를 사용하고 결과에 SAL+300을 조회한다
select sal+300 as Newsal --3번
from emp;

문제4) EMP 테이블에서 사원번호, 이름, 급여보너스를 출력하여라
select empno, ename, nvl(comm,0)--4번
from emp;

문제5) EMP 테이블에서 ENAME를 NAME로 SAL을 SALARY로 출력하여라.
select ename as ENAME, sal as SALARY --5번
from emp;

문제6) EMP 테이블에서 ENAME를 Name로 SAL*12를 Annual Salary 로 출력하여라.
select ename as Name, sal*12 as "Annual Salary" --6번
from emp;

문제7) EMP 테이블에서 ENAME를 '성 명'으로, SAL를 급 여로  출력하여라.
select ename as "성 명", sal as "급 여" --7번
from emp;


문제8) EMP 테이블에서 이름과 업무를 연결하여 출력하여라.
select ename||job--8번
from emp;

문제9) EMP 테이블에서 이름과 업무를 "King is a PRESIDENT" 형식으로 출력하여라.
select ename ||' is a '||job --9번
from emp;

문제10) EMP 테이블에서 이름과 연봉을 "KING: 1 Year salary = 60000" 
select ename||' 1 Year Salary = ' ||sal*12 --10번
from emp;

문제11) EMP 테이블에서 JOB을 모두 출력하여라.
select job --11번
from emp;

문제12) EMP 테이블에서 담당하고 있는 업무의 종류를 출력하여라.
select distinct job--12번
from emp;

문제13) EMP 테이블이 부서번호를 중복 값을 제거해서 조회하라
select distinct deptno--13번
from emp;

문제14) 부서별로 담당하는 업무를 한번씩 출력하여라.
select distinct job, deptno-- 14번
from emp;

문제15) EMP 테이블에서 사원번호, 이름, rowid를 조회하라.
select empno, ename, rowid -- 15번
from emp;

문제17) EMP 테이블에서 급여가 3000 이상인 사원의 사원번호, 이름, 담당업무, 급여를 출력하라.
select empno, ename, job, sal
from emp
where sal >=3000;

문제18) EMP 테이블에서 담당업무가 Manager인 사원의 정보를 사원정보, 성명, 담당업무, 급여, 부서번호를 출력하라.
select empno, ename, job, sal, deptno
from emp
where job = 'MANAGER';

문제19) EMP 테이블에서 1982년 1월 1일 이후에 입사한 사원의 사원번호, 성명, 담당업무, 급여, 입사일자, 부서번호를 출력하라.
select empno, ename, job, hiredate, deptno
from emp
where hiredate >= '82/01/01';

문제20) EMP 테이블에서 급여가 1300에서 1700사이의 사원의 성명, 담당업무, 급여, 부서 번호를 출력하여라.
select ename, job, sal, deptno
from emp
where sal between 1300 and 1700;

문제21) EMP 테이블에서 사원업호가 7902, 7788, 7566인 사원의 사원번호, 성명, 담당업무, 급여, 입사일자를 출력하여라.
select empno, ename, sal, deptno
from emp
where empno IN(7902, 7788, 7566);

문제22) EMP 테이블에서 입사일자가 82년도에 입사한 사원의 사번, 성명, 당당업무, 급여, 입사일자, 부서번호를 출력하여라.
select empno, ename, job, sal, hiredate, deptno
from emp
where hiredate LIKE '82%';

문제23) EMP 테이블 이름의 첫 글자가 'M'인 사원의 이름, 급여 조회하라
select ename
from emp
where ename Like 'M%';

문제24) EMP 테이블 이름의  두 번째 글자가 L인 사원의 이름,업무를  조회하라
select ename, job
from emp
where ename Like '_L%';

문제25) EMP 테이블에서 보너스가 NULL인 사원의 사원번호, 성명, 담당업무, 급여, 입사일자, 부서번호를 출력하여라.
select empno, ename, job, hiredate, deptno
from emp
where comm is null;

문제26) EMP 테이블에서 급여가 1100 이상이고 JOB이 Manager인 사원의 사원번호, 성명, 담당업무, 급여, 입사일자, 부서번호를 출력하여라.

select empno, ename, job,sal, hiredate, deptno
from emp
where sal>=1100 and job = 'MANAGER';

문제27) EMP 테이블에서 급여가 1100 이상이거나 JOB이 Manager인 사원의 사원번호, 성명, 담당업무, 급여, 입사일자, 부서번호를 출력하여라.
select empno, ename, job,sal, hiredate, deptno
from emp
where sal>=1100 or job = 'MANAGER';

  ```



