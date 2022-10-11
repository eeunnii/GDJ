package home;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
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

import org.json.JSONObject;
import org.json.XML;


@WebServlet("/Weekend01")
public class Weekend01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Weekend01() {
        super();
 
    }



		
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("age", age);
		
		JSONObject person = new JSONObject();
		obj.put("person",obj);
		
		String xmldata = XML.toString(person);
		
		response.setContentType("application/xml; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(xmldata);
		out.close();
		


	}
		
		
		
		
		
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
