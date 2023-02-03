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
    
    
    
    /* 
    시퀀스
    1. SEQUENCE
    2. 일렬번호를 생성하는 데이터베이스 객체
    3. 자동으로 증가하는 번호를 생성
    4. 기본키(PK)에서 주로 사용 - 인공키  
    5. NEXTVAL를 이용하면 새로운 번호가 생성됨 - 번호하나 뽑는거라고 생각하기
    6. CURRVAL를 이용하면 현재 번호를 확인 - 번호가 몇 번까지 갔는지 확인
    
    네추럴 키 : 회원이름처럼 원래 갖고 있는거
    인공키(:추가로 구현해 주는 데이터) : 회원번호같은거   
    */
    
    /*
    시퀀스 생성 형식
    
    CREATE SEQUENCE 시퀀스_이름 
    
        START WITH 시작값    -- 생략하면 1이다 ,생성이후 유일하게 시퀀스에서 수정이 안됨
        INCREMENT BY 증가값  -- 생략하면 1
        MINVALUE 최소값      --시퀀스가 가질 수 있는 최솟값
        MAXVALUE 최대값      -- 시퀀스가 가질 수 있는 최댓값
    
        CACHE 사용유무       -- NOCHACHE 권장 기억장소에서 자기가 쓸 번호를 미리 가져오겠다는 개념. 중간에 번호가 뜸(?)
                         -- CACHE 설정시 메모리에 시퀀스 값을 미리 할당하고
                         -- NOCACHE 설정시 시퀀스값을 메로리에 할당하지 않음
                      
                   
        CYCLE 사용유무       -- 생략하면 NOCYCLE 번호가 순환. 1~11 돌고 다시 1번가는거 -PK에서 사용한다면 NOCYCLE
                         -- CYCLE 설정시 최대값에 도달하면 최소값부터 다시 시작 
                         -- NOCYCLE 설정시 최대값 생성 시 시퀀스 생성중지
                    
        번호가 맘에안들면 다 지우고 다시 생성행함
        시퀀스 지우고 처음부터 다시 시작 - 시퀀스 다시 만드는 방법
        마이너스로 가는 것도 가능함. 
    */
    


-- 부서 테이블에서 사용할 부서_시퀀스 
DROP SEQUENCE DEPARTMENT_SEQ;

CREATE SEQUENCE DEPARTMENT_SEQ
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 100 
    NOCACHE
    NOCYCLE;
 

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
    (DEPARTMENT_SEQ.NEXTVAL, '총무부', '대구');      --데이터베이스에서 뽑아서 쓰는 방식
    
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (DEPARTMENT_SEQ.NEXTVAL,'기획부', '서울');
    
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


-- 사원테이블에서 
DROP SEQUENCE EMPLOYEE_SQ;

CREATE SEQUENCE EMPLOYEE_SQ
START WITH 1000
NOCACHE;





INSERT INTO 
    EMPLOYEE
VALUES
    (EMPLOYEE_SQ.NEXTVAL, '구창민', 1, '과장', 'm', '95/05/01', 5000000);
    
INSERT INTO
    EMPLOYEE
VALUES
    (EMPLOYEE_SQ.NEXTVAL, '김민서', 1, '과장', 'F', '17/09-01', 2000000); 
    
    INSERT INTO
    EMPLOYEE
    VALUES
    (EMPLOYEE_SQ.NEXTVAL, '이은영',2,'부장',NULL,'90-09-01',5500000);
    
    INSERT INTO 
    EMPLOYEE
    VALUES
    (EMPLOYEE_SQ.NEXTVAL, '한성일', 2, '과장', 'M', '93-04-01',500000);
    
    
    -- 오류가 발생하는 INSERT
    -- INSERT는 실패하였으나 시퀀스의 번호는 사용했음
    INSERT INTO
        EMPLOYEE
    VALUES
        (EMPLOYEE_SEQ.NEXTVAL, '신현준', 5, '대리', 'M', '98-12-01', 3500000);
   
    
    --정상데이터 다시 INSERT
    INSERT INTO
         EMPLOYEE
    VALUES
         (EMPLOYEE_SQ.NEXTVAL, '신현준', 3, '대리','M', '98-12-01',35000);
    
    
    
    --삽입메소드의 결과가 0이면 노삽입 1이면 삽임 !
    --AUTOINCREMENT
    
    
    --신경써야될거 : NULL 넣는거. 칼럼 생략하면 테이블에잇는칼럼에 모든 자료를 넣는거임
    
    COMMIT;
    
    
    
/***************************************************************/

DROP TABLE SAMPLE;
CREATE TABLE SAMPLE(
    NO1 NUMBER,
    NO2 NUMBER
);

DROP SEQUENCE SAMPLE_SEQ;
CREATE SEQUENCE SAMPLE_SEQ NOCACHE;

-- 최초 1번은 NEXTVAL를 사용해야 CURRCAL도 사용
INSERT INTO SAMPLE(NO1) VALUES(SAMPLE_SEQ.CURRVAL); 
-- SAMPLE_SEQ.CURRVAL is not yet defined in this session
-- 라고 오류남. 
    
-- NEXTCAL, CURRVAL 함께 사용
INSERT INTO SAMPLE(NO1, NO2) VALUES(SAMPLE_SEQ.NEXTVAL ,  SAMPLE_SEQ.CURRVAL);

COMMIT;