--프로시저는 한 번에 수행할 쿼리문이 여러 개인 경우 
--프로시저 단위로 묶어서 한 번에 돌려버림

--함수 
--: 사용자가 필요한 기능을 함수로 정의 
--SELECT로 불러다가 씀

--트리거 
--행ROW)삽입/수정/삭제 시 자동으로 처리되는 기능 정의 -- DML정의 
--대표적인 트리거는 삭제가 있다 . 삭제된 정보를 그대로 들고 삭제테이블로 이동. 트리거를 만들어 두면 자동으로 됨
--트리거를 쓸 수 있는 곳이 있고 없는 곳이 있다 .
--가능하면 한두가지 정도는 프로젝트에 넣는거 추천
--PL/SQL를 배운이유는 프로시저,함수,트리거를 배우기 위해서임
--이중에 함수는 예전에 수업하면서 해보자고 한 적이 있음 올림처리 내림처리 
--자리수 지정해서 올내림 처리하는게 오라클에서 지원안되서 만들어서 씀
-- 위와같은 식을 함수로 등록해서 사용함 
--이걸 연습할고임 ㅋ
--프로시저와 함수는 메소드와비슷
--데이터를 넣을 수도 있고 받을 수도 있고 입력파라미터 출력 파라미터 




/*

    프로시저
    1. PROCEURE
    2. 여러개의 쿼리문을 한 번에 실행할 수 있다 .
        (이체 : update문 2개)
    3. 작성된 프로시저는 EXECUTE문으로 실행
        EXECUTE 프로시저();
    4. 형식
        CREATE [OR REPLACE] 프로시저_이름[(매개변수)]
        -- DROP TABLE을 맨 처음에 붙이는 것처럼 OR REPLACE를 붙이면 언제나 새로운걸 쓸 수 있음 근데 여기선 바꿔준다는 개념임
        IS --- AS 가능
            변수선언 
        BEFIN 
            프로시저 본문
        [EXCEPTION
            예외처리]
        END [프로시저_이름];
*/
            
 --프로시저의 PROC1 정의(만들기)
    CREATE OR REPLACE PROCEDURE PROC1
IS
    NAME VARCHAR2(10 BYTE);
BEGIN
    NAME := '정은지';
    DBMS_OUTPUT.PUT_LINE(NAME);
END PROC1;



 
 --프로시저의 PROC1 호출(실행)
EXECUTE PROC1();            ----    메소드 호출하듯이 () 붙여서 실행한다. 

-- ㅇ디스크라이브를 4글자까지 줄일 수 잇ㅇA DESC 프로시저도 4글자로 주릴 수 잇음 EXEC


SET SERVEROUTPUT ON;



--프로시저 proc2 정의 
-- 사원번호가 100인 사원의 FIRST_NAME 서버메시지로 출력하기
CREATE OR REPLACE PROCEDURE proc2
IS
    FNAME EMPLOYEES.FIRST_NAME%TYPE;
BEGIN
    SELECT FIRST_NAME           --FIRST NAME 칼럼 값을
        INTO FNAME               --FNAME 변수에 저장       
        FROM EMPLOYEES          
        WHERE EMPLOYEE_ID = 100;
        DBMS_OUTPUT.PUT_LINE(FNAME);
END PROC2;

--프로시저 PROC2호출
EXECUTE PROC2();
    
    



--프로시저 proc3 정의
--사원번호를 전달하면 해당 사원의 frist_name을 서버 메시지로 출력하기 


--입력 파라미터
--1. 프로시저로 전달하는 값을 저장할 변수 
--2. 형식 : 변수명 IN 타입 

CREATE OR REPLACE PROCEDURE PROC3(EMP_ID IN EMPLOYEES.EMPLOYEE_ID%TYPE)
IS  
    FNAME EMPLOYEES.FIRST_NAME%TYPE;
BEGIN 
    SELECT FIRST_NAME
    INTO FNAME 
    FROM EMPLOYEES
    WHERE EMPLOYEE_ID = EMP_ID;
    DBMS_OUTPUT.PUT_LINE(FNAME);
END PROC3;



--프로시저 PROC2 호출
execute proc3(100);



-- 출력 파라미터
--1. 프로시저의 결과(반환) 값을 저장하는 변수 
--2. 형식 : 변수명 out 타입

