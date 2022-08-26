/*      ★15개 예제 완벽하게 이해하기....

        INT(0또는1) 삽입메서드(DTO) - 외부데이터 객체  {
        INSERT
        RETURN;
        }

    DQL 
    1. DATA QUERY LANGUAGE
    2. 데이터 질의어
    3. 테이블의 데이터를 조회/검색
    4. 데이터베이스에 변화가 없으므로 COMMIT 없음
        (트랜잭션의 대상이 아님)
    5. 형식
        SELECT 칼럼1, 칼럼 2, ....
            FROM 테이블 
        [WHERE 조건] 생략가능.
        [GROP BY 구룹화]
        [HAVING 그룹화_조건식]
        [ORDER BY 정렬] - 오름차순, 내림차순
    6. 실행 순서 
    ⑤      SELECT 칼럼
    ①        FROM 테이블
    ②       WHERE 조건식
    ③     GROP BY 그룹화
    ④      HAVING 그룹화_조건식
    ⑥    ORDER BY 정렬기준
          (*정해진순서대로 읽음)
          
          2~3 생략가능 
    
*/    

--※※※※※※WHERE 절 활용하기※※※※※

-- ♣1. 사원테이블에서 사원 이름 조회하기
SELECT NAME
    FROM EMPLOYEE;
--SELECT의 결과는 TABLE로 나옴

-- 1)테이블 오너(Owner) 명시 가능
SELECT NAME
    FROM SCOTT.EMPLOYEE;
    
-- 2)칼럼에 테이블 명시 
SELECT EMPLOYEE.NAME
    FROM EMPLOYEE;
    
-- 3)테이블에 별명 지정 (자주쓸예정)
SELECT EMP.NAME
    FROM EMPLOYEE EMP; -- 별명 EMP 지정
    
-- 4) 칼럼에 별명(ALIAS)지정 (자주쓸예정)
SELECT NAME AS 사원명 -- 별명 사원명 지정
    FROM EMPLOYEE;
    
----------1~4번까지 동일한 쿼리문



-- ♣2. 사원테이블의 모든 칼럼 조회하기
-- 모든 칼럼 : * (모든칼럼을 의미하는 에스터리크스?)
-- 중요 : 실무에서 *사용금지 -- 감사에 걸림.또 성능을 떨어트림


SELECT *
    FROM EMPLOYEE;
    
-- 모든 칼럼이 필요하면 모두 명시
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE;

-- ♣3. 부서 테이블에서 지역명 조회하기
-- 단, 동일한 지역은 한번만 조회
-- 중복제거 : DISTINCT
SELECT DISTINCT LOCATION
    FROM DEPARTMENT;
  
SELECT DISTINCT DEPT_NAME, LOCATION
    FROM DEPARTMENT;


/* WHERE절 활용하기 */


-- ♣4. 사원테이블 에서 직급이 '과장' 인 사원 조회
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE POSITION = '과장';
 
-- ♣5. 사원 테이블에서 급여가 2000000~5000000인 사원 조회하기
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE SALARY >= 2000000
   AND SALARY <= 5000000;

-- ♣6.사원 테이블에서 소속부서가 1,2인 사원 조회하기
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE DEPART = 1
    OR DEPART = 2;

SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE DEPART IN(1, 2);

-- 7. 사원테이블에서 성별이 없는 사원 조회
--NULL유무
--NULL이다(IS NULL), NULL(IS NOT NULL)이 아니다~ (★별도로 학습하기)
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE GENDER IS NULL;

--8. 사원테이블에서 김씨 조회
--     1) 만능문자(wilf card) 
--          ① % : 모든 문자, 글자 수 제한 없음
--          ② _ : 모든 문자, 한 글자로 제한
--     2) 예시
--          ① 김으로 시작하는 이름 찾기 : 김%
--          ② 김으로 끝나는 이름 찾기 : %김
--          ③김을 포함하는 이름 찾기 : %김%
--     3) 만능문자 연산자 
--          LIKE, NOT LIKE
--      김으로 시작하는 이름 찾기 : 김%

SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE NAME LIKE '김%';  --(★중중요요)


--9. 사원 테이블에서 사원번호가 1로 시작하는 사람 조회하기

SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE EMP_NO LIKE '1%';          --(EMP는 타입이 NUMBER임 지금 넘버랑 텍스트랑 조회중. 넘버랑 텍스트 섞여있으면 오라클은 스스로 캐스팅하여 처리한다.


/* ORDER BY 절*/
-- ASC : 오름차순 정렬, 생략 가능
-- DESC : 내침차순 정렬

--10. 사원 테이블에서 사원명의 가나다순으로 조회하기 
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 ORDER BY NAME ASC; -- 오름차순 △ // 가나다순은 오름차순 ASC 생략가능,,

--11. 사원 테이블에서 급여가 높은 순으로 사원을 조회하기 
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 ORDER BY SALARY DESC; --내림차순▽ (★)  헷갈릴 것 같음

--12. 사원 테이블에서 성별의 가나다순으로 조회하기 
--    오름차순 정렬할 때 NULL값은 마지막에 배치 
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 ORDER BY GENDER; 


--13. 사원 테이블에서 먼저 고용된 순으로 조회하기
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 ORDER BY HIRE_DATE;

--14. 사원 테이블에서 소속부서의 오름차순 정렬로 조회하되, 같은 소속부서내에서는 먼저 고용된 순으로 조회한다.
--      1차 정렬기준 : 소속부서 
--      2차 정렬기준 : 고용일자 
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 ORDER BY DEPART ASC, HIRE_DATE ASC;



/* WHERE절과 ORDER BY절 함께 사용 */
-- WHERE절 먼저, ORDER BY절 나중



--15. 사원 테이블에서 급여가 500만원 이상인 사원들을 고용된순으로 조회하기
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE SALARY >= 5000000
 ORDER BY HIRE_DATE;



/*SELECT문의 처리 순서*/
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE AS HD, SALARY
  FROM EMPLOYEE
WHERE SALARY <=500000
ORDER BY HD;            --별명주고 별명사용

SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY AS S
  FROM EMPLOYEE
WHERE S <=500000
ORDER BY HIRE_DATE;         --이거 안되는 이유 처리 순서에 의해서 된다.안된다.결정됨.
