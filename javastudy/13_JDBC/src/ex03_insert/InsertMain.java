package ex03_insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import domain.Board;

public class InsertMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		// 게시판 정보 입력 받아서 BOARD 테이블에 저장하기 
		
		
		
		// 1. SCANNER 클래스로 정보 입력 받기 
		
		
		
		// 2. Borad 클래스 타입의 객체에 입력 받은 정보 저장하기 
		
		
		
		Scanner sc = new Scanner(System.in);
		
		
		//보드넘버는 시퀀스로 조회수는 0으로 생성일은 현재날짜 sysdate로 처리함 입력 받을거는 2개 
		 
		System.out.print("제목 >>> ");
		String title = sc.nextLine();
		
		System.out.print("내용 >>> ");
		
		String content = sc.nextLine();
		
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		
		Connection con = null;
		PreparedStatement ps = null;
		
		
		
		
		// OracleDriver 클래스 로드
		// OracleDriver 클래스가 저장된 ojdbc.jar 파일을 Classpath에 등록
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			
			//DB접속 - Connection 객체 생성
			String url = "jdbc:oracle:thin:@localhost:1521:xe";  //Thin 이 아니고 thin임	//////////데이터베이스마다 주소가 다름 쓰고 있는 오라클의 정확한 버전을 알면 구글링을 통해서 알 수 있음//xe(버전이름)
			//cafe24 3개월 구매 추천 : 만이천원 안넘음 // 오라클은 비쌈 설치만 한달 해놔도 15만원나옴
			
			String user = "scott";
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);
			
			
			
			// 아래문장에서 ? 는 변수라는 뜻임// ? 로 안쓰고 바로 위에 선언한 변수명쓰면 보안에 취약해짐
			String sql = "INSERT INTO BOARD(BOARD_NO, TITLE, CONTENT, HIT, CREATE_DATE) VALUES (BOARD_SEQ.NEXTVAL, ?, ?, 0, SYSDATE)";
			
			
			
			// PreparedStatment 객체 생성  - Statement는 보안때문에 안씀 (로그인창의 아이디 비번칸에서 drop같은 쿼리문을 넘길 수 있어서 )
			ps = con.prepareStatement(sql);
			
			//쿼리문에 포함된 ? 에 변수 전달하기 
			//쿼리문에 작성된 ? 순서대로 명시하여 값을 채워줌 
			
			ps.setString(1, board.getTitle());		//  첫번째 ?에 board.getTitle() 전달하기 - title이 스트링이라서 setString 쓴거임
			ps.setString(2, board.getContent());	// setString. content가 String이니까 setstring
			
			
			// INSERT	문의 실행 (메소드 외우기)
			//1. executeUpdate() 메소드 사용하기 
			//2. executeUpate() 메소드의 반환값은 int 타입 
			//3. 반환값
			//		1) 1이 반환되는 경우 : 1개의 행이 insert되었다는 의미. 성공을 의미함
			//		2) 0이 반환되는 경우 : 0개의 행이 insert되었다는 의미 = insert 안됐다는 의미임. 실패 
			int result = ps.executeUpdate();
			if(result > 0) {									// 1보다 큰거 아니고 0보다 큼 
				System.out.println("성공");
			}else {
				System.out.println("실패");
				
			}
			
			
			
			
			
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
				if(con != null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
	}

}
