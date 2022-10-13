package ex10_up_down;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DownloadServlet() {
        super();

    }
    //ㄴ코슨는 업로드할 내용에

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청 파라미터 
		request.setCharacterEncoding("UTF-8");
		String filename = request.getParameter("filename");
		
		// 다운로드할 파일 경로 
		String realPath = getServletContext().getRealPath("upload");
		
		// 다운로드할 파일 객체 
		java.io.File file = new java.io.File(realPath, filename);
		
		// 다운로드할 파일을 읽어들일 바이트 기반 입력 스트림
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		
		// 다운로드 응답 헤더
		response.setHeader("Content-Disposition", "attachment; filename=" + filename);
		response.setHeader("Content-Length", file.length()+"");  // file.length() 바이트로 나옴, +"" 문자열을 더해주면 문자열로 나옴. 
		
		// 겟라이터 : 문자 기반 . 
		
		// ▼ 문자를 내보내는게 아니라서 outputstream사용
		
		// 응답으로 내 보낼 바이트 기반 출력 스트림
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		
		// 파일 복사 
		byte[] b = new byte[1024];
		int readByte = 0;
		while((readByte = in.read(b)) != -1) { // 1025면 두번째에서 1만큼 읽으니까..
			out.write(b,0,readByte);
		}
		
		out.close();
		in.close();
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
