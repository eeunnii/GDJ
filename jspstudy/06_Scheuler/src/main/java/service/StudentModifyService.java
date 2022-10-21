package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Student;
import repository.StudentDao;

public class StudentModifyService implements StudentService{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		//요청파라미터 
		int stuNo = Integer.parseInt(request.getParameter("stuNo"));
		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math= Integer.parseInt(request.getParameter("math"));
		
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
		
		//DB로 보낼 Student student생성
		Student student = Student.builder()
								.stuNo(stuNo)
								.name(name)
								.kor(kor)
								.eng(eng)
								.math(math)
								.ave(ave)
								.grade(grade)
								.build();
		
		// DBfh Student student 보내기(수정)
		int result = StudentDao.getInstance().updateStudent(student);
		
		PrintWriter out = response.getWriter();
		if(result>0) {
			out.println("<script>");
			out.println("alert('학생정보 수정이 되었습니다.')");
			out.println("location.href='"+request.getContextPath()+"/student/detail.do?stuNo="+stuNo+"'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('학생정보 수정 실패 .')");
			out.println("history.back()");
			out.println("</script>");
		}
		 out.close();
		
		return null;
	
	}
}
