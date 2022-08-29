-- 주석임
/* 주석 */

CREATE TABLE a_tbl(NO NUMBER);      -- 소문자로 지정했는데 실제 만들어지는 파일은 대문자임~~(오라클에서 대소문자 구분XX)
                                    -- 실행문이 다른데(대소문자) 만들어지는 것은 똑같음. 그니까 대문자로 스타일 맞추기.
                                    -- 대문자로 적을거면 대문자로 소문자로 적을거면 소문자로 

/* 
1. CHAR(SIZE) : 고정 길이 문자 타입(1~2000바이트)
2. VARCHAR2(SIZE) : 가변 길이 문자 타입(1~4000바이트)
3. NUMBER(p,s) : 정밀도(p), 스케일(s)로 표현되는 숫자 타입
    -정밀도(p) : 정수 + 소수점 모두 포함하는 전체 유효 숫자가 몇개냐 ??...유효숫자에서 0 제외됨.
    -스케일(s) : 소수 몇 자리냐?? 소수점 자리수를 의미함.
    예시) 
    NUMBER : 최대 38자리 숫자(22바이트)
    NUMBER(3) : 최대 3자리 정수 
    NUMBER(5,2) : 전체 5자리, 소수점 2자리 실수(100.45)  -- 소수점 빼고 전체 5자리, 소수점 2자리.
    NUMBER(2,2) : 1 미만의 소수점 2자리 실수는 3,2 아님. 0은 제외.   
*/

/*
테이블 생성
1. 제약조건의 이름을 지정하지 않는 방법이 있고 (SYS로 시작하는 임의의 제약조건이름이 지정)-이름을 안줘도 이름이 생김 
2. 제약 조건의 이름을 지정하는 방법이 있다 

*/


/*
제약조건 생성
1. 테이블 생성할 때 함께 지정하는 방법 
2. 테이블 생성한 뒤 테이블 수정하면서 지정
*/

/*  
제약조건 - 데이터 사전 - 다 테이블 이름임. 이미 만들어진 테이블
1. DBA_CONSTRAINTS
2. USER_CONSTRAINTS
3. ALL_CONSTRAINTS
 


USER_CONSTRAINTS USER가 만든 제약조건 확인 가능
*/

-- USER_CONSTRAINTS 테이블의 구조 확인 
DESCRIBE SYS.USER_CONSTRAINTS;           --실행하고 싶은 구문 블락해서 컨트롤+엔터하기

--USER_CONSTRAINTS 테이블의 CONSTRAINT_NAME 칼럼 확인
SELECT CONSTRAINT_NAME FROM USER_CONSTRAINTS; -- 현재 SOCTT계정의 제약조건을 봄    // 여러번 실행 가능



-- 1. 제약조건 이름 없이 테이블 만들기 
DROP TABLE USER_TBL;        -- 드랍이랑 크리에이트 만들면 언제나 새로 만듦. 이 줄 없으면 만든 상태에서 또 만드는 건 오류남
CREATE TABLE USER_TBL(
   USER_ID VARCHAR2(30 BYTE) NOT NULL PRIMARY KEY,  -- 2개의 제약조건 설정  -- PK는 테이블 대상으로 줌
    USER_PW VARCHAR2(30 BYTE) NOT NULL, 
    USER_NAME VARCHAR2(30 BYTE) NULL, -- NULL 가능하다는 뜻
    USER_AGE NUMBER(3) NULL CHECK(USER_AGE BETWEEN 0 AND 100), -- 나이는 0(포함)에서 100사이
    USER_ADDR VARCHAR2(30 BYTE) NULL, -- 주소 
    USER_TELL CHAR(13 BYTE) NULL UNIQUE,
    USER_GEN CHAR(1 BYTE) NULL  -- NOT NULL 유니크가.. 이름없이.. 뭔소리지 .. 모든 제약 조건들은 저장이 되어있다 //제약조건의 이름 추천
                                        -- PRIMARY KEY에 UNIQUE 특성이 있어서 NULL이랑 PRIMARY KEY만 적어줌(?)
                                        
  
                       
                                             
);

-- 2. 제약조건이름 없이 테이블 만들기
DROP TABLE USER_TBL;
CREATE TABLE USER_TBL(
    USER_ID VARCHAR2(30 BYTE) NOT NULL,
    USER_PW VARCHAR2(30 BYTE) NOT NULL,
    USER_NAME VARCHAR2(30 BYTE) NULL,
    USER_AGE NUMBER(3) NULL,
    USER_ADDR VARCHAR2(30 BYTE) NULL,
    USER_TEL CHAR(13 BYTE) NULL,
    USER_GEN CHAR(1 BYTE) NULL,
    PRIMARY KEY(USER_ID),                      --NOT NULL을 제외하고 테이블 아래로 뺄 수 있음. 다음 줄 참고.
    CHECK(USER_AGE BETWEEN 0 AND 100),          --NULL 유무를 제외하고 한곳에 적는 방법임
    UNIQUE(USER_TEL)
);



-- 3. 제약조건이름 지정하며 테이블 만들기
DROP TABLE USER_TBL;
CREATE TABLE USER_TBL(
    USER_ID VARCHAR2(30 BYTE) NOT NULL CONSTRAINT PK_USER_TBL PRIMARY KEY,-- 2개의 제약조건 설정  -- PK는 테이블 대상으로 줌
    USER_PW VARCHAR2(30 BYTE) NOT NULL,
    USER_NAME VARCHAR2(30 BYTE) NULL,
    USER_AGE NUMBER(3) NULL CONSTRAINT CK_USER_AGE CHECK(USER_AGE BETWEEN 0 AND 100),
    USER_ADDR VARCHAR2(30 BYTE) NULL,
    USER_TEL CHAR(13 BYTE) NULL CONSTRAINT UQ_USER_TEL UNIQUE,
    USER_GEN CHAR(1 BYTE) NULL
);



-- 4. 제약조건이름 지정하며 테이블 만들기 
DROP TABLE USER_TBL;        -- 드랍이랑 크리에이트 만들면 언제나 새로 만듦. 이 줄 없으면 만든 상태에서 또 만드는 건 오류남
CREATE TABLE USER_TBL(
    USER_ID VARCHAR2(30 BYTE) NOT NULL ,  
    USER_PW VARCHAR2(30 BYTE) NOT NULL, 
    USER_NAME VARCHAR2(30 BYTE) NULL, -- NULL 가능하다는 뜻
    USER_AGE NUMBER(3) NULL,  -- 나이는 0(포함)에서 100사이
    
    USER_ADDR VARCHAR2(30 BYTE) NULL, -- 주소 
    USER_TELL CHAR(13 BYTE) NULL,
    USER_GEN CHAR(1 BYTE) NULL,  -- NOT NULL 유니크가.. 이름없이.. 뭔소리지 .. 모든 제약 조건들은 저장이 되어있다 //제약조건의 이름 추천
                                        -- PRIMARY KEY에 UNIQUE 특성이 있어서 NULL이랑 PRIMARY KEY만 적어줌(?)
                                        --NOT NULL을 제외하고 테이블 아래로 뺄 수 있음. 다음 줄 참고.
  
                       --NULL 유무를 제외하고 한곳에 적는 방법임
   CONSTRAINT PK_USER_TBL PRIMARY KEY(USER_ID),

);