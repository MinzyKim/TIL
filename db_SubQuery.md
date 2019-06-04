## Day 14

## 0. 함수

### 0.1 그룹함수

- 정의 : 여러 행의 컬럼이 함수의 인수로 전달되고, 함수의 결과는 한개를 리턴한다.
- 종류 : sum, avg, min, max, count, stddev, variance
- 날짜, 숫자, 문자 데이터 유형에 사용 가능 함수 : min, max, count



- *그룹함수는 null을 연산에 포함시키지 않는다.*
- count(column) - null이 아닌 개수를 리턴
- count(*) - 테이블의 전체 행 수를 리턴, 내부적으로는 not null 또는 PK 제약조건이 선언된 컬럼을 기준으로 한다.



- 그룹함수(all | distinct 컬럼)

  ```sql
  select ~ 컬럼, 그룹함수(컬럼)  --4
  from ~   ---1
  [where 필터조건] ---2
  [group by 컬럼, ...] ---3
  ```

  

- 그룹함수를 적용한 컬럼과 그룹함수를 적용하지 않은 컬럼이 select절에 선언될 경우 group by 절에 그룹함수를 적용하지 않은 컬럼을 반드시 선언해 줘야 한다.



- 그룹함수의 조건은 having절에 선언한다.

  ```sql
  select ~   컬럼, 그룹함수(컬럼)   ---  5
  from ~             ------ 1
  [where 필터조건]   ------  2
  [group by 컬럼, ...]   ----  3
  [having 그룹함수 조건]  -----  4
  [order by 컬럼 정렬방식]  ----- 6
  ```

  

## 1. 검색방법

### 1.1 종류

- projection, selection, join

  - #### join?

    - 하나 이상의 테이블에서 동일한 속성의 컬럼값이 일치할 때 테이블 의 row를 결합해서 결과집합으로 생성

    - inner join = equi join

    - non-equi join

    - self join ( 자기참조가 가능한 테이블 )

    - outer join - 일치하는 조인컬럼값이 없거나, 조인컬럼값이 null인 row도 조인 결과로 생성하는 것

    - cartesian product - 조건을 생략하거나, 조인 조건을 논리적으로 잘못 정의하면 두 테이블의 모든 row가 한번 씩 join 되는 경우

    - 오라클에서 초기 버전부터 사용했었던 조인 구문

      ```sql
      where 조인조건
      ```

    - 오라클에서 지원하는 sql1999 조인 구문

      ```sql
      from tab1 a natural join tab2 b 
      -- 이름이 같은 컬럼을 자동으로 조인
      ```

      ```sql
      from tab1 a join tab2 b using (조인컬럼명) 
      -- 일치하는 컬럼만 조인
      ```

      ```sql
      from tab1 a  join tab2 b  on a.col=b.col2 
      from tab1 a  join tab1 b  on a.col=b.col2 
      ```

      ```sql
      select e.ename, e.deptno, d.dname
      from emp e, dept d;
      -- 초기 버전
      
      from tab1 a cross join tab2 b 
      -- cartesian product
      ```

      ```sql
      select e.ename, e.deptno, d.dname
      from emp e, dept d;
      where e.deptno = d.deptno(+); 
      --join할 row가 없는 쪽에다 outer join을 선언
      -- 부서번호가 null인 사원을 포함하려면
      
      select e.ename, e.deptno, d.dname
      from emp e left outer join dept d 
      on e.deptno = d.deptno;
      ```

      ```sql
      select e.ename,e.deptno, d.dname
      from emp e, dept d;
      where e.deptno(+) = d.deptno; 
      -- 소속사원이 없는 부서정보를 조인 결과에 포함하려면
      
      select e.ename, e.deptno, d.dname
      from emp e right outer join dept d on e.deptno = d.deptno;
      ```

      ```sql
      select e.ename, e.deptno, d.dname
      from emp e full outer join dept d 
      on e.deptno = d.deptno;
      
      -- 부서번호가 null인 사원데이터와 소속 사원이 없는 부서정보를 조인 결과에 포함하려면
      
      select e.ename, e.deptno, d.dname
      from emp e, dept d
      where e.deptno(+) = d.deptno(+); --error
      -- 초기 버전
      ```

      

## 2. SubQuery

