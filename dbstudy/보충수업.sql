-- 테이블 스페이스 : 테이블을 저장할 수 있는 공간. 
--sys계정에서 만들 수 있음

-- 각 조별로 테이블스페이스를 만들어줘야함
 ---DBF테이블 저장할 때 쓰는 확장명
CREATE TABLESPACE TEMP_SPACE
DATAFILE 'C:/temp/temp.dbf'
SIZE 1M;

--테이블스페이스 삭제  
--인크루팅  : 테이블 다 지움
DROP TABLESPACE TEMP_SPACE;
DROP TABLESPACE TEMP_SPACE
    INCLUDING CONTENTS;
DROP TABLESPACE TEMP_SPACE
    INCLUDING CONTENTS AND DATAFILES;    
DROP TABLESPACE TEMP_SPACE
    INCLUDING CONTENTS AND DATAFILES
        CASCADE CONSTRAINTS;

/*
DROP TABLESPACE TEMP_SPACE INCLUDING CONTENTS;
        AND DATAFILES -- (데이터파일지워주삼)
        KEEP DATAFILES 
        CASCADE --다른 공간에서 관계를 맺고 있는 테이블도 같이 지움
        */

DROP TABLE ORDERS;
DROP TABLE CUSTOMERS;
DROP TABLE PRODUCTS;

        
CREATE TABLE PRODUCTS(
    PRODUCT_NO NUMBER NOT NULL
    ,PRODUCT_NAME VARCHAR2(100 BYTE) NOT NULL
    ,PRODUCT_PRICE NUMBER
    ,PRODUCT_IMAGE VARCHAR2(300 BYTE) 
    ,CONSTRAINT PRODUCT_PK PRIMARY KEY(PRODUCT_NO)
);

CREATE TABLE CUSTOMERS(
    CUSTOMER_NO NUMBER NOT NULL
    ,CUSTOMER_ID VARCHAR2(30 BYTE) NOT NULL
    ,CUSTOMER_NAME VARCHAR2(30 BYTE) 
    ,CUSTOMER_PHONE VARCHAR2(15 BYTE)
    ,CONSTRAINT CUSTOMER_ID_UQ UNIQUE (CUSTOMER_ID)
    ,CONSTRAINT CUSTOMER_PK PRIMARY KEY(CUSTOMER_NO)
);

CREATE TABLE ORDERS (
    ORDER_NO NUMBER NOT NULL
    ,CUSTOMER_NO NUMBER NOT NULL
    ,PRODUCT_NO NUMBER NOT NULL
    ,ORDER_AMOUNT NUMBER
    ,CONSTRAINT ORDER_CUSTOMER_FK FOREIGN KEY (CUSTOMER_NO) REFERENCES CUSTOMERS(CUSTOMER_NO)
    ,CONSTRAINT ORDER_PRODUCT_FK FOREIGN KEY (PRODUCT_NO) REFERENCES PRODUCTS(PRODUCT_NO)
    ,CONSTRAINT ORDER_PK PRIMARY KEY (ORDER_NO)
);

DESC USER_CONSTRAINTS;
SELECT CONSTRAINT_NAME FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'ORDERS';

ALTER TABLE ORDERS DROP CONSTRAINT ORDER_NO;