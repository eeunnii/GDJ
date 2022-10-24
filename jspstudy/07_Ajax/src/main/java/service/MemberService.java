package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MemberService {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//ajax 통신은 포워드,리다이렉트 둘 다 안하는 방식 , 한 jsp에서 데이터를 주고 받는게 ajax == 페이지 바뀌지 않음
	// actionforward를 반환하지 않음, 인덱스(웰컴파일)-> 이동 한번 할거임
	// ajax는 페이지 변경이 없는 특징이 잇음
	// 화면이 깜빡엿다 --> 페이지 이동. 에이작스는 아님

}
