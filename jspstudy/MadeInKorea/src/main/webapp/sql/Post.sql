
DROP SEQUENCE POST_SEQ;
CREATE SEQUENCE POST_SEQ NOCACHE;

DROP TABLE POST;
CREATE TABLE POST(
NO NUMBER PRIMARY KEY,
WITER VARCHAR2(1000 BYTE) NOT NULL,
TITLE VARCHAR2(1000 BYTE),
CREATE_DATE DATE,
CONTENT VARCHAR2(4000 BYTE)
);

INSERT INTO POST VALUES (POST_SEQ.nextval, '작성자','제목',SYSDATE,'내용');
