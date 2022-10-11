package ex06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MovieXMLServlet")
public class MovieXMLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MovieXMLServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 클라이언트 아이디, 시크릿
		String clientId = "_48qz3Z7EcTkgD6lEtVx";
		String clientSecret = "_fp9SsfDX0";

		// 요청 파라미터 (검색어ㅡ 결과갯수)
		request.setCharacterEncoding("UTF-8");
		String query = request.getParameter("query"); // 사용자가 입력한 정보가 들어잇음.
		String display = request.getParameter("display");

		// 검색어 UTF-8 인코딩
		try {
			query = URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("검색어 인코딩 실패"); // 해당 글씨는 error의 responseText로 들어간다
			out.close();
		}

		// API 접속
		String apiURL = "https://openapi.naver.com/v1/search/movie.xml?query=" + query + "&display=" + display;
		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) { // 잘못된 형태의 url일때 뜸. 주소 틀렸을 때
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API URL이 잘못되었습니다.");
			out.close();
		} catch (IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out1 = response.getWriter();
			out1.println("API 연결이 실패하였습니다.");
			out1.close();
		}

		// API 요청
		try {
			// 요청 메소드 // Method Setting(GET/POST)
			con.setRequestMethod("GET");
			// 요청 헤더
			con.setRequestProperty("X-Naver-Client-Id", clientId); // key, vaule
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		} catch (IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API 요청이 실패하였습니다");
			out.close();
		}

		// 응답 스트림 생성 (정상, 에러 스트림)
		BufferedReader reader = null;
		try {
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
		} catch (IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println();
			out.close();
		}

		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("API 응답이 실패했습니다");
			out.close();
		}

		// client.html로 API 응답 결과 보내기
		response.setContentType("application/xml; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
		out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
