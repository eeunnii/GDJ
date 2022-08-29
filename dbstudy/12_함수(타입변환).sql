DESC DUAL;      -- 테이블 구조를 확인하는 명령 
SELECT 1+2          
    FROM DUAL;         --테이블이름까지 있어야함 
    
    -- DUAL 테이블에는 DUMMY라는 ,.뭐가 있다 
    
    
    
    /*
    DUAL 테이블
    1. DUMMY 칼럼에 'X' 값을 하나 가지고 있는 테이블
    2. 아무 의미 없는 테이블
    3. 오라클에서는 FROM절이 필수 이기 때문에,
    테이블이 필요 없는 조회문에서 DUAL 테이블을 사용함
    */
    
    
    /*
    타입 변환 함수 
    1. TO_NUMBER( '문자열' )         : 문자열 형식의 숫자를 숫자 형식으로 변환
    2. TO_CHAR( 데이터, ['형식'] )     : 지정된 데이터를(주로 숫자나 날짜)를 형식에 맞는 문자열로 변환
    3. TO_DATE( '문자열','형식' )    : 지정된 문자열을 날짜 형식으로 변환
    */
    
    -- ▶1. 숫자로 변환(TO_NUMBER)◀
    SELECT '100', TO NUMBER('100')
     FROM DUAL;
    SELECT '1.5', TO NUMBER('1.5')
     FROM DUAL;
     
    -- 숫자와 '문자' 연산은 오라클에 의해 
    -- 숫자와 숫자 연산으로 수정된 뒤 처리됨.
    -- '문자' -> TO_NUMBER('문자') 방식으로 자동으로 처리됨.
    SELECT 1+'1'    -- SELECT 1+ TO_NUMBER('1')  -- 오라클이 자동으로 처리 
    FROM DUAL;
    
    --'문자'와 '문자' 연산도 모두 숫자로 바꿔서 처리 
    SELECT '1'+'1'  -- TO_NUMBER('1') + TO_NUMBER('1')
     FROM DUAL;
     
    SELECT *
    FROM EMPLOYEE
    WHERE EMP_NO = '1001';    -- =은 연산자임. 여기서 TO NUMBER 개입됨
    
    
    /*
    TO_NUMBER(EMP_NO) = 1
    EMP_NO = 1
    EMP_NO = '1'
    EMP_NO = TO_CHAR(1)
    
    연산자 왼쪽은 가급적 함수가 안들어가는게 좋음 
    */
    
    
     -- ▶2. 문자로 변환(TO_CHAR)◀
     -- ① 숫자 -> 문자로 변환
    SELECT  
            TO_CHAR(1234)              -- '1234'
          , TO_CHAR(1234,  '99999')    -- 숫자 부족하면 공백으로 채움 -- '  1234'
          , TO_CHAR(1234,  '00000')     -- 숫자 부족하면 '0' 문자로 채움 -- '001234'
          , TO_CHAR(1234,  '9,999')    -- 천단위 구분기호를 붙여달라는 뜻 -- '1,234;
          , TO_CHAR(12345, '9,999')    -- 출력해보기. 자리수가 부족해서. -- #####   -- 4자리로 지정하엿으나 5자리여서 표기할 수 없음
          , TO_CHAR(12345, '99,999')   -- '12,245'
          , TO_CHAR(1.4,   '9')        -- 값 : 1 // 정수 한자리 표기. (소수 이하 반올림)
          , TO_CHAR(1.5,   '9')        -- 값 : 2 // 반올림
          , TO_CHAR(1.123, '0.00')     -- '0.12', 소수 이하 2자리 표기     
          , TO_CHAR(1.129, '0.00')     --'0.13' , 소수 이하 반올림 표기
        FROM
            DUAL; 
    
    -- ② 날짜 -> 문자로 변환
    -- 현재 날짜와 시간
    -- DATE 타입의 SYSDATE 
    -- YSTIMESTAMP 타입의 SYSTIMESTAMP
    SELECT 
          SYSDATE       --YY/MM/DD 형식으로 표시하지만 시간 데이터도 가지고 있음
        , SYSTIMESTAMP
      FROM DUAL;        --우리나라는 세계 표시기준에서 +09:00
      
    SELECT
        TO_CHAR(SYSDATE, 'YYYY-MM-DD')
      , TO_CHAR(SYSDATE, 'HH:MI:SS')
      FROM
        DUAL;
      
      -- ③ 날짜로 변환(TO DATE)
      -- '05/06/07' 날짜는 언제인가? 알려주기 전에는 모름 
      -- 지정된 형식으로 해석
      -- 예시) 'YY/MM/DD' : 05년 06월 07일
      -- 예시) 'MM/DD/YY' : 07년 6월 5일
      -- 어떤 날짜를 어떻게 해석해야 하는지 알려주는 함수
      SELECT --(★)
            TO DATE('05/06/07', 'YY/MM/DD')
            TO DATE('05/06/07', 'MM/DD/YY')
        FROM 
            DUAL;
            
    -- 현재 날짜를 YYYY-MM-DD 형식으로 조회
    
    SELECT 
       -- TO DATE(SYSDATE, 'YYYY/MM/DD') FROM DUAL;  --SYSDATE 날짜를 DATE 날짜로 바꿈. 결론 안됨
         TO CHAR(SYSDATE, 'YYYY/MM/DD') FROM DUAL;
         
    --사원 테이블에서 90/01/01~99/12/31 사이에 입사한 사원 조회하기
    
    SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
         FROM EMPLOYEE
         WHERE TO DATE(HIRE_DATE, 'YY/MM/DD') BETWEEM TO_DATE('90/01/01','99/12/31') AND TO_DATE('31/12/99', 'DD/MM/YY');
         --실무가면 날짜를 VARCHAR2 타입으로 저장함. 이유는 훨씬 빨라서. 
         
         CREATE TABLE SAMPLE(
            DT1 DATE,
            DT2 TIMESTAMP,
            DT3 VARCHAR2(10 BYTE)  -- YYYY-MM-DD 으로 저장됨
        );
        
        --DT1 DT1 칼럼에 현재 날짜 넣기 
        INSERT INTO SAMPLE(DT1, DT2, DT3) VALUES(SYSDATE, SYSTIMESTAMP, TO_CHAR(SYSDATE, 'YYYY-MM-DD'));
        
        SELECT DT1, DT2, DT3 FROM SAMPLE; 
        
        SELECT DT1, DT2, DT3
        FROM SAMPLE
        WHERE DT1 = TO_DATE('22/05/26', 'YY/MM/DD'); --★ 중요한데 이해못함.. 몽소리야
         
   
    