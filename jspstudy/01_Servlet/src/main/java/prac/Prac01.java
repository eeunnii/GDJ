package prac;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




// http://localhost:9090/01_Servlet/now 주소를 입력하면 
// 현재 날짜와 시간을 보여주기


@WebServlet("/now")
public class Prac01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Prac01() {
        super();
    }


    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//응답 데이터 타입(MINE-TYPE)
		response.setContentType("text/html; charset=UTF-9");
		
		// 응답 스트림 생성
		PrintWriter out = response.getWriter();
		
		// 현재 날짜시간 생성
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DATE);
		int hour = cal.get(Calendar.HOUR);
		int MINUTE = cal.get(Calendar.MINUTE);
		int SECOND = cal.get(Calendar.SECOND);
		
		
		// 응답 만들기 
		out.println("<h1>현재시간은 "+year+"년 ");
		
	    
		
		
	}

	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
