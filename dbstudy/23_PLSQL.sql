/*

PL/SQL : ORACLE
VARIABLE
IF
WHLIE

---다음주에 끝나면 JDBC
PL/SQL
    PROCEDURE
    FUNCTION
    TRIGGER
    
    
    JDBC : 자바와 데이터베이스의 연결
    DBCP

*/







/*
 PL/SQL
1. 오라클의 프로그래밍 처리가 가능한 SQL
2. 프로그래밍 형식
        [DECLARE]
            [변수 선언]
        BEGIN
            수행할 PL/SQL
        END;
3. PL/SQL의 데이터(변수, 상수)는 서버메시지로 출력해볼 수 있다.
4. 서버메시지 출력을 위해서 최초 1회 설정이 필요(디폴트 SET SERVEROUTPUT OFF)
    SET SEVEROUTPUT ON; 

*/


-- 기초 데이터 준비 
-- HR 계정의 EMPLOYEES 테이블을 SCOTT계정으로 복사해오기 

CREATE TABLE EMPLOYEES
    AS(SELECT *
        FROM HR.EMPLOYEES);
-- 기본키 다시 생성
ALTER TABLE EMPLOYEES
    ADD CONSTRAINT PK_EMPLOYEES PRIMARY KEY (EMPLOYEE_ID);
    
    
    
-- 서버 메세지 출력 가능 상태로 변경 
-- 한 번만 실행하면 됨
SET SERVEROUTPUT ON;

-- 서버메시지 출력
BEGIN 
    DBMS_OUTPUT.PUT_LINE('HELLO');  -- HELLO 출력 후 줄 바꿈
END;


--★1. 스칼라 변수 선언
--스칼라 변수 : 직접 타입을 명시하는 변수
--대입 연산자 : 콜론등호(:=)
--변수 선언은 DECLARE에서 처리 


DECLARE 
    --스칼라 변수 선언
    NAME VARCHAR2(20 BYTE);
    AGE NUMBER(3);
BEGIN
    NAME := '정은지';
    AGE := 24;
    DBMS_OUTPUT.PUT_LINE('내 이름은 ' || NAME || '입니다');
    DBMS_OUTPUT.PUT_LINE('내 나이는 ' || AGE || '살입니다.');
END;

-- ★2.참조 변수 선언
-- 참조 변수 : 특정 칼럼의 타입을 그대로 사용하는 변수 
-- 선언 방법 
-- 테이블명.칼럼%TYPE

DECLARE 
        --참조 변수 선언
    NAME EMPLOYEES.FIRST_NAME%TYPE;  --FIRST_NAM 와 같은 타입
BEGIN
    NAME := '정은지';
    DBMS_OUTPUT.PUT_LINE('내 이름은 ' || NAME || '입니다');
END;


-- 2. 참조 변수 활용
-- 테이블의 데이터를 읽어 참조 변수에 저장. SALARY를 읽어오겟다. 
-- SELECT 칼럼 INTO 변수 FROM 테이블 WHERE 조건식
-- 참조변수 하나의 데이터를 읽어올거임
-- 문제. EMPLOYEE_ID가 100인 회원의 FIRST_NAME, LAST_NAME, SALARY 정보를 참조 변수에 저장
DECLARE 
    F_NAME EMPLOYEES.FIRST_NAME%TYPE;
    L_NAME EMPLOYEES.LAST_NAME%TYPE;
    V_SALARY EMPLOYEES.SALARY%TYPE;
BEGIN
    SELECT
        FIRST_NAME,LAST_NAME,SALARY 
    INTO 
        F_NAME,L_NAME,V_SALARY    
    FROM
       EMPLOYEES
    WHERE
       EMPLOYEE_ID = 100;           -- EMPLOYEE_ID는 PK이임 값 하나 나옴 
    DBMS_OUTPUT.PUT_LINE(F_NAME || L_NAME || V_SALARY);
END;

-- ★3. 레코드 변수 선언
--레코드 : 필드의 모임 
--DB에서 레코드란?
--레코드 변수 : 여러 칼럼을 동시에 저장하는 변수 
--레코드 변수 정의와 선언 과정으로 진행 

DECLARE
    --레코드 변수 타입 정의(타입 만들기)
        TYPE MY_TYPE IS RECORD(
            V_FNAME EMPLOYEES.FIRST_NAME%TYPE,
            V_LNAME EMPLOYEES.LAST_NAME%TYPE,
            V_SALARY EMPLOYEES.SALARY%TYPE
            );
            --레코드 변수 선언(변수 만들기 )
            V_ROW MY_TYPE;   -- V_ROW 는 변수 3개가 모이는거임
    BEGIN 
        SELECT
            FIRST_NAME, LAST_NAME, SALARY
        INTO
            V_ROW
        FROM
            EMPLOYEES
        WHERE
            EMPLOYEE_ID = 100;
        DBMS_OUTPUT.PUT_LINE(V_ROW.V_FNAME||V_ROW.V_LNAME||V_ROW.V_SALARY);              --V_ROW 이걸 클래스라고 가정. 뒤에는 객체임)
