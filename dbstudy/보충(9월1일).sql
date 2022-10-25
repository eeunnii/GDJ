-- HR 계정 접속



-- 1. DEPARTMENT_NAME의 오름차순 정렬 기준으로 전체 사원들의 EMPLOYEE_ID, FIRST_NAME, DEPARTMENT_ID를 조회하시오.
-- 조인
SELECT E.EMPLOYEE_ID, E.FIRST_NAME, D.DEPARTMENT_ID
FROM EMPLOYEES E INNER JOIN DEPARTMENTS D
ON D.DEPARTMENT_ID = E.DEPARTMENT_ID
ORDER BY D.DEPARTMENT_ID ASC;


-- 타입이 섞여있는 경우의 오름차순 정렬은 아래와 같이 됨
-- 문자, 숫자, 날짜, NULL



-- 2. DEPARTMENT_ID가 20인 사원중에서 전체 평균 연봉(SALARY) 이상을 받는 사원의 EMPLOYEE_ID, FIRST_NAME, DEPARTMENT_ID, SALARY를 조회하시오.


-- 조건의 칼럼 조회하는 칼럼이 모두 한 테이블에 있음. 고로 조인이 아니다

--서브쿼리 : 전체 평균 연봉
--메인쿼리 : SELECT 조회할칼럼 FROM 테이블 WHERE DEP부서번호 = 20 AND 연봉>= (전체평균연봉)
SELECT  EMPLOYEE_ID, FIRST_NAME, DEPARTMENT_ID, SALARY
FROM EMPLOYEES
WHERE DEPARTMENT_ID = 20
AND SALARY > (SELECT AVG(SALARY)
                FROM EMPLOYEES);    --최소 쿼리문 FROM까지 써줘야함. 


-- 3. JOB_ID가 'IT_PROG'인 사원중에서 가장 높은 연봉(SALARY)을 받는 사원의 연봉보다 더 많은 연봉을 받는 사원들의 EMPLOYEE_ID, FIRST_NAME, JOB_ID, SALARY를 조회하시오.




SELECT EMPLOYEE_ID, FIRST_NAME, JOB_ID, SALARY
FROM EMPLOYEES
WHERE SALARY > (SELECT MAX(salary)
                FROM EMPLOYEES
                WHERE JOB_ID = 'IT_PROG');



-- 4. EMPLOYEE_ID가 115인 사원과 동일한 JOB_ID와 DEPARTMENT_ID를 가진 사원의 EMPLOYEE_ID, FIRST_NAME, JOB_ID, DEPARTMENT_ID를 조회하시오.


--서브쿼리 : 사원번호가 115인 사원

SELECT EMPLOYEE_ID, FIRST_NAME, JOB_ID, DEPARTMENT_ID
FROM employees                                                          --WHERE절의 () = () 양쪽 갯수도 같고 조건도 같아야함
WHERE (JOB_ID, DEPARTMENT_ID) = ( SELECT JOB_ID, DEPARTMENT_ID          --칼럼은 2개지만 ROW 1개임. (115)  결론: 단일행서브쿼리
                                  FROM employees                        --IN은 다중행에서 사용. IN으로도 가능. 
                                    WHERE EMPLOYEE_ID = '115');





-- 5. LOCATION_ID가 1000~1500 사이인 국가들의 COUNTRY_ID, COUNTRY_NAME을 조회하시오.


---조인---
select DISTINCT C.COUNTRY_ID, C.COUNTRY_NAME   -- C.COUNTRY_ID도 가능 양쪽에 다 있는 키니까 
FROM COUNTRIES C INNER JOIN LOCATIONS L             -- DISTINCT 중복을 제거할 칼럼 앞에 적어줌
ON C.COUNTRY_ID = L.COUNTRY_ID
WHERE L.LOCATION_ID BETWEEN 1000 AND 1500;

---서브쿼리---
--서브쿼리 : LOCATION_ID가 1000~1500인 지역의 COUNTRY_ID가져오기 (다중행 - ROW가 여러개)
--메인쿼리 : SELECT 칼럼 FROM COUTRIES WHERE COUNTRY_ID IN (서브쿼리)          --  다중행이니까 = 말고 IN써야됨

SELECT COUNTRY_ID, COUNTRY_NAME
FROM COUNTRIES
WHERE COUNTRY_ID IN (SELECT DISTINCT COUNTRY_ID             --서브쿼리만 조회하면 중복된 데이터 나와서 서브쿼리에서 중복데이터제거해준다.
                    FROM LOCATIONS
                    WHERE LOCATION_ID BETWEEN 1000 AND 1500);





-- 6. MANAGER가 아닌 사원들의 EMPLOYEE_ID, FIRST_NAME을 조회하시오.

