package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import repository.MemberDao;

public class MemberListService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ★ajax에서 모든 서비스가 할 일 : json을 만들어서 응답합★
		// jsp가 돌아온건 말이 안됨 
		// 데이터 자체가 응답으로 넘어감
		// 요청하는 jsp와 응답받는 jsp가 같은게 ajax의 특징
		// mvc 패턴은 a에서 요청하면 b로 갓음 jsp바꿔가면서, 근데 ajax에서는 그게 업음
		
		
		//응답데이터 형식(SJON) -- 덮어쓰기 연습??
		response.setContentType("application/json; charset=UTF-8");
		
		//응답 데이터 만들기 
		/*
		   {
		   	"count" : 3,
		   	"members" : [
		   		{
		   			"memberNo" : 1, 
		   			"id" : "user1",
		   			"name" : "회원1",
		   			"gender" : "F"
		   			"grade" : "gold",
		   			"address" : "jeju"
		   		},
		   		{
		   			"memberNo" : 2, 
		   			"id" : "user2",
		   			"name" : "회원2",
		   			"gender" : "M"
		   			"grade" : "sliver",
		   			"address" : "seoul"
		   		},
		   		{
		   			"memberNo" : 3, 
		   			"id" : "user3",
		   			"name" : "회원32",
		   			"gender" : "F"
		   			"grade" : "bronze",
		   			"address" : "jeju"
		   		}
		   	]
		 
		 
		 */
		
		
		JSONObject obj = new JSONObject();
		obj.put("count", MemberDao.getInstance().selectAllMemberCount());
		obj.put("members", MemberDao.getInstance().selectAllMember()); // selectAllMember()반환타입이 List임
		
		// 응답
		PrintWriter out = response.getWriter();
		out.println(obj.toString()); // JSON문자열을 응답
		out.close();
	}

}
