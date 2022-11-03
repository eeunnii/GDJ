DROP SEQUENCE BOARD_SEQ;
CREATE SEQUENCE BOARD_SEQ NOCACHE;

DROP TABLE BOARD;

CREATE TABLE BOARD(
BOARD_NO NUMBER NOT NULL, 
TITLE VARCHAR2(1000 BYTE) NOT NULL,
CONTENT VARCHAR2(4000 BYTE),   --  에디터 사용(더 큰 크기가 필요하면 CLOB사용 - 오라클 피피티에 내용잇음)
WRITER VARCHAR2(100 BYTE) NOT NULL,
CREATE_DATE VARCHAR2(10 BYTE) ,
MODIFY_DATE VARCHAR2(10 BYTE) ,
CONSTRAINT PK_BOARD PRIMARY KEY(BOARD_NO)
);

commit