/*
    일대다 관계의 테이블 만들 때 고려해야될 것들
    1. 명청
        - 부모테이블 : 일(1),  PK를 가진 테이블
        - 자식 테이블 : 다(M), PK를 가진 테이블
    2. 생성 - 순서가 있음
        - 부모 테이블을 먼저 만들어야함. 자식테이블을 나중에 생성!
    3. 삭제
        - 생성의 역순으로.
        - 자식테이블을 먼제 삭제한 후 부모테이블 삭제. 
*/




--학교 테이블 
DROP TABLE SCHOOL;
CREATE TABLE SCHOOL(
    SCH_CODE NUMBER(1) NOT NULL PRIMARY KEY,
    SCH_NAME VARCHAR2(20) NOT NULL
);

-- 테이블 만들 때 다시 못 만들음. 테이블 수정해야할 때. 테이블 삭제하고 새로 생성하기.
-- 하지만 다른사람들도 같이 테이블을 쓰는 경우 함부로 삭제하면 안됨 수업은 상관없으니까 삭제 후 새로 생성
-- 테이블이 없는 상태에서 DROP TABLE하면 오류나고 다음 문장 실행(자바와 다름--자바는 오류나면 실행종료되니까)


--NOT NULL 제외하고 나머지 제약 조건을 마지막에 몰아서 배치하는 방법
DROP TABLE SCHOOL;
CREATE TABLE SCHOOL(
    SCH_CODE NUMBER(1) NOT NULL,
    SCH_NAME VARCHAR2(20) NOT NULL,
    PRIMARY KEY(SCH_CODE)
);

--제약조건에 이름을 주는 방법 - 각 제약조건 앞에 이름을 붙여줌 -- NOT NULL빼고 붙일 수 있음 
DROP TABLE SCHOOL;
CREATE TABLE SCHOOL(
    SCH_CODE NUMBER(1) NOT NULL CONSTRAINT PK_SCHOOL PRIMARY KEY,
    SCH_NAME VARCHAR2(20) NOT NULL
);

-- 제약 조건 5개 NO NULL, UNIQUE, CHECK, PK, FK (NOT NULL빼고 이름 주기 가능, 일반적으로 PK, FK에 이름 붙임)



DROP TABLE SCHOOL;
CREATE TABLE SCHOOL(
    SCH_CODE NUMBER(1) NOT NULL,
    SCH_NAME VARCHAR2(20) NOT NULL,
    CONSTRAINT PK_SCHOOL PRIMARY KEY(SCH_CODE)
);


-- 학생테이블만들기+ 외래키 포함해서 
DROP TABLE STUDENT;
CREATE TABLE STUDENT(
    STU_NO CHAR(5 BYTE) NOT NULL PRIMARY KEY,
    SCH_CODE NUMBER(1) NOT NULL REFERENCES SCHOOL(SCH_CODE), --그냥 NUMBER가 아니고 NUMBER(1) 인 이유 : 기본키의 타입에 맞추기 위해서 
                                                           --외래키와 기본키의 관계 : 부모자식 관계임. 관계를 맺을 때 PK를 가진 테이블이 부모테이블이다. FK를 가진테이블을 자식테이블이라고 함
    STU_NAME VARCHAR2(20 BYTE) NULL     -- NULL 값도 가능함
);


-- 아랫단으로 몰아보기. 
DROP TABLE STUDENT;
CREATE TABLE STUDENT(
    STU_NO CHAR(5 BYTE) NOT NULL,
    SCH_CODE NUMBER(1) NOT NULL, 
    STU_NAME VARCHAR2(20 BYTE) NULL,
    PRIMARY KEY (STU_NO),
    FOREIGN KEY (SCH_CODE) REFERENCES SCHOOL(SCH_CODE)    --외래키
);


--- 제약조건 아래로 몬 상태에서 이름 주기 
DROP TABLE STUDENT;
CREATE TABLE STUDENT(
    STU_NO CHAR(5 BYTE) NOT NULL,
    SCH_CODE NUMBER(1) NOT NULL, 
    STU_NAME VARCHAR2(20 BYTE) NULL,
    CONSTRAINT PK_STUDENT PRIMARY KEY (STU_NO),
    CONSTRAINT FK_STUDENT_SCHOOL FOREIGN KEY (SCH_CODE) REFERENCES SCHOOL(SCH_CODE)    --외래키이름 줄 때 이름 너무 길면 오류남 30글자 
);


--최종 정리 
-- 생성 : 부모 먼저, 자식 나중
-- 삭제 : 자식 먼저, 부모 나중


DROP TABLE STUDENT;
DROP TABLE SCHOOL;
CREATE TABLE SCHOOL(
    SCH_CODE NUMBER(1) NOT NULL,
    SCH_NAME VARCHAR2(20 BYTE) NOT NULL,
    CONSTRAINT PK_SCHOOL PRIMARY KEY(SCH_CODE)

);
CREATE TABLE STUDENT(
    STU_NO CHAR(5 BYTE) NOT NULL,
    SCH_CODE NUMBER(1) NOT NULL, 
    STU_NAME VARCHAR2(20 BYTE) NULL,
    CONSTRAINT PK_STUDENT PRIMARY KEY (STU_NO),
    CONSTRAINT FK_STUDENT_SCHOOL FOREIGN KEY (SCH_CODE) REFERENCES SCHOOL(SCH_CODE)

);


--다대다 관계는 실제로 불가능하다 테이블 3개정도는 잇어야함~
--일대다관계를 2번하는걸 다대다 관계라고 생각하면 됨

CREATE TABLE SCH_CLASS(
    CLASS_NO CHAR(1 BYTE) NOT NULL CONSTRAINT PK_SCH_CLASS_NO PRIMARY KEY,
    CLASS_NAME VARCHAR2(5 BYTE) NOT NULL,
    CALSS_POR VARCHAR2(5 BYTE) NOT NULL
);


CREATE TABLE SUGANG(
    APPLI_NO NUMBER(1) NOT NULL PRIMARY KEY,
    STU_NO CHAR(5 BYTE) NOT NULL CONSTRAINT FK_STU_NO REFERENCES STUDENT(STU_NO),
    CLASS_NO CHAR(1 BYTE) NOT NULL CONSTRAINT FK_CLASS_NO REFERENCES SCH_CLASS(CLASS_NO)
);

DROP TABLE SUGANG;
DROP TABLE SCH_CLASS;

DESCRIBE SYS.USER_CONSTRAINTS;
    