END;


DECLARE
    -- 레코드 변수 타입 정의(타입 만들기)
    TYPE MY_TYPE IS RECORD(
        V_FNAME EMPLOYEES.FIRST_NAME%TYPE,
        V_LNAME EMPLOYEES.LAST_NAME%TYPE,
        V_SALARY EMPLOYEES.SALARY%TYPE
    );
    -- 레코드 변수 선언(변수 만들기)
    V_ROW MY_TYPE;
BEGIN
    SELECT
           FIRST_NAME, LAST_NAME, SALARY
      INTO
           V_ROW
      FROM EMPLOYEES
     WHERE
           EMPLOYEE_ID = 100;
    DBMS_OUTPUT.PUT_LINE(V_ROW.V_FNAME || V_ROW.V_LNAME || V_ROW.V_SALARY);
END;


-- ★4.행 변수 선언하기
--행 (ROW) 전체를 저장할 수 있는 타입
-- 선언 방법
--테이블 %ROWTYPE

DECLARE
    --행 변수 선언
    V_ROW EMPLOYEES%ROWTYPE;
BEGIN
    SELECT *
        INTO V_ROW
        FROM EMPLOYEES
        WHERE EMPLOYEE_ID = 100;
        DBMS_OUTPUT.PUT_LINE(V_ROW.FIRST_NAME|| V_ROW.LAST_NAME||V_ROW.SALARY);
END;


-- 5. IF
/*
        IF 조건식 THEN 
            실행문 
        ELSIF 조건식 THEN
            실행문
        ELSE 
            실행문 
        END IF;
        
        
        
*/



-- 1)성인/미성년자
DECLARE
    AGE NUMBER(3);
    RESULT VARCHAR2(20 BYTE);
BEGIN
    AGE := 45;
    IF AGE >= 20 THEN
        RESULT := '성인';
    ELSE
        RESULT := '미성년자';
    END IF;
    
    DBMS_OUTPUT.PUT_LINE(AGE || '살은 ' || RESULT || '입니다.');
END;

-- 2) 학점(A,B,C,D,F)
DECLARE
    SCORE NUMBER(3);
    GRADE CHAR(1 BYTE);
BEGIN
    SCORE := 100;
    IF GRADE <= 100 THEN
        RESULT := 'A';
    ELSIF GRADE <90 THEN
        RESULT := 'B';
    ELSIF GRADE <80 THEN
        RESULT := 'C';
    ELSIF GRADE <70 THEN
        RESULT := 'D';
    ELSE GRADE <60 
        RESULT := 'F';
    END IF;
END;

--▼선생님의 답
DECLARE
    SCORE NUMBER(3);
    GRADE VARCHAR2(1 BYTE);
BEGIN
    SCORE := 100;
    IF SCORE >= 90 THEN
        GRADE := 'A';
    ELSIF SCORE >= 80 THEN
        GRADE := 'B';
    ELSIF SCORE >= 70 THEN
        GRADE := 'C';
    ELSIF SCORE >= 60 THEN
        GRADE := 'D';
    ELSE
        GRADE := 'F';
    END IF;
    DBMS_OUTPUT.PUT_LINE(SCORE || '점은 ' || GRADE || '학점입니다.');
END;


--3) EMPLOYEE_ID가 150인 사원의 연봉을 참조하여 
--15000 이상이면 '고연봉', 10000 이상이면 '중연봉', 나머지는 '저연봉'
DECLARE 
    SAL EMPLOYEES.SALARY%TYPE;
    RES VARCHAR2(20 BYTE);
BEGIN 
    SELECT SALARY
    INTO SAL
    FROM EMPLOYEES
    WHERE EMPLOYEE_ID = 150;
    IF SAL >= 15000 THEN
        RES := '고연봉';
    ELSIF SAL >= 10000 THEN
        RES := '중연봉';
    ELSE
        RES := '저연봉';
    END IF;
    DBMS_OUTPUT.PUT_LINE('연봉' || SAL || '은' || RES || '입니다');
END;





--6. CASE 문
/*
CASE
     WHEN 조건식 THEN
         실행문
     WHEN 조건식 THEN
         실행문
     ELSE 
         실행문
END CASE;

--WHEN이 IF 대용으로 사용되는 경우가 있다
*/

--주민번호를 이용해 성별 조회하기 
DECLARE  
    SNO VARCHAR2(14 BYTE);
    GENDER_NUM VARCHAR2(1 BYTE);
    GENDER VARCHAR2(1 BYTE);
