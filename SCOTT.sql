DROP TABLE EMPLOYEE;
DROP TABLE DEPARTMENT;



CREATE TABLE DEPARTMENT(
    DEPT_NO NUMBER NOT NULL,
    DEPT_NAME VARCHAR2(15 BYTE) NOT NULL,
    LOCATION VARCHAR2(15 BYTE)  NOT NULL
);

CREATE TABLE EMPLOYEE(
    EMP_NO    NUMBER            NOT NULL,
    NAME      VARCHAR2(20 BYTE) NOT NULL,
    DEPART    NUMBER            NULL,
    POSITION  VARCHAR2(20 BYTE) NULL,
    GENDER    CHAR(2)           NULL,
    HIRE_DATE DATE              NULL, 
    SALARY    NUMBER            NULL
);



--기본키
ALTER TABLE EMPLOYEE 
    ADD CONSTRAINT PK_EMPLOYEE PRIMARY KEY (EMP_NO);
 
ALTER TABLE DEPARTMENT 
    ADD CONSTRAINT PK_DEPARTMENT PRIMARY KEY (DEPT_NO);  
    
-- 외래키
ALTER TABLE EMPLOYEE
    ADD CONSTRAINT FK_EMPLOYEE_DEPARTMENT FOREIGN KEY (DEPART) 
    REFERENCES DEPARTMENT(DEPT_NO)
       -- ON DELETE SET NULL;
       -- ON UPDATE CASCADE;
       ON DELETE CASCADE;
       
       
 DROP SEQUENCE DEPARTMENT_SEQ;
CREATE SEQUENCE DEPARTMENT_SEQ
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 100
    NOCACHE
    NOCYCLE;

-- 부서 테이블에 행(Row) 삽입
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (DEPARTMENT_SEQ.NEXTVAL, '영업부', '대구');
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (DEPARTMENT_SEQ.NEXTVAL, '인사부', '서울');
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (DEPARTMENT_SEQ.NEXTVAL, '총무부', '대구');
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (DEPARTMENT_SEQ.NEXTVAL, '기획부', '서울');

-- 작업의 완료
COMMIT;


-- 사원 테이블에서 사용할 사원_시퀀스
DROP SEQUENCE EMPLOYEE_SEQ;
CREATE SEQUENCE EMPLOYEE_SEQ
    START WITH 1001
    NOCACHE;

-- 사원 테이블에 행(Row) 삽입
INSERT INTO 
    EMPLOYEE
VALUES
    (EMPLOYEE_SEQ.NEXTVAL, '구창민', 1, '과장', 'M', '95/05/01', 5000000);
INSERT INTO
    EMPLOYEE
VALUES
    (EMPLOYEE_SEQ.NEXTVAL, '김민서', 1, '사원', 'F', '17/09/01', 2000000);
INSERT INTO
    EMPLOYEE
VALUES
    (EMPLOYEE_SEQ.NEXTVAL, '이은영', 2, '부장', NULL, '90-09-01', 5500000);
INSERT INTO
    EMPLOYEE
VALUES
    (EMPLOYEE_SEQ.NEXTVAL, '한성일', 2, '과장', 'M', '93-04-01', 5000000);
INSERT INTO
    EMPLOYEE
VALUES
    (EMPLOYEE_SEQ.NEXTVAL, '신현준', 3, '대리', 'M', '98-12-01', 3500000);

COMMIT;


SELECT NAME
    FROM employee;
    
SELECT NAME
    FROM SCOTT.employee;
    
SELECT EMPLOYEE.NAME
    FROM EMPLOYEE;
    
SELECT EMP.NAME
    FROM EMPLOYEE EMP;
    
SELECT NAME AS 사원명
    FROM EMPLOYEE;
    
SELECT EMP_NO,NAME,POSITION,GENDER,HIRE_DATE,SALARY
    FROM EMPLOYEE;

SELECT DISTINCT LOCATION
    FROM DEPARTMENT;

SELECT DISTINCT DEPT_NAME,LOCATION -- 
    FROM department;
    
SELECT EMP_NO,NAME,DEPART,POSITION,GENDER,HIRE_DATE,SALARY
    FROM EMPLOYEE
    WHERE POSITION = '과장';

SELECT EMP_NO,NAME,DEPART,POSITION,GENDER,HIRE_DATE,SALARY
    FROM EMPLOYEE
    WHERE SALARY>=2000000
    AND SALARY<=5000000;
    
SELECT EMP_NO,NAME,DEPART,POSITION,GENDER,HIRE_DATE,SALARY
    FROM EMPLOYEE
    WHERE DEPART=1
    OR DEPART=2;
    
SELECT EMP_NO,NAME,DEPART,POSITION,GENDER,HIRE_DATE,SALARY
    FROM EMPLOYEE
    WHERE DEPART IN(1,2);    

SELECT EMP_NO,NAME,DEPART,POSITION,GENDER,HIRE_DATE,SALARY
    FROM EMPLOYEE
    WHERE GENDER IS NULL;
    
SELECT EMP_NO,NAME,DEPART,POSITION,GENDER,HIRE_DATE,SALARY
    FROM EMPLOYEE
    WHERE NAME LIKE '김%';

SELECT EMP_NO,NAME,DEPART,POSITION,GENDER,HIRE_DATE,SALARY
    FROM EMPLOYEE
    WHERE EMP_NO LIKE '1%';

SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
    FROM EMPLOYEE
    ORDER BY NAME ASC;
   
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
    FROM EMPLOYEE
    ORDER BY SALARY DESC;

SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
    FROM EMPLOYEE
    ORDER BY GENDER;

SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
    FROM EMPLOYEE
    ORDER BY hire_date;
    
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
    FROM EMPLOYEE
    ORDER BY DEPART ASC, hire_date DESC;
   
--15. 사원 테이블에서 급여가 500만원 이상인 사원들을 고용된순으로 조회하기  
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
    FROM EMPLOYEE
    WHERE salary >=5000000
    ORDER BY hire_date ASC;
    
    
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY AS S
  FROM EMPLOYEE;
 
 
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY AS S
  FROM EMPLOYEE
WHERE salary >=0
ORDER BY HIRE_DATE;      