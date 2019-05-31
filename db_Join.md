# DAY14 Database

## *<저번시간 복습>*

## 0. DBMS

- 계층형 - 망형 - 관계형 - 객체관계형 - 클러스터로 구성하여 사용

- 2가지 유형의 데이터를 저장

  - Business또는 User
  - Meta Data

- <역사>

  ```
  1960년대 : 플로우 차트 중심, 파일 구조로 데이터 저장 관리
  1970년대 : 네트워크 데이터베이스, 망형 데이터베이스 상용화
  1980년대 : 관계형 데이터베이스 상용화
  ```

- Table  - Column(속성) + Row(Record)
- Primary Key (고유키) 
  - Not null
  - Unique
- Foreign Key (외래키)
  - RDBMS 참조관계 (parent Table의 PK를 참조하는 child 테이블의 외래키 설정)
- Null 정의
  - 값이 할당되지 않음을 의미
  - 0아님
  - ' '아님(Empty space)
  - 산술연산 결과 및 비교연산 결과, 논리연산 결과도 모두 null
  - nvl(null이 있는 컬럼, 대체값)

## 1. SQL

## 1.1 SQL 언어, 기본함수

- 선언적 언어, 결과 기술

- DML - Select~, Insert~, Update~, delete~
- DDL - Create~, Alter~, Drop~, Truncate~, Rename~, Comment~(메타데이터)
- DCL - Grant~, Revoke~
- TCL - Commit~, Rollback~, savepoint~



- 검색 구문

  ```sql
  select * |[distinct]column,.... | expression [as] "allias"
  from 테이블명
  [where 조건]
  ...
  order by 기준column명 [desc];
  ```

  

- 테이블 구조 확인 - desc, describe

- 컬럼 타입 : 

  - char(size)

  - varchar2(size)

  - number(p, s)

  - date

  - timestamp(1/10억 시간까지 나타낼 수 있음)

  - timestamp with timezone

  - interval year to month

  - interval day to second
  - rowid

- 컬럼 타입에 따른 연산 :

  - number - 산술연산
  - char/varchar2 - || 결합연산자
  - date - ±n, ±1/24, date-date

- where절 연산자 :

  - IN - 여러 값의 리스트에서 값들을 =, or 선택데이터에 사용
  - LIKE - 문자로 패턴, _,% 만능문자와 함께 사용
  - BETWEEN A AND B - 범위 연산자,  A는 하한값, B는 상한값
  - IS NULL / IN NOT NULL - NULL값의 비교 연산자
  - =, >, <=, >=, !=, <>

- 조건이 여러 개일 때 = 논리 연산자 사용

  - 우선 순위
    - not, and, or

- order by 컬럼

  ```sql
  order by 컬럼;
  order by 표현식;
  order by 별칭;
  order by column position;
  ```



- 함수 - 반드시 하나의 값을 리턴
  - 단일행 함수 - character, number, date, conversion
  - 복수행 함수
  - 분석(Window) 함수



## 1.2 SQL 함수

### 1.2.1 null관련 함수

- nvl(column, expression) - coulumn이랑 expression이 동일한 타입이어야 함
- nvl2(column, expression1(null이 아닐 때), expression2(null일 때)) - expression1이랑 expression2이 동일한 타입이어야 함
- coalesce(column, expression1, expression2, ...) - 첫번째 인수가 null인지 확인, 모든 인수를 체크해서 null이 아닌 최초의 값을 만나면 null이 아닌 값을 리턴하고 함수를 종료
- nullIf(expression1, expression2) - expression1이랑 expression2 가 값이 동일하면 null을 리턴, 다르면 expression1을 리턴

- 문> 사원들중 커미션을 받지 않는 사원들은 -1로 출력(이름, 급여, 커미션)
- 문> 사원들중 커미션을 받는 사원은 급여+커미션을 출력하고, 커미션을 받지 않는 사원은 'No Commition'을 출력

### 1.2.2 조건처리함수

- 조건처리함수 : decode(column, 표현식1, 리턴값, 표현식2, 리턴값2,...)