--프로시저 proc4 정의 
-- 사원번호-100인 사원의 first_name을 출력 파라미터 fname에 저장하기 
CREATE OR REPLACE PROCEDURE PROC4(FNAME OUT EMPLOYEES.FIRST_NAME%TYPE)
IS
BEGIN 
    SELECT FIRST_NAME
     INTO FNAME
     FROM EMPLOYEES
     WHERE EMPLOYE_ID = 100;
END PROC4;


--프로시저 proc4 호출
DECLARE
    FNAME EMPLOYEES.FIRST_NAME%TYPE;     -- FNAME을 선언한다 여기서에서 선언한 FNAME이 위로 전달 됨(출력파라미터로 사용할 변수)
BEGIN 
    PROC4(FNAME);                 -- PLSQL(프로그래밍) 내부에서 프로시저를 호출할 땐 ECECUTE 생략
    DBMS_OUTPUT.PUT_LINE(FNAME);            
END;
-- 여기있는 값을 프로시저 4에 주는게 아니고 프로시저에서 줌



--프로시저 PROC5 정의
--사원번호가 있으면 FIRST_NAME을 출력 파라미터로 전달. 없으면 'NONAME'을 출력 피라미터로 전달
CREATE OR REPLACE PROCEDURE PROC5(FNAME OUT EMPLOYEES.FIRST_NAME%TYPE)
IS 
BEGIN
    SELECT FRIST_NAME
    INTO FNAME
    FROM 
    EMPLOYEES
    WHERE
    EMPLOYEE_ID = 500;
EXCEPTION
    WHEN OUTERS THE -- 모든 예외를 처리함(WHEN NO_DATE_FOUND THEN 가능)
        FNAME := 'NONAME';
END PROC5;




--프로시저 PROC5 호출
DECLARE
    FNAME EMPLOYEES.FIRST_NAME%TYPE;
BEGIN
    PROC5(FNAME);
    DBMS_OUTPUT.PUT_LINE(FNAME);
END;



--프로시저 PRCO5 호출 
DECLARE
    FNAME EMPLOYEES.FIRST_NAME%TYPE;
BEGIN
    PROC5(FNAME);
    DBMS_OUTPUT.PUT_LINE(FNAME);
END;



--연습 1. 입력 파라미터에 사원번호 전달. 출력 파라미터의 FRIST_NAME 반환하기
--존재하지 않는 사원번호는 출력 파라미터에 'NONAME'반환하기 

--프로시저 PROC6정의 
CREATE OR REPLACE PROCEDURE PROC6(EMP_ID IN EMPLOYEES.EMPLOYEE_ID%TYPE, FNAME OUT EMPLOYEES.FIRST_NAME%TYPE)
IS
BEGIN
    SELECT FIRST_NAME
      INTO FNAME
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = EMP_ID;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        FNAME := 'NoName';
END PROC6;

--프로시저 PROC6 호출
DECLARE
    FNAME EMPLOYEES.FIRST_NAME%TYPE;
BEGIN 
    PROC5(100, FNAME);  -- 사원번호 100인 얘를 FNAME에 두겟다 
    DBMS_OUTPUT.PUT_LINE(FNAME);
    PROC5(101, FNAME);  -- 사원번호 100인 얘를 FNAME에 두겟다 
    DBMS_OUTPUT.PUT_LINE(FNAME);
END;






-- 최종 실습 환경

DROP TABLE BUY;
DROP TABLE CUSTOMER;
DROP TABLE PRODUCT;

-- 제품 테이블
CREATE TABLE PRODUCT(
    PROD_CODE  NUMBER             NOT NULL,  -- 제품코드
    PROD_NAME  VARCHAR2(10 BYTE),            -- 제품명
    PROD_PRICE NUMBER,                       -- 제품가격
    PROD_STOCK NUMBER                        -- 재고
);
-- 제품 기본키
ALTER TABLE PRODUCT
    ADD CONSTRAINT PK_PRODUCT PRIMARY KEY(PROD_CODE);
-- 제품 입력
INSERT INTO PRODUCT VALUES(1000, '진라면', 500, 100);
INSERT INTO PRODUCT VALUES(1001, '신라면', 600, 100);
COMMIT;


