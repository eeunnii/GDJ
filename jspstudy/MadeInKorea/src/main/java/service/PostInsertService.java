package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Post;
import repository.PostDao;

public class PostInsertService implements PostService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String witer = request.getParameter("witer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		Post post = new Post();
		post.setWiter(witer);
		post.setTitle(title);
		post.setContent(content);
		
		int result = PostDao.getInstance().insertPost(post);
		
		PrintWriter out;
		try {
			out = response.getWriter();
			if(result>0) {
				out.println("<script>");
				out.println("alert('삽입성공.')");
				out.println("location.href='"+request.getContextPath()+"/post/list.do'");
				out.println("</script>");	
			} else {
				out.println("<script>");
				out.println("alert('게시글이 등록이 실패했습니다..')");
				out.println("history.back()");  // history.go(-1)
				out.println("</script>");
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