```sql
조건 값을 알수 없어서 query를 2번 수행해야 하는 경우 subquery를 활용할 수 있다.

subquery = inner query= nested query
main query = outer query
# subquery 는 mainquery의  select절, from절, where절, having절, order by절 에  subquery가 정의될 수 있습니다.
# where절과 having절의 subquery는 연산자 오른쪽에 () 안에 정의
 

*단일 행을 리턴하는 subquery : single row subquery
*복수행을 리턴하는 subquery : multiple row subquery
*단일 행, 단일 컬럼값을 리턴 subquery : scalar subquery
*두개 이상의 컬럼값을 리턴하는 subquery : multiple column subquery

where절에 single row subquery 를 사용할 경우 반드시 single row operator(>, >=, <=, <, =, <>)와 함께 사용
where절에 multiple row subquery를 사용할 경우 반드시 multiple row operator(In, any, all)와 함께 사용
```

```sql
문> ADAMS 보다 급여를 많이 받는 사원
select *
from emp
where sal > ( select sal
             from emp 
             where ename = 'ADAMS' );
문> 사원번호 7839번과 동일한 직무를 담당하는 사원정보 검색
select ename,sal, job
from emp
where job = (select job
               from emp
             where empno = 7839);

문> emp 테이블에서 최소 월급을 받는 사원 정보 검색
select ename, sal
from emp
where sal = ( select min(sal)
            from emp);

문> emp 테이블에서 전체 사원 평균 월급보다 급여가 적게 받는 사원 검색
select ename, sal
from emp
where sal < (select avg(sal)
            from emp);

문>EMP 테이블에서 사원번호가 7521인 사원과 업무가 같고 
급여가 7934인 사원보다 많은 사원의 사원번호, 이름, 담당업무, 입사일자, 급여를 조회하라.
select empno, ename, job, hiredate, sal
from emp
where job = (select job
            from emp
            where empno = 7521) and
      sal > (select sal
            from emp
            where empno = 7934);

문> EMP 테이블에서 부서별 최소 급여가 20번 부서의 최소 급여보다 많은 부서번호와 부서의 최소급여를 조회하라.
select deptno, min(sal)
from emp
group by deptno
having min(sal) > (select min(sal)
            from emp
            where deptno=20);

문> 10번부서 사원의 월급과 동일한 월급을 받는 다른 부서의 사원을 검색하시오
select ename, sal
from emp
where sal in (select sal
            from emp
            where deptno = 10)
            and
            deptno<>10;

문>부서별로 가장 급여를 많이 받는 사원의  사원번호 , 이름, 급여, 부서번호를 
조회하라
select empno, ename, sal, deptno
from emp
where (deptno,sal) in (select distinct deptno, max(sal)
            from emp
            group by deptno);
            
--multiple column subquery, pair-wise 비교

문>업무가 SALESMAN인 최소 한명 이상의 사원보다 급여를 많이 받는 사원의 이름,  급여, 업무를 조회하라
select ename, sal, job
from emp
where sal >any(select sal
            from emp
            where job like 'SALESMAN')
            and
            job <> 'SALESMAN';

문>업무가 SALESMAN인 모든 사원이 받는 급여보다 급여를 많이 받는 사원의 이름,  급여, 업무를 조회하라
select ename, sal, job
from emp
where sal >all(select sal
            from emp
            where job like 'SALESMAN')
            and
            job <> 'SALESMAN';

문> 직무별 평균 급여중에서 직무별 평균급여가 가장 작은 직무를 조회하시오 
(직무, 평균월급)
select job, avg(sal)
from emp
group by job
having avg(sal) in (select min(avg(sal)) --함수 안의 함수
              from emp
              group by job);
              
문> 부서번호 80번 사원들중에서 월급이 높은 3사람을 조회하시오
select rownum, ename, sal 
-- rownum 은 하나씩 가져올 때 마다 row번호를 출력
from emp

conn hr/oracle
select rownum, employee_id, department_id, last_name, salary
from (select  employee_id, department_id, last_name, salary
      from employees
      where department_id = 80
      order by salary desc)
where rownum < 4;

문>subquery를 사용해서 관리자인 사원들만 검색
select *
from emp
where empno in(select mgr
                from emp);

문>subquery를 사용해서 관리자가 아닌 사원들만 검색
select *
from emp
where empno not in(select mgr
                from emp
                where mgr is not null);
                
in (= or 값을 비교)
not in (!= and 값을 비교)
-> null값과 만나면 최종 결과가 null이 되므로 검색되는 레코드가 없다.
-> 모두다 검색하는 쿼리의 경우 반드시 is not null 연산과 함께 사용할 것.

문> 각 부서별로 평균급여보다 급여를 많이 받는 사원 검색 (이름, 부서, 급여) - corelated subquery, join
-- 메인쿼리에 row 수 만큼 수행해야 함
select a.deptno, a.ename, a.sal
from emp a, (select deptno, avg(sal) avgsal
            from emp
            group by deptno) b
where a.deptno = b.deptno
and a.sal>b.avgsal;

또는

select a.deptno, a.ename, a.sal
from emp a
where a.sal < (select avg(sal)
              from emp
              where a.deptno = deptno);

<좀 이상하지만 내가한거>
select ename, deptno, sal
from emp
where sal >any ( select avg(sal)
            from emp
            group by deptno)
order by deptno desc;

문> 사원들 중에서 2번이상 부서 또는 직무를 변경한 이력이 있는 
사원의 사번, 이름(last_name) 출력하시오

select a.employee_id, a.last_name
from employees a, (select employee_id, count(employee_id) cnt
                   from job_history
                   group by employee_id) b
where a.employee_id = b.employee_id
and b.cnt >=2;


select a.employee_id, a.last_name
from employees  a                     
where 2 <=  (select count(employee_id)
             from job_history
             where a.employee_id =employee_id)
             
             
where exists (co-realted subquery)

문> subquery를 사용해서 관리자인 사원들만 검색
select empno
from emp a
where exists (select '1'
             from emp
             where a.empno = mgr);
             
문> subquery를 사용해서 관리자가 아닌 사원들만 검색
select empno
from emp a
where not exists (select '1'
             from emp
             where a.empno = mgr);   
             
문> 부서별 총 급여가 전체 부서의 평균급여보다 큰 부서번호와 총급여를 출력

with 
dept_sum as (select department_id, sum(salary) sum_sal
             from employees
             group by department_id),
emp_avg as (select avg(sum_sal) total_avg
             from dept_sum)
             
select a.department_id, a.sum_sal
from dept_sum a, emp_avg b
where a.sum_sal > b.total_avg;      
```

