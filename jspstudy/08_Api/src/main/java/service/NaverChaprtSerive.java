package service;

import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface NaverChaprtSerive {
	
public String getChaptchakey();
public Map<String, String> getChaptchaImage(HttpServletRequest requset, String key);
public void refreshCaptcha(HttpServletRequest requset, HttpServletResponse response);
public boolean validateUserInput(HttpServletRequest request);
}
