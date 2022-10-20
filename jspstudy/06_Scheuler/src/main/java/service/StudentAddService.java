package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Student;
import repository.StudentDao;

public class StudentAddService implements StudentService{
	@Override
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		// 요청 파라미터
		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		
		// 파생값
		double ave = (kor+eng+math)/3.0; // 그냥 3으로 나누면 소수점 다 없어짐
		String grade;
		switch((int)(ave/10)) {
		case 10: case 9: grade="A"; break;
		case 8: grade="B";break;
		case 7: grade="D";break;
		case 6: grade="E";break;
		default: grade = "F";
		
		}
		
		//DB로 보낼 Student student객체생성
		Student student = Student.builder()
				.name(name)
				.kor(kor)
				.eng(eng)
				.math(math)
				.ave(ave)
				.grade(grade)
				.build();
		
		//DB에 Student student 삽입
		int result = StudentDao.getInstance().insertStudent(student);
				
		
		// 삽입 성공 실패
		PrintWriter out = response.getWriter();
		if(result>0) {
			out.println("<script>");
			out.println("alert('신규학생이 등록 되었습니다.')");
			out.println("location.href='"+request.getContextPath()+"/student/list.do'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('학생등록 시류패 .')");
			out.println("history.back()");
			out.println("</script>");
		}
		 out.close();
		
		return null; // Service를 통해서 직접 응답했기 때문에 컨트롤러로 null를 반환함
				// 컨트롤러가 null을 반환받으면 리다이렉트/포워드 모두 수행하지 않음 ..
		
		
		
	}
}
