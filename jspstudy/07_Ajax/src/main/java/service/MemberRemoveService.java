package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import repository.MemberDao;

public class MemberRemoveService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(request.getParameter(" memberNo"));
		
		try {
			// 요청 파라미터 
			Optional<String> opt= Optional.ofNullable(request.getParameter("memberNo"));
			int memberNo = Integer.parseInt(opt.orElse("0"));  // 파라미터로 "백"들어오면 유류남
			
			System.out.println(memberNo);
			
			// 삭제 
			int result = MemberDao.getInstance().deleteMember(memberNo);
			
			// 응답 데이터 형식 : JSON
			response.setContentType("application/json");
			
			// 응답 데이터 
			/*
			 * 	성공 응답
			 * {isSuccess" :true}
			 * 
			 * 실패 응답
			 * {isSuccess : false}
			 */
			JSONObject obj = new JSONObject();
			obj.put("isSuccess", result>0);
			
			// 응답
			PrintWriter out = response.getWriter();
			out.println(obj.toString());
			out.close();
			
			
		}catch(Exception e) {
			// ㅇㅖ외 응답 데이터 형식, 일반텍스트
			response.setContentType("text/plain; chaerset=UTF-8");
			
			// 응답
			PrintWriter out = response.getWriter();
			out.println("잘못된 회원번호가 전달되었습니다");
			out.close();
			
		}	

	}

}