-- 고객 테이블
CREATE TABLE CUSTOMER(
    CUST_NO    NUMBER             NOT NULL,   -- 고객번호
    CUST_NAME  VARCHAR2(10 BYTE),             -- 고객명
    CUST_POINT NUMBER                         -- 고객포인트
);
-- 고객 기본키
ALTER TABLE CUSTOMER
    ADD CONSTRAINT PK_CUSTOMER PRIMARY KEY(CUST_NO);
-- 고객 입력
INSERT INTO CUSTOMER VALUES(1, '철수', 0);
INSERT INTO CUSTOMER VALUES(2, '영희', 0);
COMMIT;

-- 구매 테이블
CREATE TABLE BUY(
    BUY_NO     NUMBER NOT NULL,  -- 구매번호
    CUST_NO    NUMBER NOT NULL,  -- 고객번호(FK)
    PROD_CODE  NUMBER NOT NULL,  -- 제품코드(FK)
    BUY_AMOUNT NUMBER            -- 구매수량
);
ALTER TABLE BUY
    ADD CONSTRAINT PK_BUY PRIMARY KEY(BUY_NO);
ALTER TABLE BUY
    ADD CONSTRAINT FK_BUY_CUSTOMER FOREIGN KEY(CUST_NO)
        REFERENCES CUSTOMER(CUST_NO);
ALTER TABLE BUY
    ADD CONSTRAINT FK_BUY_PRODUCT FOREIGN KEY(PROD_CODE)
        REFERENCES PRODUCT(PROD_CODE);

-- 구매 테이블 시퀀스
DROP SEQUENCE BUY_SEQ;
CREATE SEQUENCE BUY_SEQ NOCACHE;


-- 구매 프로시저
-- 1. BUY_PROC(고객번호, 제품코드, 구매수량)
-- 2. 진행해야 할 쿼리
--    1) 구매 테이블에 구매 내역을 INSERT 한다.
--    2) 고객 테이블의 고객포인트를 UPDATE 한다. (총 구매액의 10% 적립)
--    3) 제품 테이블의 재고를 UPDATE 한다.
CREATE OR REPLACE PROCEDURE BUY_PROC
(
    C_NO    IN CUSTOMER.CUST_NO%TYPE,
    P_CODE  IN PRODUCT.PROD_CODE%TYPE,
    BUY_AMT IN BUY.BUY_AMOUNT%TYPE
)
IS
BEGIN
    -- 1) 구매 테이블에 구매 내역을 INSERT 한다.
    INSERT INTO BUY(BUY_NO, CUST_NO, PROD_CODE, BUY_AMOUNT)
    VALUES(BUY_SEQ.NEXTVAL, C_NO, P_CODE, BUY_AMT);
    -- 2) 고객 테이블의 고객포인트를 UPDATE 한다. (총 구매액의 10% 적립, 정수로 올림 처리)
    UPDATE CUSTOMER
       SET CUST_POINT = CUST_POINT + CEIL((SELECT PROD_PRICE
                                             FROM PRODUCT
                                            WHERE PROD_CODE = P_CODE) * BUY_AMT * 0.1)
     WHERE CUST_NO = C_NO;
    -- 3) 제품 테이블의 재고를 UPDATE 한다.
    UPDATE PRODUCT
       SET PROD_STOCK = PROD_STOCK - BUY_AMT
     WHERE PROD_CODE = P_CODE;
    -- 4) 커밋
    COMMIT;
EXCEPTION
    -- 예외 처리(예외 발생 시 아무 일도 없었던 것으로!)
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('예외코드 ' || SQLCODE);
        DBMS_OUTPUT.PUT_LINE('예외메시지 ' || SQLERRM);
        -- 롤백
        ROLLBACK;
END BUY_PROC;


-- 구매 프로시저 호출
EXECUTE BUY_PROC(1, 1000, 10);  -- 고객번호1, 제품코드1000, 구매수량 10

-- 확인
SELECT PROD_CODE, PROD_NAME, PROD_PRICE, PROD_STOCK
  FROM PRODUCT;
SELECT CUST_NO, CUST_NAME, CUST_POINT
  FROM CUSTOMER;
SELECT BUY_NO, CUST_NO, PROD_CODE, BUY_AMOUNT
  FROM BUY;