package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.PostDao;

public class PostListService implements PostService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("posts", PostDao.getInstance().selectAllPosts());
		request.setAttribute("count", PostDao.getInstance().countAllPosts());
		
	
		return new ActionForward("/post/list.jsp",false);
	}

}