BEGIN
    SNO := '901010-1234567';       --SUBSTR(SNO,-7,1) 뒤에서 7번째 글자 --SUBSTR(SNO,8,1)
    SELECT SUBSTR(SNO,8,1)
    INTO GENDER_NUM
    FROM DUAL;
    
    CASE
        WHEN GENDER_NUM = '1' THEN
        GENDER := 'M';
        WHEN GENDER_NUM = '2' THEN
        GENDER := 'F';
    END CASE;
    DBMS_OUTPUT.PUT_LINE('성별은 '||GENDER||'입니다.');
    END;
    
    
    --WHILE 문
    /*
    WHILE 조건식 LOOP
        실행문
    END LOOP;
    */
    
--1) 1~5 출력하기 
DECLARE 
    N_NUMBER(1);
BEGIN
    N := 1;
    WHILE N<=5 LOOP
        DBMS_OUTPUT.PUT_LINE(N);
        N := N+1;
    END LOOP;
END;
    
--2) EMPLOYYES 테이블의 모든 사원의 FIRST_NAME, LAST_NAME 조회
--FIRST_NAME과 LAST_NAME을 레코드 변수에 저장하고 해당 값을 조회
--레코드 변수는 사원 한명의 정보만 저장 할 수 있으므로 한명씩 저장 후 출력 
--사원번호는 100~206 값을 가짐 


DECLARE 
    --레코드 변수 (정의한 후 선언 순으로 사용해야함_
    
    
--참조변수선언 
EMP_NO EMPLOYEES.EMPLOYEE_ID%TYPE;
--레코드 변수 정의 
TYPE NAME_TYPE IS RECORD(
FNAME EMPLOYEES.FIRST_NAME%TYPE,
LNAME EMPLOYEES.LAST_NAME%TYPE
);
-- 레코드 변수 선언(변수 만들기)
FULL_NAME NAME_TYPE;
   
BEGIN
    -- 사원번호(100~206) 기준으로 WHILE LOOP
    EMP_NO := 100;
    WHILE EMP_NO <=206 LOOP
    SELECT FIRST_NAME, LAST_NAME
    INTO FULL_NAME
    FROM EMPLOYEES
    WHERE EMPLOYEE_ID = EMP_NO;
    DBMS_OUTPUT.PUT_LINE(FULL_NAME.FNAME || ' ' || FULL_NAME.LNAME);
    EMP_NO := EMP_NO+1;
    
    END LOOP;
END;

--8. FOR문
/* 
FOR 변수IN시작 .. 종료. LOOP
    실행문
END LOOP;
*/

--1) 1~5
DECLARE
    N   NUMBER(1);
BEGIN
    FOR N IN 1..5 LOOP
        DBMS_OUTPUT.PUT_LINE(N);
    END LOOP;
END;

--2) 짝수/홀수
DECLARE
        N NUMBER;
        MODULAR NUMBER(1);
        RESULT VARCHAR2(10 BYTE);
BEGIN
    FOR N IN 1..10 LOOP
        SELECT MOD(N,2)
            INTO MODULAR
            FROM DUAL;
        IF MODULAR = 0 THEN
            RESULT := '짝수';
        ELSE
            RESULT := '홀수';
        END IF;
        DBMS_OUTPUT.PUT_LINE(N||'은'||RESULT||'입니다,');
    END LOOP;
END;
    
--3) EMPLOYEES 테이블의 모든 사원의 연봉 합계/평균 조회하기 
DECLARE
        EMP_NO EMPLOYEES.EMPLOYEE_ID%TYPE;    
        SAL_EMPLOYEES.SALARY%TYPE;
        TOTAL NUMBERL;   --합계에서 가져오는거 아니고 선언
        CNT NUMBER;
        AVERAGE NUMBER;
BEGIN
    TOTAL := 0;
    CNT := 0;
    
    
    
    
    
----(4)DEPARTMENT_ID가 50인 사원정보를 DEPT50테이블에 복사하기 
--(1) EMPLOYEES 와 구조가 동일한 DEPT50테이블 생성
--(2) 행 변수를 이용해 EMPLOYEES 정보 읽기 
--(3) 행 변수의 DEPARTMENT_ID가 50 이면 DEPT50에 INSERT




--12. 예외처리
/* 
    EXCEPTION
        WHEN 예외 THEN 
            예외처리 
        WHEN 예외 THEN
            예외처리
        WHEN OTHERS THEN
            예외처리 
*/           
DECLARE 
    FNAME EMPLOYEES.FIRST_NAME%TYPE;
BEGIN
    SELECT FIRST_NAME
    INTO FNAME
    FROM EMPLOYEES
   -- WHERE EMPLOYEE_ID=0;  -- 존재하지 않는 사원 조회
    WHERE DEPARTMENT_ID = 50;
EXCEPTION
    WHEN NO_DATA_FOUND THEN  -- 예외
        DBMS_OUTPUT.PUT_LINE('조회된 데이터가 없음');
       
        
        WHEN OTHERS THEN  -- 예외
        DBMS_OUTPUT.PUT_LINE('예외코드'||SQLCODE);
        DBMS_OUTPUT.PUT_LINE('예외메시지' || SQLERRM);
END;

