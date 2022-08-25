-- 테이블 삭제 

DROP TABLE CUSTOMER;

DROP TABLE BANK;

-- 테이블 생성

CREATE TABLE BANK(
    BANK_CODE VARCHAR2(20 BYTE),
    BANK_NAME VARCHAR2(30 BYTE) NOT NULL            --  NULL값 생략 가능함
    
);


-- 테이블 생성

CREATE TABLE CUSTOMER(
    NO NUMBER,
    NAME VARCHAR2(30) NOT NULL,
    PHONE VARCHAR2(30),
    AGE NUMBER,
    BANK_CODE VARCHAR2(20 BYTE)
);

-- 기본키 추가


ALTER TABLE BANK
    ADD CONSTRAINT PK_BANK PRIMARY KEY(BANK_CODE);
    
ALTER TABLE CUSTOMER
    ADD CONSTRAINT PK_NO PRIMARY KEY(NO);


-- 외래키 추가 


ALTER TABLE CUSTOMER
    ADD CONSTRAINT UQ_PH UNIQUE(PHONE); 
    
ALTER TABLE CUSTOMER    
    ADD CONSTRAINT CK_AGE CHECK(AGE BETWEEN 0 AND 100);         -- BETWEEN 0 AND 100 날짜나 기준 정할 때 사용함. CHECK 말고
    
ALTER TABLE CUSTOMER     
    ADD CONSTRAINT FK_BANK_CODE_CUSTOMER FOREIGN KEY (BANK_CODE) 
        REFERENCES BANK(BANK_CODE)
            ON DELETE CASCADE;
    
    
-- ◁------< -    실선 : FK 필수 아니라는 뜻
-- ◁ㅡㅡㅡㅡ<-     점선 : FK 필수
-- ◁------○<-   ON DELETE SET NULL      -- NULL값을 채우겟다. 단 FK 조건이 NOT NULL로 되어있으면 안됨 NULL이 들어갈 수 잇게 설정해놔야함

-- ◁------X<-    ON DELETE CASCADE   -- 함께 지워지겠다 


-- 테이블 변경하기(ALTER TABLE)
-- 1. 칼럼의 추가 : ALTER TABLE 테이블 ADD 칼럼명 데이터타입 [제약조건]
-- 2. 칼럽 수정 : ALTER TABLE 테이블 MODIFY 칼럼명 데이터타입[제약조건]
-- 3. 칼럼 삭제 : ALTER TABLE 테이블 DROP COLUM 칼럼명
-- 4. 칼럼의 이름 : ALTER TABLE 테이블 RENAME COLUMN OLD 이름 TO NEW 이름
-- 5. 테이블 이름 : ALTER TABLE 테이블 RENAME RENAME TO NEW이름-  올드이름할 필요 X알터 테이블 뒤에 적혀있으니까 

-- 1. BANK 테이블에 BANK_PHONE 칼럼을 추가하시오.
    ALTER TABLE BANK 
        ADD BANK_PHONE VARCHAR2(20 BYTE) NULL;


-- 2. CUSTOMER 테이블에 GRADE 칼럼을 추가하시오. ('VIP', 'GOLD', 'SILVER' 중 하나의 값만 가진다.) -- CHECK 제약조건
    ALTER TABLE CUSTOMER
        ADD GRADE VARCHAR2(6 BYTE) CHECK(GRADE IN('VIP', 'GOLD', 'SILVER'));    --  ★★텍스트는 작은 따옴표로 묶어줌


-- 3. BANK 테이블의 BANK_NAME 칼럼을 VARCHAR2(15 BYTE)로 수정하시오.
    ALTER TABLE BANK 
      MODIFY BANK_NAME VARCHAR2(15 BYTE);        --  NULL유무를 지정하지 않으면 알아서 상속받음 문제에 NULL이야기없으니까 생략


-- 4. BANK 테이블의 BANK_NAME 칼럼을 NULL로 수정하시오.
    ALTER TABLE BANK
        MODIFY BANK_NAME VARCHAR2(15 BYTE) NULL;


-- 5. CUSTOMER 테이블의 AGE 칼럼을 삭제하시오.
    ALTER TABLE CUSTOMER
        DROP COLUMN AGE;

-- 6. CUSTOMER 테이블의 NO 칼럼과 NAME 칼럼이름을 CUST_NO와 CUST_NAME으로 변경하시오.
    ALTER TABLE CUSTOMER
        RENAME COLUMN CUST_NO TO CUST_NAME;
    ALTER TABLE CUSTOMER
        RENAME COLUMN CUST_NAME TO CUST_NO;
        
-- 7. BANK 테이블의 이름을 BANK_TBL 로 수정하시오.
ALTER TABLE BANK
    RENAME TO BANK_TBL;
        
    

    
    
    