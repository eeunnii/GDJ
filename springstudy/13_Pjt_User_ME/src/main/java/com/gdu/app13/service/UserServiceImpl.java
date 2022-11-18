package com.gdu.app13.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.app13.domain.RetireUserDTO;
import com.gdu.app13.domain.SleepUserDTO;
import com.gdu.app13.domain.UserDTO;
import com.gdu.app13.mapper.UserMapper;
import com.gdu.app13.util.SecurityUtil;

@PropertySource(value = {"classpath:email.properties"})
@Service
public class UserServiceImpl implements UserService {

	// 이메일을 보내는 사용자 정보
	@Value(value = "${mail.username}")
	private String username;  // 본인 지메일 주소
	
	@Value(value="${mail.password}")
	private String password;  // 발급 받은 앱 비밀번호
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private SecurityUtil securityUtil;
	
	@Override
	public Map<String, Object> isReduceId(String id) {
		
		// 조회조건으로 사용할 Map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id",id);
		
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isUser", userMapper.selectUserByMap(map) != null);
		result.put("isRetireUser", userMapper.selectRetireUserById(id) != null);
		return result;
	}
	
	@Override
	public Map<String, Object> isReduceEmail(String email) {
		
		// 조회 조건으로 사용할 Map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isUser", userMapper.selectUserByMap(map) != null);
		return result;
	}
	
