DROP TABLE EMPLOYEE;
DROP TABLE DEPARTMENT;



CREATE TABLE DEPARTMENT(
    DEPT_NO NUMBER NOT NULL,
    DEPT_NAME VARCHAR2(15 BYTE) NOT NULL,
    LOCATION VARCHAR2(15 BYTE) NOT NULL
);
CREATE TABLE EMPLOYEE(
    EMP_NO NUMBER NOT NULL,
    NAME VARCHAR2(20 BYTE) NOT NULL,
    DEPART NUMBER,
    POSITION VARCHAR2(20 BYTE),
    GENDER CHAR(2 BYTE),
    HIRE_DATE DATE,
    SALARY NUMBER
);




ALTER TABLE EMPLOYEE 
    ADD CONSTRAINT PK_EMPLOYEE PRIMARY KEY (EMP_NO);
 
ALTER TABLE DEPARTMENT 
    ADD CONSTRAINT PK_DEPARTMENT PRIMARY KEY (DEPT_NO);    

ALTER TABLE EMPLOYEE
    ADD CONSTRAINT FK_EMPLOYEE_DEPARTMENT FOREIGN KEY (DEPART) 
    REFERENCES DEPARTMENT(DEPT_NO)
       -- ON DELETE SET NULL;
       -- ON UPDATE CASCADE;
       ON DELETE CASCADE;
    
    
    
    /* 
    DML 
    1. DATA MANIPULATION LANGUAGE
    2. 데이터 조작어 
    3. 행(ROW, RECORD, TUPLE) 단위 삽입, 수정, 삭제
    4. 트랜잭션(작업) 완료를 위해 COMMIT이 필요함
    5. 취소도 가능함. ROLLBACK 사용가능
    6. 종류 
        1)INTSERT INTO VALUES
        2) UPDATE SET WHERE
        3) DELETE FROM WHERE
        
        
        
    */
    -- 행 삽입
    -- 1. 지정한 칼럼에 삽입
    -- INSERT INTO 테이블 ♤(칼럼1, 칼럼2...)♤ VALUES(값1, 값2..)
    -- 정보를 삽입할 칼럼 VALUES
    -- ↑칼럼의 갯수와 값 갯수가 같아야함. 
    --♤ ♤ 생략가능
    -- 2. 모든 칼럼에 데이터 삽입 (칼럼 리스트 생략))
    -- INSERT INTO 테이블 VALUES(값1, 값2)
    
    --부서 테이블에 행(ROW) 삽입
    --부모 테이블(PK)에 먼저 삽입을 해야 함. (부서번호가 있어야 사원 삽입 가능함)

INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES 
    (1, '영업부', '대구');
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (2, '인사부', '서울');
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (3, '총무부', '대구');
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (4,'기획부', '서울');
    
--작업의 완료
COMMIT;

/*
INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME) VALUES(5, '개발부'); -- LOCATION에 들어가는 값이 없는 경우는 NULL값을 넣는거임
-- 이 문장은 실행안됨. 왜냐면 테이블 만들 때 DEPT_NAME를 NOT NULL로 했기 때문이다. 그래서 NULL값 못들어가서 오류남
    
    
INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME,LOCATION) VALUES(5, '개발부','부에노스아이리스');  
    -- INSERT 실패. 이유는 크기가 넘어가서
    
INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME,LOCATION) VALUES(4, '개발부','인천');    
    -- 실패. 유니크 걸림. 4가 중복 데이터라서 실행안댐
    
    
    *바이트로 지정해서 한글쓸 때 조심해야함!
*/


-- 사원테이블에 행(ROW) 삽입
-- 부모 테이블(관계에서 FK를 가진 테이블)은 참조 무결성에 위배되지 않는 데이터만 삽입 가능
-- 부서(부서번호) - 사원(소속부서)
-- PK               FK
-- 1,2,3,4        - 1,2,3,4 중 하나만 가능 
INSERT INTO 
    EMPLOYEE
VALUES
    (1001, '구창민', 1, '과장', 'm', '95/05-01', 5000000);
    
INSERT INTO
    EMPLOYEE
