-- 1. 순위
--     1) Rank() OVER(ORDER BY 순위구할칼럼 ASC) : 오름차순 순위, 낮은 값이 1등, ASC는 생략가능               --- 낮은 숫자가 1등 ▲
--     2) RANK() OVER(ORDER BY 순위구할칼럼 DESC) : 내림차순 순위, 높은 값이 1등
--     3) 같은 값이면 같은 등수(동점)로 처리 


--  1) EMPLOYEES 테이블의 사원 정보를 연봉이 높은 순으로 조회하기
--      연봉 순위를 함께 조회하기
SELECT 
        RANK() OVER(ORDER BY SALARY DESC) AS 연봉순위           --  RNAK만 넣으면 정렬까지 됨
        , EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY
        FROM 
        EMPLOYEES;
        
--   2) EMPLOYEES 테이블의 사원 정보를 입사순으로 조회하기 
--      먼저 입사한 사원이 1등

SELECT
        RANK() OVER(ORDER BY HIRE_DATE ASC) AS 입사순위
        ,EMPLOYEE_ID
        ,FIRST_NAME
        ,LAST_NAME
        ,HIRE_DATE
     FROM
        EMPLOYEES
    WHERE
        DEPARTMENT_ID IS NOT NULL;
        

--- 2. 그룹화 
---      OVER(PARTITION BY 그룹화 칼럼)
---      그룹화 작업을 수행하므로 집계함수(그룹함수)와 함께 사용이 가능함
SELECT DISTINCT department_id 
,SUM(SALARY) OVER(PARTITION BY DEPARTMENT_ID) AS 부서별연봉합계
,FLOOR(AVG(SALARY) OVER(PARTITION BY DEPARTMENT_ID)) AS 부서별연봉평균
,MAX(SALARY) OVER(PARTITION BY DEPARTMENT_ID) AS 부서별최대연봉
,MIN(SALARY) OVER(PARTITION BY DEPARTMENT_ID) AS 부서별최저연봉
,MAX(SALARY) OVER(PARTITION BY DEPARTMENT_ID) AS 부서별최대연봉
FROM EMPLOYEES
WHERE DEPARTMENT_ID IS NOT NULL;

--RANK() 함수와 PARTITION BY 함께 사용하면 그룹 내 순위 구하기 가능
SELECT
        RANK() OVER(PARTITION BY DEPARTMENT_ID ORDER BY SALARY DESC) AS 부서내연봉순위
        ,EMPLOYEE_ID
        ,FIRST_NAME
        ,LSAT_NAME
        ,SALARY
        ,DEPARTMENT_ID
    FROM EMPLOYEES
    ORDER BY 
    DEPARTMENT_ID ASC;
    
-- 3. 분기 처리(IF)
-- DECODE(표현식)
--      , 값1, 결과1
--      , 값2, 결과2
--      , 값3, 결과3
-- 표현식의 결과과 값 1이면 결과 1반환, 값 2이면 결과 2 반환
-- 표현식의 결과와 값의 비교는 동등비교(=)만 가능함


--조인이 많으면 성능이 떨어짐
-- 조인? 디코드??  

-- JOIN없이 EMPLOYEES 테이블만 이용하여 EMPLOYEE_ID, DEPARTMENT_ID, DEPARTMENT_NAME 조회하기
SELECT
    EMPLOYEE_ID
    ,DECODE(DEPARTMENT_ID
        ,10, 'Administration'
        ,20, 'Marketion'
        ,30, 'Purchasing'
        ,40, 'Human Resources'
        ,50, 'Shipping'
        ,60, 'IT') AS 부서명
    FROM
        EMPLOYEES;
        
-- PHONE_NUMBER REGION
-- 011          MOBILE
-- 515          EAST
-- 590          WEST
-- 603          SOUTH
-- 650          NORTH

-- EMPLOYEE_ID, PHONE_NUMBER, REGION 조회하기
SELECT
        EMPLOYEE_ID
        , PHONE_SUMBER
        ,DECODE(SUBSTR(PHONE_NUMBER,1,3))           --SUBSTR의 결과는 STRING
        ,'011','MOBILE'
        ,'515, 'EAST'
        ,590, 'WEST'
        ,603,'SHUTH'
        ,'650','NORTH') AS REGINON 
        
    FROM EMPLOYEES;
    
    

    
    
--4. 분기 표현 식
----CASE
--     WHEN조건식 THEN 결과값1
--     WHEN조건식2 THEN 결과값 2
--     ELSE 결과괎N
--END

--SLSART<10000 : C THEN 'C'
--SALARY>20000 : C THEN 'B'


SELECT
       EMPLOYEE_ID
     , SALARY
     , CASE
            WHEN SALARY < 10000 THEN 'C'
            WHEN SALARY < 20000 THEN 'B'
            ELSE 'A'
       END AS 구분
  FROM
       EMPLOYEES;


---깃허브 보기



--EMPLOYEE_ID, HIRE_DATE(YYYY-MM-DD), 근무개월수, 퇴직금 정산유무
--퇴직금정산대상 : 근무개월수가 240개월 이상이면 '정산대상', 아니면 빈 문자열

SELECT 
    EMPLOYEE_ID AS 사원번호
    ,TO_CHAR(HIRE_DATE, 'YYYY-MM-DD') AS 고용날짜
    ,FLOOR(MONTHS_BETWEEN(SYSDATE,HIRE_DATE)) AS 근무개월수
    ,CASE
            WHEN FLOOR(MONTHS_BETWEEN(SYSDATE,HIRE_DATE)) > 240 THEN '정산대상'
            WHEN FLOOR(MONTHS_BETWEEN(SYSDATE,HIRE_DATE)) <= 240 THEN ' '        -- A>240, A<240 하면 240이 포함이 안된 식 240값이 NULL로 나오게 됨
        END AS 퇴직금정산유무   
   FROM EMPLOYEES;  