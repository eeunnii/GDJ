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

-- 1. BANK 테이블에 BANK_PHONE 칼럼을 추가하시오.


-- 2. CUSTOMER 테이블에 GRADE 칼럼을 추가하시오. ('VIP', 'GOLD', 'SILVER' 중 하나의 값만 가진다.)


-- 3. BANK 테이블의 BANK_NAME 칼럼을 VARCHAR2(15 BYTE)로 수정하시오.


-- 4. BANK 테이블의 BANK_NAME 칼럼을 NOT NULL로 수정하시오.


-- 5. CUSTOMER 테이블의 AGE 칼럼을 삭제하시오.


-- 6. CUSTOMER 테이블의 NO 칼럼과 NAME 칼럼이름을 CUST_NO와 CUST_NAME으로 변경하시오.

    
    
    