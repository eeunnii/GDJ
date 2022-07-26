INSERT INTO SLEEP_USERS (USER_NO, ID, PW, NAME, GENDER, EMAIL, MOBILE, BIRTHYEAR, BIRTHDAY, POSTCODE, ROAD_ADDRESS, JIBUN_ADDRESS, DETAIL_ADDRESS, EXTRA_ADDRESS, AGREE_CODE, SNS_TYPE, JOIN_DATE, LAST_LOGIN_DATE)
(SELECT U.USER_NO, U.ID, U.PW, U.NAME, U.GENDER, U.EMAIL, U.MOBILE, U.BIRTHYEAR, U.BIRTHDAY, U.POSTCODE, U.ROAD_ADDRESS, U.JIBUN_ADDRESS, U.DETAIL_ADDRESS, U.EXTRA_ADDRESS, U.AGREE_CODE, U.SNS_TYPE, U.JOIN_DATE, A.LAST_LOGIN_DATE
FROM USERS U LEFT OUTER JOIN ACCESS_LOG A
ON U.ID = A.ID
WHERE MONTHS_BETWEEN(SYSDATE, A.LAST_LOGIN_DATE) >= 12 --접속기록이 있는 회원
OR (MONTHS_BETWEEN(SYSDATE, U.JOIN_DATE) >= 12 AND A.LAST_LOGIN_DATE IS NULL)) -- 접속기록이 없는 회원)



SELECT U.USER_NO, U.ID, U.PW, U.NAME, U.GENDER, U.EMAIL, U.MOBILE, U.BIRTHYEAR, U.BIRTHDAY, U.POSTCODE, U.ROAD_ADDRESS, U.JIBUN_ADDRESS, U.DETAIL_ADDRESS, U.EXTRA_ADDRESS, U.AGREE_CODE, U.SNS_TYPE, U.JOIN_DATE, U.PW_MODIFY_DATE, U.INFO_MODIFY_DATE, U.SESSION_ID, U.SESSION_LIMIT_DATE, A.LAST_LOGIN_DATE
FROM USERS U LEFT OUTER JOIN ACCESS_LOG A
ON U.ID = A.ID
WHERE MONTHS_BETWEEN(SYSDATE, A.LAST_LOGIN_DATE) >= 12 --접속기록이 있는 회원
OR (MONTHS_BETWEEN(SYSDATE, U.JOIN_DATE) >= 12 AND A.LAST_LOGIN_DATE IS NULL) -- 접속기록이 없는 회원


--select 회원.칼럼 , 접속기록.칼럽
--form 회원 left outer join 접속기록
--on 회원.아이디 = 접속기록.아이디 
--where months_between(sysdate, 접속기록,last_login_date) >= 12 --접속기록이 있는 회원
--or months_between(sysdate, 회원.join_date) >= 12 and 접속기록_last_login_date is null) -- 접속기록이 없는 회원

DELETE 
FROM USERS
WHERE ID IN (
SELECT U.ID
FROM U.ID = A.ID
WHERE MONTHS_BETWEEN(SYSDATE, A.LAST_LOGIN_DATE) >= 12 --접속기록이 있는 회원
OR (MONTHS_BETWEEN(SYSDATE, U.JOIN_DATE) >= 12 AND A.LAST_LOGIN_DATE IS NULL))




