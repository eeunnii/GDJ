package com.gdu.notice.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.notice.domain.NoticeDTO;
import com.gdu.notice.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper mapper;
	
	@Override
	public void findAllNotices(Model model) {
		model.addAttribute("notices", mapper.selectAllNotices());
	}

	@Override
	public int increseNoticeHit(int noticeNo) {
		return mapper.updateHit(noticeNo);
	}
	
	@Override
	public void findNoticeByNo(int noticeNo, Model model) {
		model.addAttribute("notice", mapper.selectNoticeByNo(noticeNo));
	}

	@Override
	public void addNotice(HttpServletRequest request, HttpServletResponse response) {
		NoticeDTO notice = new NoticeDTO();
		notice.setTitle(request.getParameter("title"));
		notice.setContent(request.getParameter("content"));
		int result = mapper.insertNotice(notice);
		response.setContentType("text/html; charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			if(result > 0) {  // if(result == 1) {
				out.println("<script>");
				out.println("alert('새로운 공지사항이 등록되었습니다.');");
				out.println("location.href='" + request.getContextPath() + "/ntc/list';");
				// out.println("location.href='/notice/ntc/list';");  당장은 되지만 미래를 위해서 사용하지 않는다.
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('공지사항이 등록되지 않았습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modifyNotice(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		NoticeDTO notice = new NoticeDTO();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setNoticeNo(noticeNo);
		int result = mapper.updateNotice(notice);
		response.setContentType("text/html; charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			if(result > 0) {  // if(result == 1) {
				out.println("<script>");
				out.println("alert('공지사항이 수정되었습니다.');");
				out.println("location.href='" + request.getContextPath() + "/ntc/detail?noticeNo=" + noticeNo + "';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('공지사항이 수정되지 않았습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeNotice(HttpServletRequest request, HttpServletResponse response) {
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		int result = mapper.deleteNotice(noticeNo);
		response.setContentType("text/html; charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			if(result > 0) {  // if(result == 1) {
				out.println("<script>");
				out.println("alert('공지사항이 삭제되었습니다.');");
				out.println("location.href='" + request.getContextPath() + "/ntc/list';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('공지사항이 삭제되지 않았습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
