/* 
    사용자 함수 
    1. Function
    2. 어떤 값을 반환할 때 사용
    3. 사용자가 만든 함수의 개념
    4. 값을 반환할 때 return을 사용 
    5. 입력 파라미터 사용 가능(출력 파라미터 사용 안함)
    6. 값을 확인할 수 있도록 SELECT문과 같은 쿼리문에서 호출
    7. 형식 
        CREATE [OR REPLACE] FUNCTION 함수_이름[(매개변수)]
        RETURN 반환타입
        IS 
            변수선언
        BEGIN
            함수본문
        [EXCEPTION
            예외처리]
        END [함수_이름];
    */
    
--함수 func1 정의 
CREATE OR REPLACE FUNCTION FUNC1
RETURN VARCHAR2 -- 반환타입에서는 크기를 명시하지 않음
IS
BEGIN
    RETURN 'HELLO WORLD'; -- 반환값
END FUNC1;


--함수 func1 호출
--SELECT UPPER(FIRST_NAME)                --UPPER 내장함수
--FROM EMPLOYEES;

SELECT FUNC1()   
-- 정의는 잇으나 전달되는 값이 없음
FROM DUAL;



---함수 func 정의 
-- 사원번호를 전달하면 해당 사원의 full_name이 반환되는 함수 
CREATE OR REPLACE FUNCTION FUNC2(EMP_ID EMPLOYEES.EMPLOYEE_ID%TYPE)
RETURN VARCHAR2
is
    FNAME EMPLOYEES.FIRST_NAME%type;
    LNAME EMPLOYEES.LAST_NAME%type;
begin 
    select FIRST_NAME, LAST_NAME
    into FNAME, LNAME
    from EMPLOYEES
    where EMPLOYEE_ID = EMP_ID;
    return FNAME || ' '|| LNAME;
end FUNC2;


--함수 FUNC2호출
SELECT FUNC2(EMPLOYEE_ID)
FROM EMPLOYEES;


---함수 FUNC3 정의 
--연봉을 전달하면 'A그룹', 'B그룹', 'C그룹'을 반환하는 함수 
--15000 이상 : A그룹
--8000 이상 : B 그룹
--나머지 : C그룹
CREATE OR REPLACE FUNCTION FUNC3(SAL EMPLOYEES.SALARY%TYPE)
RETURN VARCHAR2
ID 
 RESULT VARCHAR(10 BYTE)
BEGIN
    IF 
    IF SAL>=15000 THEN
    RESULT :=
    
    
    --함수 MY_CEIL정의 
    CREATE OR REPLACE FUNCTION MY_CEIL(N NUMBER, DIGIT NUMBER)
    RETURN NUMBER
    IS 
    RESULT NUMBER;
    BIGIN 
    RESULT

-- 함수 FUNC3 호출