- 조건처리 표현식, 표준 sql3 : case [표현식] when [값|조건표현식] then 값 [else 값] end

  - 문> 사원들의 부서번호가 10번이면 월급을 5%인상

    ​		부서번호가 20번이면 월급을 10%인상

    ​		부서번호가 30번이면 월급을 3%인상

    ​		그 외의 부서는 월급 100인상

    ​		현재의 월급과 인상된 월급을 출력

    ```sql
    select ename, deptno, sal,
    		decode(deptno, 10, sal*1.05
                  		 , 20, sal*1.1
                  		 , 30, sal*1.03, sal+100) "increase"
    from emp;
    ```

    ```sql
    select ename, deptno, sal,
    		case deptno when 10 then sal*1.05
                  		when 20 then sal*1.1
                  		when 30 then sal*1.03 
                  		else sal+100 end "increase"
    from emp;
    ```

    

  - 문> 월급에 대한 세금 출력

     	  월급이 1000미만이면 0,

    ​	   2000미만이면 월급의 5%,

    ​	   3000미만이면 월급의 10%

    ​	   4000미만이면 월급의 15%

    ​       4000이상이면 월급의 20%

         ```sql
  select ename,
  		(case when (sal<1000) then 0
                		when (sal<2000) then sal*0.05
                		when (sal<3000) then sal*0.1
                      when (sal<4000) then sal*0.15
                		else sal*0.2 end) "세금"
  from emp;
         ```

  ```sql
  select ename, deptno, sal,
  		decode(trunc(sal/1000), 0, 0,
                                  1,  sal*0.05
                                 , 2, sal*0.1
                                 , 3, sal*0.15
                                  ,sal*0.2) "세금"
                		
  from emp;
  ```

### 1.2.3 그룹함수

- 그룹핑된 행 집합, 테이블의 전체 행 집합의 컬럼이 함수의 인수로 전달되고 결과는 반드시 1개 리턴

  - sum(number타입|expression)

  - avg(number타입|expression)

  - min(number, char, date 컬럼타입 |expression)

  - max(number, char, date 컬럼타입 |expression)

  - count([distinct]number, char, date 컬럼타입 |expression)

    - null이 아닌 값(행) 개수 리턴

    ```sql
    select min(ename), max(ename)
    from emp;
    
    select count(distinct deptno)
    from emp;
    
    select count(comm) --null은 카운트하지 않는다.
    from emp;
    
    select sum(comm)/count(*), avg(nvl(comm,0))
    from emp;
    
    select deptno, avg(sal)
    from emp
    group by deptno;
    
    select avg(sal)
    from emp
    group by deptno;
    ```

    

  - stddev(number타입|expression)

    - 표준편차

  - variance(number타입|expression)

    - 분산

- Group by 함수

  - ```sql
    *그룹함수를 적용한 컬럼과 그룹함수를 적용하지 않은 컬럼이 select절에 함께 선언될 경우 반드시 그룹함수를 적용하지 않은 컬럼은 group by절에 선언해야 함
    
    select deptno, avg(sal)
    from emp
    group by deptno;
    
    *group by절에 선언한 컬럼이 select절에 반드시 선언은 선택적.
    select  avg(sal)
    from emp
    group by deptno; 
    
    *group by절에는 column명만 선언할 수 있음
    ```

  - Group by 함수의 조건절은 Having절이다.

    ```sql
    --실행순서--
    select deptno, count(deptno), sum(sal) --4
    from emp                     --1
    group by deptno              --2
    having count(deptno) >= 4;   --3  
    ```

### 1.2.4  JOIN

- Projection

- Selection

- Join

  ```sql
  employees, emp - 사원정보
  departments, dept - 부서정보
  
  ex)사원이름, 부서번호, 부서이름
  
  oracle join syntax - where절에 조인조건 선언
  sql1999 표준 syntax - from절에 조인조건 선언
  
  *equi join(inner join)
  *non-equi join
  *self-join(자기참조가 가능한 테이블에서만)
  
  ※ 조인 조건을 잘못 정의하거나 , 
  조인 조건을 누락하면 cartesian product (cross join)
  
  ※outer Join(조인컬럼값이 null인 경우 결과집합에 포함시키기 위한 조인)
  ```

#### 1.2.4.1  Natural JOIN