--MANAGER가 아닌 사원 : EMPLOYEE_ID가 MANAGER_ID에 없는 사원
--서브쿼리 : MANAGER_ID의 중복을 제거결과(MANAGER의 번호 모아두기)
--메인쿼리 : SELECT 칼럼 FROM 테이블 WHERE EMPLOYEE_ID ▶NOT IN◀(서브쿼리)


---------------????????암것도 안나오는디????????---------
SELECT EMPLOYEE_ID, FIRST_NAME
FROM EMPLOYEES 
WHERE MANAGER_ID NOT IN ( SELECT DISTINCT MANAGER_ID
                            FROM EMPLOYEES 
                            WHERE MANAGER_ID IS NOT NULL);                   --서브쿼리에서 NULL발생.그래서 메인쿼리까지 실행했을 때 값이 아무것도 안나옴
                            
--서브쿼리의 결과는 NULL을 포함하면 메인 쿼리가 동작하지 않는다.                             





-- 7. 근무하는 CITY가 'Southlake'인 사원들의 EMPLOYEE_ID, FIRST_NAME를 조회하시오.

SELECT E.EMPLOYEE_ID, E.FIRST_NAME, L.CITY              --이너조인경우에서 테이블 3개 이상이면 콤마가 쉽다(?)
FROM LOCATIONS L, DEPARTMENTS D, EMPLOYEES E
WHERE L.LOCATION_ID = D.LOCATION_ID
AND D.DEPARTMENT_ID = E.DEPARTMENT_ID
AND L.CITY = 'Southlake';           --   SOUTHLAKE와 Southlake는 다르다



-------집가서 서브쿼리로 해보기






-- 8. 가장 많은 사원이 근무 중인 부서의 DEPARTMENT_ID와 근무 인원 수를 조회하시오.

--가장 많은 사원이 근무 중인 부서 : 부서별 사원수를 구해야 알 수 있다->그룹화 필요함

--조건 : 근무중인 사원수 = 최대사원수
--having절? where절?
--답은 해빙인데... 왜? ★집계함수를 이용한 조건은  having 절만 가능함★

SELECT COUNT(*), DEPARTMENT_ID
FROM EMPLOYEES
WHERE DEPARTMENT_ID IS NOT NULL
GROUP BY DEPARTMENT_ID                  
HAVING COUNT(*) = (SELECT MAX(COUNT(*))
                    FROM EMPLOYEES
                    GROUP BY DEPARTMENT_ID);
                    
SELECT DEPARTMENT_ID, COUNT(*)
FROM EMPLOYEES
WHERE DEPARTMENT_ID IS NOT NULL
GROUP BY DEPARTMENT_ID                  
HAVING COUNT(*) = (SELECT MAX(COUNT(*))
                    FROM EMPLOYEES
                    GROUP BY DEPARTMENT_ID);
                    
--PARTITION BY 를 활용 
SELECT B.DEPARTMENT_ID, B.CNT
  FROM (SELECT A.DEPARTMENT_ID, A.CNT
          FROM (SELECT DISTINCT DEPARTMENT_ID, COUNT(*) OVER(PARTITION BY DEPARTMENT_ID) AS CNT
                  FROM EMPLOYEES) A
        ORDER BY CNT DESC) B
 WHERE ROWNUM = 1;


-- 9. 전체 사원 중 최대 연봉을 받는 사원의 EMPLOYEE_ID, FIRST_NAME, SALARY를 조회하시오.
--인라인뷰 A 의 의미 : 최대 연봉이 맨 위에 있는 테이블 
--인라인뷰 B 의 의미 : 연봉순으로 정렬된 테이블에 행 번호(RN)를 부착시켜 둔 테이블  
--별명을 붙여줘야 제약조건없이 원하는 번호를 붙일 수 있다(?)
--최종결과:인라인뷰B에서 행번호(RN)가 1인 행 조회 

SELECT B.EMPLOYEE_ID, B.FIRST_NAME, B.SALARY
FROM (SELECT ROWNUM AS RN, A.EMPLOYEE_ID, A.FIRST_NAME, A.SALARY
        FROM EMPLOYEE_ID, FIRST_NAME, SALARY)
                FROM(SELECT EMPLOYEE_ID, FIRST_NAME, SALARY
                    FROM EMPLOYEES
                    ORDER BY SALARY DESC) A)B
WHERE B.RN = 1;

        


-- 10. 연봉 TOP 11 ~ 20 사원의 EMPLOYEE_ID, FIRST_NAME, SALARY를 조회하시오.

--인라인뷰 A : 연봉순으로 정렬된 뒤 행 번호(RN)가 부착된 테이블 

SELECT A.EMPLOYEE_ID, A.FIRST_NAME, A.SALARY 
FROM ( SELECT ROW_NUMBER() OVER(ORDER BY SALARY DESC) AS RN, EMPLOYEES
    FROM EMPLOYEES) A
