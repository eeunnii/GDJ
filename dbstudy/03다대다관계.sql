DROP TABLE PROCEEDING;
DROP TABLE EMPLOYEE;
DROP TABLE PROJECT;
DROP TABLE DEPARTMENT;

--삭제는 생성 반대로 가야함



CREATE TABLE DEPARTMENT (
    DEPT_NO VARCHAR2(15 BYTE) NOT NULL,
    DEPT_NAME VARCHAR2(30 BYTE) NULL,
    DEPT_LOCATION VARCHAR2(30 BYTE) NULL
);


CREATE TABLE PROJECT(
    PJT_NO NUMBER NULL,  -- 넘버 크기가 지정안되있으면 괄호()없이 그냥 NUMBER로 쓸 수 있음
    PJT_NAME VARCHAR2(30 BYTE) NOT NULL,
    BEGIN_DATE DATE NULL,
    END_DATE DATE NULL
);

CREATE TABLE EMPLOYEE(
    EMP_NO NUMBER NOT NULL,
    DEPT_NO VARCHAR2(15 BYTE) NULL,
    POSITION CHAR(10 BYTE) NULL, 
    NAME VARCHAR2(15 BYTE)NULL,
    HIRE_DATE DATE NULL,
    SALARY NUMBER NULL
);

CREATE TABLE PROCEEDING(
    PCD_NO NUMBER NOT NULL,
    EMP_NO NUMBER NULL,
    PJT_NO NUMBER NULL,
    PJT_STATE NUMBER NOT NULL
);    


--기본키 추가 
ALTER TABLE DEPARTMENT          --  테이블 변경
    --ALTER 테이블에서 사용하는거 2개 추가 ADD 수정 MODIFY
    ADD CONSTRAINT PK_DEPARTMENT PRIMARY KEY(DEPT_NO);     --  제약조건을 추가하겠다 

ALTER TABLE EMPLOYEE    
    ADD CONSTRAINT PK_EMPLOYEE PRIMARY KEY(EMP_NO);
    
ALTER TABLE PROCEEDING    
    ADD CONSTRAINT PK_PROCEEDING PRIMARY KEY(PCD_NO);    
    
ALTER TABLE PROJECT 
    ADD CONSTRAINT PK_PROJECT PRIMARY KEY(PJT_NO);  







--외래키 추가 
ALTER TABLE EMPLOYEE
     ADD CONSTRAINT FK_EMPLOYEE_DEPARTMENT FOREIGN KEY(DEPT_NO)
        REFERENCES DEPARTMENT(DEPT_NO);
        
ALTER TABLE PROCEEDING
     ADD CONSTRAINT FK_PROCEEDING_EMPLOYEE FOREIGN KEY(EMP_NO)
        REFERENCES EMPLOYEE(EMP_NO); 
        
ALTER TABLE PROCEEDING
     ADD CONSTRAINT FK_PROCEEDING_PROJECT FOREIGN KEY(PJT_NO)
        REFERENCES PROJECT(PJT_NO);         


--기본키 제거
-- FK에 의해서 참조되고 있는 PK는 제거할 수 없다. 먼저  FK를 제거해야한다.
ALTER TABLE DEPARTMENT
    DROP CONSTRAINT PK_DEPARTMENT; -- 일케하면 오류남. 다른 테이블이 DEPARTMENT를 참조중이라서. 그래서 참조중인 테이블먼저 삭제하고 얘를 지워야함
ALTER TABLE DEPARTMENT    
    DROP PRIMARY KEY;  -- 기본키 제거는 이 문법을 사용
ALTER TABLE PROCEEDING 
    DROP CONSTRAINT PK_PROCEEDING;


--외래키 제거 
ALTER TABLE EMPLOYEE
    DROP CONSTRAINT FK_EMPLOYEE_DEPARTMENT;
    --깃허브참고. 



--한 계정안에 같은 이름의 제약조건 x

--DESCRIBE SYS.USER_CONSTRAINTS;
--SELECT CONSTRAINT_NAME FROM USER_CONSTRAINTS;
--PURGE RECYCLEBIN;