```sql
conn hr2/oracle
문> 사원이름, 부서번호, 부서이름
select last_name, department_id, department_name
from  employees  , departments  ; ---error 


select a.last_name, a.department_id, b.department_name
from  employees a, departments b;  ---? 20명의 사원 데이터 (20*8)rows, cartesian product, 조인조건 누락

select a.last_name, a.department_id, b.department_name
from  employees a, departments b
where a.department_id = b.department_id;
---? 20명의 사원 데이터?error

--natual join 은 oracle 서버가 조인할 테이블에서 동일한 이름의 컬럼으로 자동 equi 방식 조인을 수행
--natual join 은 조인할 테이블에서 동일한 이름의 컬럼 앞에 소유자 테이블명 또는 alias를 선언하지 않음
--natual join 은 동일한 속성이지만, 설계할때 부모와 자식 테이블에서 이름을 다르게 정의하면 조인 수행 안됨
select a.last_name,  department_id, b.department_name
from  employees a natural join  departments b;  -->? 19rows?


select a.last_name, a.department_id, b.department_name
from  employees a, departments b
where a.department_id = b.department_id
and a.manager_id = b.manager_id;


select a.last_name,  department_id, b.department_name
from  employees a  join  departments b using (department_id); 

```

#### 1.2.4.2 Inner JOIN

```sql
conn scott/oracle
create table  copy_emp
as select  empno , ename, job, hiredate, sal, mgr, deptno deptid
from emp;

desc  copy_emp
select * from copy_emp;

문> copy_emp와 dept테이블에서 사번, 이름, 부서번호, 부서명 출력
select a.empno, a.ename, b.deptno, b.dname
from copy_emp a  join  dept b  on a.deptid = b.deptno;


drop table copy_emp purge;  --테이블 삭제


select a.empno, a.ename, b.deptno, b.dname
from emp a  inner join  dept b  on a.deptno = b.deptno;


```

#### 1.2.4.3 Non-Equi JOIN

```sql
문> 사원이름, 급여, 급여의 등급을 조회 출력  --non-equi join으로 해결
select a.ename, a.sal, b.grade
from emp a, salgrade b
where a.sal between b.losal and b.hisal;

select a.ename, a.sal, b.grade
from emp a join  salgrade b  on a.sal between b.losal and b.hisal;
```

#### 1.2.4.4 Self JOIN

```sql
문> 사원번호 사원이름 관리자번호 관리자이름을 조회결과 출력 --self join
select a.empno, a.ename, a.mgr , b.ename
from  emp a, emp b
where a.mgr = b.empno;

select a.empno, a.ename, a.mgr , b.ename
from  emp a join emp b  on a.mgr = b.empno;
```

#### 1.2.4.5  * 3개이상 조인 할 때

```sql
conn hr2/oracle
desc employees
desc departments
desc locations
※ n개의 테이블을 조인할때 최소 조인 조건은  n-1개 
문> 사원이름 , 소속 부서이름, 부서가 위치한 도시를 조회 출력

<1번째 방식>
select a.last_name, b.department_name, c.city
from employees a, departments b, locations c
where a.department_id = b.department_id
and b.location_id = c.location_id; 
--세 개 참조할 땐 and로 묶어주면 됨

<2번째 방식>
select a.last_name, b.department_name, c.city
from employees a join departments b
on a.department_id = b.department_id
join locations c on b.location_id = c.location_id; 
--join ~ on ~ join ~ on
※일반 필터는 where절에 써주면 된다.
```

#### 1.2.4.6  Outer JOIN

- ##### Right-Outer join

    ```sql
  conn scott/oracle
  insert into emp (empno, ename) values (8000, 'Hong');
  commit;
  
  문> 부서번호가 없는 사원을 포함해서 사원들의 부서이름를 함께 출력
  select a.empno, a.ename, a.deptno, b.dname
  from emp a, dept b
  where a.deptno = b.deptno;   --8000번 hong사원 누락됨
  
  select a.empno, a.ename, a.deptno, b.dname
  from emp a, dept b
  where a.deptno = b.deptno(+); ----8000번 hong사원 포함?
    ```

- ##### Left-Outer Join

  ```sql
  문> 부서정보를 기준으로 부서의 소속 사원을 출력하고,
  소속 사원이 없는 부서도 출력합니다.
  select b.deptno, b.dname, a.empno, a.ename, 
  from emp a, dept b
  where a.deptno = b.deptno
  order by  b.deptno;   ---40번 부서정보 누락?
  
  
  select b.deptno, b.dname, a.empno, a.ename, 
  from emp a, dept b
  where a.deptno(+) = b.deptno
  order by  b.deptno;  ------40번 부서정보 포함?
  ```

- ##### Full-Outer Join

  ```sql
  select  b.deptno, b.dname, a.empno, a.ename
  from emp a full outer join dept b
  on a.deptno(+) = b.deptno
  order by b.deptno;
  ```

  

```sql

```

