package home;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Weekend01")
public class Weekend01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Weekend01() {
        super();
 
    }



		
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String clientId = "_48qz3Z7EcTkgD6lEtVx";
		String clientSecret = "_fp9SsfDX0";
		
		request.setCharacterEncoding("UTF-8");
		String query = request.getParameter("UTF-8");
		String display = request.getParameter("display");
		
		try {
			query = URLEncoder.encode(query, "UTF-8");
		}catch(UnsupportedEncodingException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("검색어 인코딩 실패");
			out.close();
		}
		
		
		
		String apiURL = "https://openapi.naver.com/v1/search/movie.xml?&query="+query+"&display="+display;
		URL url = null;
		HttpURLConnection con = null;
		
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
		}catch(MalformedURLException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API URL이 잘못되었습니다.");
			out.close();
		}catch(IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API 연결이 실패하였습니다.");
			out.close();
		}
		
		try {
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			
		}catch(IOException e) {
			
		}
		
		
		

	}
		
		
		
		
		
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