	@Override
	public Map<String, Object> sendAuthCode(String email) {
		
		/*
		이메일 보내기 API 사용을 위한 사전 작업
		
		1. 구글 로그인
		2. [Google 계정] - [보안]
		    1) [2단계 인증]  - [사용]
		    2) [앱 비밀번호]
		        (1) 앱 선택   : 기타
		        (2) 기기 선택 : Windows 컴퓨터
		        (3) 생성 버튼 : 16자리 앱 비밀번호를 생성해 줌(이 비밀번호를 이메일 보낼 때 사용)
		 */
			
		// 인증코드 만들기
		String authCode = securityUtil.getAuthCode(6);  // String authCode = securityUtil.generateRandomString(6);
		System.out.println("발송된 인증코드 : " + authCode);
		
		// 이메일 전송을 위한 필수 속성을 Properties 객체로 생성
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");  // 구글 메일로 보냄(보내는 메일은 구글 메일만 가능)
		properties.put("mail.smtp.port", "587");             // 구글 메일로 보내는 포트 번호
		properties.put("mail.smtp.auth", "true");            // 인증된 메일
		properties.put("mail.smtp.starttls.enable", "true"); // TLS 허용
		

		
		// 사용자 정보를 javax.mail.Session에 저장
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		// 이메일 작성 및 전송
		try {
			
			Message message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(username, "인증코드관리자"));            // 보내는사람
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));  // 받는사람
			message.setSubject("[Application] 인증 요청 메일입니다.");                   // 제목
			message.setContent("인증번호는 <strong>" + authCode + "</strong>입니다.", "text/html; charset=UTF-8");  // 내용
			
			Transport.send(message);  // 이메일 전송
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// join.jsp로 반환할 데이터
		// 생성한 인증코드를 보내줘야 함
		// 그래야 사용자가 입력한 인증코드와 비교를 할 수 있음
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("authCode", authCode);
		return result;
		
	}
	
	@Transactional  //insert, update, delete중 2개 이상 호출되는 서비스에서 필요함 -- 8장 참고,.
	@Override
	public void join(HttpServletRequest request, HttpServletResponse response) {
		// 파라미터 
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String mobile = request.getParameter("mobile");
		String birthyear = request.getParameter("birthyear");
		String birthmonth = request.getParameter("birthmonth");
		String birthdate = request.getParameter("birthdate");
		String postcode = request.getParameter("postcode");
		String roadAddress = request.getParameter("roadAddress");
		String jibunAddress = request.getParameter("jibunAddress");
		String detailAddress = request.getParameter("detailAddress");
		String extraAddress = request.getParameter("extraAddress");
		String email = request.getParameter("email");
		String location = request.getParameter("location");
		String promotion = request.getParameter("promotion");
		
		
		
		// 일부 파라미터는 DB에 넣을 수 있도록 가공
		pw = securityUtil.sha256(pw);
		name = securityUtil.preventXSS(name); // 이거 뭐더라 
		//birthmonth +birthday 하나로 합쳐서 재구성해야함
		String birthday = birthmonth + birthdate;
		detailAddress = securityUtil.preventXSS(extraAddress);
		int agreeCode = 0;  // 필수 동의
		if(location != null && promotion == null) {  //location != null로 하면 안됨 name잇으면? // null이라는 건 안온다는 이야기고, name속성이 들어가있는데 값 없이 전달되면 name만 전달
			// null -- 택배가 갔는데 내용물이 없다. 빈문자열 -- 택배가 안갔다
			agreeCode = 1;  // 필수 + 위치
		} else if(location == null && promotion != null) {
			agreeCode = 2;  // 필수 + 프로모션
		} else if(location != null && promotion != null) {
			agreeCode = 3;  // 필수 + 위치 + 프로모션
		}
		
		
		//db로 보낼 userDTOㅠ만들기
		UserDTO user = UserDTO.builder()
				.id(id)
				.pw(pw)
				.name(name)
				.gender(gender)
				.email(email)
				.mobile(mobile)
				.birthyear(birthyear)
				.birthday(birthday)
				.postcode(postcode)
				.roadAddress(roadAddress)
				.jibunAddress(jibunAddress)
				.detailAddress(detailAddress)
				.extraAddress(extraAddress)
				.agreeCode(agreeCode)
				.build();						
	
		// 회원가입처리 
		int result = userMapper.insertUser(user);
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(result>0) {
				
				// 로그인 처리를 위해서 session에 로그인 된 사용자 정보를 올려둠
				// 모든 페이지 위에 session이 있어서 어떤 페이지든 session에 있는걸 꺼내서 쓸 수 있음
				// 모든 jsp위에는 session이 있다. ${} 속성이름 그대로 써서 사용할 수 있음
				request.getSession().setAttribute("loginUser", userMapper.selectUserById(id));
				
				// 로그인 기록 남기기 
				int updateResult = userMapper.updateAccessLog(id);
				if(updateResult == 0) {
					userMapper.insertAccessLog(id); // 이것만 남아도 된다는게 무슨뜻이지 
				}
				
				out.println("<script>");
				out.println("alert('회원가입되었습니다!');");
				out.println("location.href='"+request.getContextPath()+"';");
				out.println("</script>");
			}else {
				out.println("<script>");
				out.println("alert('회원가입에 실패했습니다.');");
				out.println("history.go(-2);");   // 2칸 뒤로 가라!
				out.println("</script>");
			}
			
			out.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	@Transactional  //insert, update, delete중 2개 이상 호출되는 서비스에서 필요함
	@Override
	public void retire(HttpServletRequest request, HttpServletResponse response) {
		
		// 탈퇴한 회원의 userNo, id, joinDate는 session의 loginUser에 저장되어 있음
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
		
		// 탈퇴할 회원 RetireUserDTO 생성
		RetireUserDTO retireUser = RetireUserDTO.builder()
												.userNo(loginUser.getUserNo())
												.id(loginUser.getId())
												.joinDate(loginUser.getJoinDate())
												.build();
				//탈퇴처리									
		int deleteResult = userMapper.deleteUser(loginUser.getUserNo());
		int insertResult = userMapper.insertRetireUser(retireUser);
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(deleteResult>0 && insertResult>0) {
				
				// session은 서버 메모리상의 정보임.
				
				// loginUser 삭제를 위해서 session에 올라가있던 로그인 정보를 지워줘야함 = 세션초기화
				session.invalidate();
				
				out.println("<script>");
				out.println("alert('회원탈퇴되었습니다!');");
				out.println("location.href='"+request.getContextPath()+"';");
				out.println("</script>");
			}else {
				out.println("<script>");
				out.println("alert('회원탈퇴에 실패했습니다.');");
				out.println("history.back();");   // 2칸 뒤로 가라!
				out.println("</script>");
			}
			
			out.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	@Override
	public void login(HttpServletRequest request, HttpServletResponse response) {
		
		// 로그인 유지 처리는 keeplogin 메소드가 따로 처리함 
		
		// 로그아웃을 누르면 로그인 유지가 풀려야함
		keeplogin(request, response);
		
		// 파라미터
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String url = request.getParameter("url");
		
		// pw는 DB에 저장된 데이터와 동일한 형태로 가공한다 
		pw = securityUtil.sha256(pw);
		
		//DB로 보낼 UserDTO
		UserDTO user = UserDTO.builder()
								.id(id)
								.pw(pw)
								.build();
		// id, pw가 일치하는 회원을 DB에서 조회하기 
		UserDTO loginUser = userMapper.selectUserByIdPw(user);
		
		// id, pw가 일치하는 회원이 있다 : 로그인 기록 남기기 + session에 loginUser 저장
		if(loginUser
				!= null) {
			// 로그인 기록 남기기
			int updateResult = userMapper.updateAccessLog(id);
			if(updateResult == 0) {
				userMapper.insertAccessLog(id); // 이것만 남아도 된다는게 무슨뜻이지 
			}
			request.getSession().setAttribute("loginUser", userMapper.selectUserByMap(id));
			//이동 (로그인페이지 이전 페이지로 되돌아가기)
			
			try {
				response.sendRedirect(url);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}else {
		// id, pw가 일치하는 회워이 없다 : 로그인 페이지로 돌려보내기
			
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
					out.println("<script>");
					out.println("alert('일치하는 회원 정보가 없다.!');");
					out.println("location.href='"+request.getContextPath()+"';");  // index.jsp 로감
					out.println("</script>");
				
				out.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}	
			
		}
		
		
		
	}
	

	
	
	
	
	
	
	
	@Override
	public void keeplogin(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * 로그인 유지를 체크한 경우 
		 * 
		 * 1. Session_id를 쿠키에 저장해 둔다,
		 * 		(쿠키명 : keepLogin)
		 * 2. session_id를 DB에 저장해 둔다.
		 * 		(SESSION_ID 칼럼에 session_id를 저장하고, SESSION_LIMIT_DATE 칼럼에 현시점부터 15일 후 날짜를 보관한다. == 15일까지는 자동 로그인이 돌아간다. 
		 */
		
		/*
		 * 로그인 유지를 체크하지 않은 경우 
		 * (쿠키랑 db 양쪽에 데이터가 저장되어 있으니 한 쪽의 데이터만 지워줘도 된다,)
		 * 
		 * 1. 쿠키 또는 DB에 저장된 정보를 삭제한다.
		 * 	편의상 쿠키명 keepLogin을 제거하는 것으로 처리한다.
		 * 
		 * 
		 */
		
		//파라미터
		String id = request.getParameter("id");
		String keepLogin =request.getParameter("keepLogin"); 
		
		
		// 체크박스에 name이 있어도 체크 안하고 보내면 파라미터 전달도 안감
		
		//로그인 유지를 체크한 경우 
		if(keepLogin !=null) {
			
			// session_id
			String sessionId = request.getSession().getId();
			
			// session_id를 쿠키에 저장하기 
			Cookie cookie = new Cookie("keepLogin", request.getSession().getId());
			cookie.setMaxAge(60 * 60 * 24 * 15); // 15일 
			cookie.setPath(request.getContextPath()); // 쿠키의 적용 범위를 설정함
			response.addCookie(cookie);
			
			// session_id를 DB에 저장하기
			UserDTO user = UserDTO.builder()
						.id(id)
						.sessionId(sessionId)
						.sessionLimitDate(new Date(System.currentTimeMillis()+1000*60*60*24*15)) // long타입의 데이터 :  timestamp 1/1000 // 현재 timestamp구하고, +15일 계산 값 구하기 
						// 60*60*24*15 여기까지만 하면 "초"단위, *1000 해주면 밀리초 단위됨
						.build();
			
			userMapper.updateSessionInfo(user);
			
		}
		//로그인 유지를 체크하지 않은 경우 
		else {
			Cookie cookie = new Cookie("keepLogin", "");
			cookie.setMaxAge(0); // 쿠키 유지 시간 0이면 삭제를 의미함
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
		}
		
		
		
		
	}
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		
		//코드 리펙토링... 

		// 로그아웃 처리 -- 세션 초기화
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser")!=null) {
			session.invalidate();
		}
		//로그아웃할 때 session만 날리는게 아니고 자동로그인도 풀어야함
		
		//로그인 유지 풀기 
		//(쿠키는 클라이언트에 있음-- 쿠키는 하나만 골라서 가지고 오는게 안됨. 배열의 형태로 죄다 가져와서 확인해야함)
		Cookie[] cookieList = request.getCookies();
		
		if(cookieList==null) {
			return;
		}
		Cookie cookie = null;
		for(int i = 0; i<cookieList.length; i++) {
			if(cookieList[i].getName().equals("keeplogin")) {
				cookie = new Cookie("keepLogin", "");
				cookie.setMaxAge(0); // 쿠키 유지 시간 0이면 삭제를 의미함
				cookie.setPath(request.getContextPath()); // 쿠키 저장하는 장소를 contextPath로 바꿈. 원래는 .. request에 들어있음
				break;
				
				// 그냥 제거하는게 아니라 확인해서 있으면 제거. 하는 기능이 더 좋음
				// 여기에선 확인하는거 생략함 
				 
				// session에서 확인, cookie에서 확인
				
				// session_id는 유니크 값임 
			}
		}
		response.addCookie(cookie);
		
		// 자동로그인하면 session_id가 생긴다. 
		// 모든 매핑에 접속할 때 (디비=쿠키)를 비교해서 로그인되도록 처리해야함
		// 포인트는 모든 매핑임 ex네이버 접속할 때 꼭 메인으로 들어가는 게 아니듯이 
		
		// 모든 매핑이 실행되기 전에 intersepter가 실행되도록 구현한다. 
		// intersepter는 ture, false를 반환함. true는 인터셉터 끝나고 그다음 코드를 처리함 false는 자기가 실행을 막아버림
		// intersepter는 스스로 개입함 
		
		
		
	}
	
	
	
	@Override
	public UserDTO getUserBySessionId(Map<String, Object> map) {
		
		return userMapper.selectUserByMap(map);
	}
	
	
	
	@Override
	public Map<String, Object> confirmPassWord(HttpServletRequest request) {

		// 파라미터 pw + SHA-256 처리
		String pw = securityUtil.sha256(request.getParameter("pw"));
		
		// id
		HttpSession session = request.getSession();
		String id = ((UserDTO)(session.getAttribute("loginUser"))).getId(); // 세션에 올라가 잇는 User에서 id 빼옴
		
		// 조회 조건으로 사용할 Map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pw", pw);
		
		// id와 pw 일치하는 회원 조회 
		UserDTO user = userMapper.selectUserByMap(map);
		
		// 결과 반환
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("isUser", user !=null);   // user가 맞으면 true, 아니면 false반환함
		
		return result;
	}
	
	
		@Override
		public int modifyPassword(HttpServletRequest request, HttpServletResponse response) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		
		
		@Transactional
		@Override
		public void sleepUserHandle() {
			int insertCount = userMapper.insertSleepUser();
			if(insertCount>0) {
				userMapper.deleteSleepForUser();
			}
			
		}
		
		
		@Override
		public SleepUserDTO getSleepUserById(String id) {
			return userMapper.selectSleepUserById(id);
		}
		
		
		@Transactional
		@Override
		public void restoreUser(HttpServletRequest request, HttpServletResponse response) {
			// 계정 복원을 위해서 사용자가 입력한 비밀번호
			String pw = securityUtil.sha256(request.getParameter("pw"));
			
			// 계정 복원을 원하는 사용자의 아이디 (request의 파라미터에 없음. session에 들어있으니까 jsp에서 파라미터 전송 안해도됨)
			HttpSession session = request.getSession();
			SleepUserDTO sleepUser = (SleepUserDTO)session.getAttribute("sleepUser");
			String id = sleepUser.getId();
			
			// 조회 조건으로 사용할 Map
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("pw", pw);
			
			// pw가 일치하는 정보 확인
			if(pw.equals(sleepUser.getPw())) {
				
				
				int insertCount = userMapper.insertres(id);
				int deleteCount = userMapper.ddeleteSleepUser(id);
				if(insertCount>0) {
					userMapper.ddeleteSleepUser(id);
				}
				
				
				try {
					// 응답
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					if(insertCount> 0 && deleteCount>0) {
						out.println("<script>");
						out.println("alert('휴면 계정이 복구되었습니다. 휴면 계정 활성화를 위해 곧바로 로그인을 해 주세요');");
						out.println("location.href='" + request.getContextPath() + "/users/login/form';");
						out.println("</script>");
					
					}else {
						out.println("<script>");
						out.println("alert('휴면 계정이 복구되지않았습니다.');");
						out.println("history.back();");
						out.println("</script>");
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			// 1분마다 스케줄러를 돌려서 빼갈꺼임.. 복붙한 데이터가 로그인을 안하면 스케줄러가 돌면서 다시 휴먼으로 빠짐
		}
		
	
		

}

		
		
		@Override
		public String getNaverLoginApiURL(HttpServletRequest request) {
			
			// 네이버 개발자 센터에서 가져온 코드
			
			String apiURL = null;
			try {
				String clientId = "_48qz3Z7EcTkgD6lEtVx";//애플리케이션 클라이언트 아이디값";
				String redirectURI = URLEncoder.encode("http://localhost:9090/"+request.getContextPath()+"/user/naver/login", "UTF-8");
				SecureRandom random = new SecureRandom();
				String state = new BigInteger(130, random).toString();
				apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
				apiURL += "&client_id=" + clientId;
				apiURL += "&redirect_uri=" + redirectURI;
				apiURL += "&state=" + state;
				HttpSession session = request.getSession();
				session.setAttribute("state", state);
			}catch(Exception e) {
				e.printStackTrace();
			}
			return apiURL;
			
			

		}
		
		
		@Override
		public UserDTO getNaverLoginTokenNProfile(HttpServletRequest request) {

			// access_token받기 
		    String clientId = "_48qz3Z7EcTkgD6lEtVx";//애플리케이션 클라이언트 아이디값";
		    String clientSecret = "_fp9SsfDX0";//애플리케이션 클라이언트 시크릿값";
		    String code = request.getParameter("code");
		    String state = request.getParameter("state");
		    String redirectURI = null;
		    
		    
		    
		    
		    
		    
		    try {
		    	redirectURI = URLEncoder.encode("YOUR_CALLBACK_URL", "UTF-8");   // 네이버 로그인할거니가 필요한 정보 여기로 주십시오 
		    	String apiURL;
		    	apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&"; //옛다 주소 
		    	apiURL += "client_id=" + clientId;
		    	apiURL += "&client_secret=" + clientSecret;
		    	apiURL += "&redirect_uri=" + redirectURI;
		    	apiURL += "&code=" + code;
		    	apiURL += "&state=" + state;
		    	
		    	//access_token
		    	
		    }catch(UnsupportedEncodingException e) {
		    	e.printStackTrace();
		    }
		    System.out.println("apiURL="+apiURL);
		    try {
		      URL url = new URL(apiURL);
		      HttpURLConnection con = (HttpURLConnection)url.openConnection();
		      con.setRequestMethod("GET");
		      int responseCode = con.getResponseCode();
		      BufferedReader br;
		      System.out.print("responseCode="+responseCode);
		      if(responseCode==200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		      }
		      String inputLine;
		     
		      while (((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();
		      con.System.out.println(res.toString());
		      
		      /*
		       * 
		       * 	res.toString()
		       * {
		       * 	"access_token  // 
		       * 
		       */
		      
		      
		      
		      
		      
		      
		      
		      
		      
		      JSONObject obj = new JSONObject(res.toString());  // 꺼낼 때 .. json이 필요해서 라이브러리 추가했음
		      obj.getString("access_token");
		      refresh_token = obj.getString("refresh_token");
		    		  
		      
		      
		      
		      
		      
		      
		      
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
			
		    // 받아온 profile을 UserDTO로 만들어서 반환
		    
		    
		    
			
			return null;
		}
}