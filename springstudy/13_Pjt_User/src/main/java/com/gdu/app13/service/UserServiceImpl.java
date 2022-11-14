package com.gdu.app13.service;

import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.gdu.app13.mapper.UserMapper;
import com.gdu.app13.util.SecurityUtil;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	
	private UserMapper userMapper;
	private SecurityUtil securityUtil;
	
	
	@Override
	public Map<String, Object> isReduceId(String id) {
		Map<String, Object> result =new HashMap<String, Object>();
		result.put("isUser", userMapper.selectUserById(id)!=null);  // isUser가 true이면 회원이고 flase이면 회원이 아님
		result.put("isRetireUser", userMapper.selectRetireUserById(id)!=null);
		// 조회되었으면 ture
		return null;
	}
	
	@Override
	public Map<String, Object> isReduceEmail(String email) {

		return null;
	}
	
	@Override
	public Map<String, Object> sendAuthCode(String email) {

		//인증코드만들기 (2가지 방법중 뭘쓰던 상관없음)
		String authCode = securityUtil.getAuthCode(6);   // 우리가 수동으로 만들거
		//String authCode = securityUtil.generateRandomString(6);  // depend가 해결해줌
		
		System.out.println("발송된 인증코드 :" + authCode);
		
		//이메일 전송을 위한 실수 속성을 Properties객체로 생성
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.con"); // 구굴메일로 보냄(보내는 메일은구글메일만 가능)
		properties.put("mail.smtp.port","587"); // 구글 메일로 보내는 포트 번호 
		properties.put("mail.smtp.auth", "true");    // 인증된 메일
		properties.put("mail.smtp.starttls.emable", "true");  // TLS 허용
		
		/*
		 * 이메일 보내기 api사용을 위한 사전 작업
		 * 
		 * 1. 구글 로그인
		 * 2. Google 계정 - 보안
		 * 		1) 2단계 인증 - 사용
		 * 		2) 앱 비밀번호 
		 * 			(1) 앱 선택 : 기타
		 * 			(2) 기기 선택 : Windows 컴퓨터 
		 * 			(3) 생성 버튼 : 16자리 앱 비밀번호를 생성해 줌(이 비미번호를 이메일 보낼 때 사용)
		 * 
		 * 
		 */
		
		// 이메일을 보내는 사용자 정보 
		String username = ""; // 본인 지메일 주소
		String password="";  //발급받은 앱 비밀번호
		
		// 사용자 정보를 javax.mail.Session에 저장함
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getpasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		
		});
		
		// 이메일 작성 및 종료 
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username, "인증코드관리자")); // 보내는사람
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));  //받는사람// s가 안들어가는걸로. 한명한테만 보낼꺼니까 //
			message.setSubject("[Application]인증 요청 메일입니다");
			message.setContent("인증번호<Strong>"+authCode+"</strong>", "text/html; charset=UTF-8"); // 본문
			
			Transport.send(message); // 이메일 전송
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// join.jsp로 반환할 데이터 (생성한 인증코드를 보내줘야함
		// 그래야 사용자가 입력한 인증코드와 비교할 수 있음
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("authCode", authCode);
		return result;

	}

}
