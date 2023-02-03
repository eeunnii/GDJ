package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Member;
import repository.MemberDao;

public class MemberAddService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		//요청 파라미터 
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String grade = request.getParameter("grade");
		String address = request.getParameter("address");
		
		// 삽입할 Member member 생성
		Member member = Member.builder()
				.id(id)
				.name(name)
				.gender(gender)
				.grade(grade)
				.address(address)
				.build();
		
		// 삽입
		
		int result=0;
		try {
			result=MemberDao.getInstance().insertMember(member);   // 이 부분은 예외발생 우려가 잇음	
			
			// 응답데이터 형식 (JSON)
			response.setContentType("application/json; charset=UTF-8");
			
			// 응답 데이터 성공
			
			/*
			 * 삽입성공 
			 * {
			 * 		"isSuccess" : true
			 * }
			 * 
			 * 삽입실패 
			 * {
			 * 		"isSuccess" : false
			 * }
			 */
			JSONObject obj = new JSONObject();
			obj.put("isSuccess", result > 0);
			
			// 응답
			PrintWriter out = response.getWriter();
			out.print(obj.toString());
			out.close();
			
		}catch(Exception e) {
			// 예외처리( $.ajax()의 error프로퍼티로 응답)
			
			// 응답 데이터 형식(일반 텍스트)
			response.setContentType("text/plain; charset=UTF-8");
			
			// 응답 
			PrintWriter out = response.getWriter();
			out.println("신규회원등록이 실패했습니다.\\n입력 데이터를 확인하세요."); // 자바에선 역슬래쉬 하나로 됨.
			out.close();
		}
	}

}
