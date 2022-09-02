

-- 숫자 함수 

-- 1. 제곱
-- POWER(A, B) : A의 B제곱
SELECT POWER(2,10) FROM DUAL;

-- 2. 제곱근(루트)
-- SQRT(A) : 루트 A
SELECT SQRT(25) FROM DUAL;
-- SQRT(5) : 25 -- 틀림. 시험문제에 가끔나옴

-- 3. 절대값
SELECT ABS(5) FROM DUAL;
SELECT ABS(-5) FROM DUAL;

-- 4. 나머지 
-- MOD(A,B) : A를 B로 나눈 나머지 
SELECT MOD(7,2) FROM DUAL;

-- 5. 부호 판별
-- SIGN(A) : A의 부호가 +이면 1, -이면 -1, 0이면 0을 반환한다. 
SELECT SIGN(5), SIGN(-5), SIGN(0) FROM DUAL;

-- 6. 정수로 올림
-- CEIL(A) : 실수 A를 정수로 올림
SELECT CEIL(1.1), CEIL(-1.1)FROM DUAL;

-- 7. 정수로 내림
--  FLOOR(A) : 실수 A를 정수로 내림 
SELECT FLOOR(1.1), FLOOR(-1.1) FROM DUAL;

-- 8. 원하는 자릿수로 절사 
-- TRUNC(A, [DIGIT]) : 실수 A를 DIGIT 자릿수로 절사, DIGIT 생략하면 정수로 절사

SELECT
        TRUNC(1.9999)       -- 1
       ,TRUNC(1.9999, 1)    -- 1.9
       ,TRUNC(1.9999, 2)    -- 1.99
    FROM
        DUAL;
SELECT
        TRUNC(9999,-1)---9990(원 단위 절사)
       ,TRUNC(9999,-2)---9900
    FROM
        DUAL;
        
-- 9. 원하는 자릿수로 반올림 
-- ROUND(A, [DIGIT]) : 실수 A를 DIGIT 자릿수로 반올림, DIGIT 생략하면 정수로 반올림  
SELECT
        ROUND(145.45) --- 145
       ,ROUND(145.45, 1)--145.5 
       ,ROUND(145.45, -1) -- 150
    FROM
        DUAL;
        
        
-- 문제 발생
-- 1. 원하는 자릿수로 올림  (★?)                     DIGIT
--      1) 소수 1자리 : CEIL(값 *10) / 10               1
--      2) 소수 2자리 : CEIL(값 *100) / 1000             2
--      3) 소수 3자리 : CEIL(값 *1000) / 1000            3
--      4) 정수       : CEIL(값 *1)/1                    0
--      5) 일의 자리 : CEIL(값 *0.1)/0.1                -1
--      6) 십의 자리 : CEIL(값*0.01)/0.01               -2
--      7) 백의 자리 : CEIL(값*0.001)/0.001              -3
--      일반화       : CEIL(값*POWER(10,DIGIT)) / POWER(10, DIGIT)
SELECT 
        CEIL(1.111*POWER(10,1))/POWER(10,1)           --1.2
        ,CEIL(1.111*POWER(10,2))/POWER(10,2)          --1.12
        ,CEIL(11111*POWER(10,-1))/POWER(10,-1)        --11120
        ,CEIL(11111*POWER(10,-2))/POWER(10,-2)        --11200
    FROM
        DUAL;
        
-- 2. 원하는 자릿수로 내림
--      CEIL 대신 FLOOR를 사용하면 됨




-- 날짜 함수 


-- 1. 현재 날짜와 시간
SELECT SYSDATE, SYSTIMESTAMP FROM DUAL;

-- 2. 원하는 형식으로 날짜와 시간 조회
--   TO_CHAR 함수 : 날짜를 문자로 변환해서 조회
SELECT
        TO_CHAR(SYSDATE, 'YYYY-MM-DD HH:MI:SS')     --  12시간 표기법
       ,TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS')    -- 24시간 표기법
    FROM
        DUAL;
        
-- 3. 단위(년,월,일,시,분,초) 추출 함수      
-- EXTRSACT ( 단위 FROM 날짜 )
SELECT
        EXTRACT(YEAR FROM SYSDATE) AS 년도 
        ,EXTRACT(MONTH FROM SYSDATE) AS 월
        ,EXTRACT(DAY FROM SYSDATE) AS 일
        ,EXTRACT(HOUR FROM SYSTIMESTAMP) AS 시 -- UTC 기준 9시간 후.
        ,EXTRACT(MINUTE FROM SYSTIMESTAMP) AS 분
        ,EXTRACT(SECOND FROM SYSTIMESTAMP) AS 초
        ,EXTRACT(TIMEZONE_HOUR FROM SYSTIMESTAMP) AS 시  -- 우선 TIMEZONE 설정이 필요
        ,FLOOR(EXTRACT(SECOND FROM SYSTIMESTAMP)) AS 초
    FROM
        DUAL;

--단위(년, 월, 일, 시, 분, 초) 추출은 TO_CHAR 함수로도 가능함
SELECT
        TO_CHAR(SYSDATE, 'YYYY')
        ,TO_CHAR(SYSDATE, 'M')
        ,TO_CHAR(SYSDATE, 'D')
        ,TO_CHAR(SYSDATE, 'HH24')
        ,TO_CHAR(SYSDATE, 'MI')
        ,TO_CHAR(SYSDATE, 'SS')
    FROM
        DUAL;
        
-- 날짜 연산
-- 1) 하루(1일)를 숫자 1로 처리 
--      12시간을 숫자 0.5로 처리한다.
-- 2) 특정 단위 후 날짜 
--      (1) 1년 후: 12개월 후, 함수가 없음(지원할 필요가 없어서)
--      (2) 1개월 후 : ADD_MONTHS 함수 사용
--      (3) 1일 후 : +1, 함수 없음

SELECT
        SYSDATE - 1 AS 어제 
       ,SYSDATE + 1 AS 내일
       ,SYSDATE - 0.5 AS "12시간 전"   --  첫글짜 숫자로 하면 안먹혀서 큰 따옴표로 처리 
       ,SYSDATE + 0.5 AS "12시간 후"
       ,TO_CHAR(SYSDATE-0.5, 'MM/DD HH24:MI:SS') AS "12시간 전"
       ,TO_CHAR(SYSDATE+0.5, 'MM/DD HH24:MI:SS') AS "12시간 후"
    FROM
        DUAL;
        
-- 5. N 개월 전후 날짜 
--      ADD_MONTHS(날짜, N)
SELECT 
         ADD_MONTHS(SYSDATE, -1) AS "1개월전"
        ,ADD_MONTHS(SYSDATE, 1) AS "1개월후"
    FROM
        DUAL;
        
-- 6. 경과한 개월 수 (외울필요x)
--MONTHS_BETWEEN(최근날짜, 이전 날짜) : 두 날짜 사이의 경과한 개월 수 
SELECT 
        MONTHS_BETWEEN(SYSDATE, HIRE_DATE)
    FROM
        EMPLOYEES;
        

-- 나중에 프로젝트 할 때 비밀번호 3개월 변경 주기 만들 때 이 함수 사용됨.

        