## 3. 집합 연산자

### 3.1 특징

- 각각의 select문의 컬럼개수와 타입은 일치해야 한다.
- order by절은 마지막 select문에 선언 가능
- Union, Union all, Minus, Intersect



### 3.2 문제

```sql
문> 20명 사원의 현재와 과거의 모든 부서, 직무 이력을 출력 (동일한 직무와 부서 근무 이력은 중복 데이터로 출력합니다.)
select employee_id, job_id, department_id
from employees
union all
select employee_id, job_id, department_id
from job_history;


문> 20명 사원의 현재와 과거의 모든 부서, 직무 이력을 출력 (동일한 직무와 부서 근무 이력은 한번만 결과 데이터로 출력합니다.)
select employee_id, job_id, department_id
from employees
union
select employee_id, job_id, department_id
from job_history;

문> 20명 사원중 의 현재 직무와 부서를 과거에 동일한 부서와 직무를 담당한 사원 조회
(사원번호, 직무, 부서번호)
select employee_id, job_id, department_id
from employees
intersect
select employee_id, job_id, department_id
from job_history;

문> 입사한 이후에 한번도 직무나 부서를 변경한 적이 없는 사원번호 출력
select employee_id
from employees
minus
select employee_id
from job_history;

문> 전체 사원들의 급여 평균과
    부서별 사원들의 급여 평균과 
    부서와 직무별 사원들의 급여 평균을 단일 결과 집합으로 출력
select to_number(null), to_char(null),avg(sal) -- 개수 맞춰주려고 타입변환함수 호출
from emp
union all
select deptno,to_char(null),avg(sal)
from emp
group by deptno
union all
select deptno, job, avg(sal)
from emp
group by rollup (deptno, job);

*group by rollup 순서
-> group by (A,B,C)
-> group by (A,B)
-> group by (A)
-> group by (전체집계)

*group by cube : 모든 경우의 수가 다들어감
-> group by (A,B,C)
-> group by (A,B),(B,C),(A,C)
-> group by (A),(B),(C)
-> group by (전체집계)
```