VALUES
    (1002, '김민서', 1, '과장', 'F', '17/09-01', 2000000); 
    
    INSERT INTO
    EMPLOYEE
    VALUES
    (1003, '이은영',2,'부장',NULL,'90-09-01',5500000);
    
    INSERT INTO 
    EMPLOYEE
    VALUES
    (1004, '한성일', 2, '과장', 'M', '93-04-01',500000);
    
    --신경써야될거 : NULL 넣는거. 칼럼 생략하면 테이블에잇는칼럼에 모든 자료를 넣는거임
    
    COMMIT;
    
    --INSERT INTO EMPLOYEE(EMP_NO, NAME, DEPART) VALUES (1005, '아무개',5);
    
    --외부데이터 INSERT
    -- 엑셀 데이터(시트마다 TABLE 마트불 2갱ㄱ
    --테이블 하나가 시트 하나씀. 
    
    
    -- 외부 데이터 import
    -- 엑셀 데이터(시트마다 테이블 1개)
    -- 테이블 선택 후 우클릭 - [데이터 임포트]
    
    -- 부서 테이블 수정
    -- 부서 번호가 1인 부서의 지역을 '인천'으로 수정 
UPDATE DEPARTMENT
   SET LOCATION = '인천' -- 여기서의 = 는 대입.
 WHERE DEPT_NO = 1; --(여기서 같다라는 의미로 사용됨)
 
-- 부서 번호가 3인 부서명을 '전략부', 지역을 '부산'으로 수정한다
UPDATE DEPARTMENT
   SET DEPT_NAME= '전략부'
     , LOCATION = '부산'
 WHERE DEPT_NO = 3;
 --특정조건을실행할지 안할지 
    --WHERE 1=1 // IF문이랑 같음!

--부서번호가 3인 부서의 부서번호를 6으로 수정
/*
부서 - 사원
1    -1
2    -1
3    -2
4    -2
5    -1


PK 부서번호를 6으로 바꾸는건 문제가 되지 않음. 유니크에 안걸림.
근데 부서를 3->6하면 
사원의 3번은 참조무결성 위배규칙에 걸림
그래서 안됨. 
mYSQL에는 CASCADE 옵션을 붙여서 함께 수정하는 명령문이 있는데.
ORACLE SQL에서는 안됨.
*/

-- 3. 부서번호가 3인 부서의 부서번호를 6으로 수정
-- DEPARTMENT의 부서번호를 EMPLOYEE가 참조중이므로 수정이 안 됨
-- 해결책
-- 1. 외래키 일시중지
-- 2. 수정
-- 3. 외래키 재시작

ALTER TABLE EMPLOYEE
    DISABLE CONSTRAINT FK_EMPLOYEE_DEPARTMENT;  -- 외래키 중지

UPDATE EMPLOYEE
   SET DEPART = 6
 WHERE DEPART = 3;

UPDATE DEPARTMENT
   SET DEPT_NO = 6
   WHERE DEPT_NO = 3;
   
--DEPARTMENT의 부서번호를 EMPLOYEE가 참조중이므로 수정이 안됨
--
ALTER TABLE EMPLOYEE
    ENABLE CONSTRAINT FK_EMPLOYEE_DEPARTMENT;  -- 외래키 시작




-- 4. 부서번호 1인 사원들의 월급을 100000 인상
UPDATE EMPLOYEE
   SET SALARY = SALARY + 100000
 WHERE DEPART = 1;

-- 5. 직급이 '과장'인 사원들의 월급을 10% 인상
UPDATE EMPLOYEE
   SET SALARY = SALARY * 1.1
 WHERE POSITION = '과장';



--테이블삭제
--1. 부서번호가 4인 부서를 삭제
-- 부서번호가 4아닌 행을 참조하는 사원이 없으므루 정상 삭제됨
DELETE
  FROM DEPARTMENT
 WHERE DEPT_NO =4;
 
--2. 부서번호가 1인 부서를 삭제 
-- 부서번호가 1인 행(ROW)의 소속부서가 NULL값으로 함께 변경 
-- 외래키 옵션으로 ON DELETE SET NULL 처리를 하였기 때문에 
-- 부서번호가 1인 행의 소속부서가 모두 NULL값으로 함께 변경됨
DELETE
  FROM DEPARTMENT
 WHERE DEPT_NO = 1;
 
// 

