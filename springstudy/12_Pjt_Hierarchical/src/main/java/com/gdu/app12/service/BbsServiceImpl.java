package com.gdu.app12.service;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.app12.domain.BbsDTO;
import com.gdu.app12.mapper.BbsMapper;
import com.gdu.app12.util.PageUtil;

import lombok.AllArgsConstructor;



@AllArgsConstructor
@Service  // bin으로 등록함
public class BbsServiceImpl implements BbsService {
	
	// 필드가 2개 이상일 때 @Autowired를 해야하는 경우, @AllArgsConstructor(생성자를 이용)를 이용하면 모두 주입된다.
	
	private BbsMapper BbsMapper;
	private PageUtil pageUtil;
	

	@Override
	public void findAllBbsList(HttpServletRequest request, Model model) {
		
		// 파라미터 page 전달되지 않으면 page=1로 처리함
		Optional<String> op1t = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(op1t.orElse("1"));
		
		// 파라미터 recordPerPage, 전달되지 않으면 recordPerPage........
		Optional<String> opt2 = Optional.ofNullable(request.getParameter("recordPerPage"));
		int recordPerPage = Integer.parseInt(opt2.orElse("10"));
		
		// 전체 게시글 수 
		int totalRecord = BbsMapper.selectAllBbsCount();
		
		// 페이징에 필요한 모든 계산 완료
		pageUtil.setPageUtil(page, recordPerPage, totalRecord);
		
		// DB로 보낼 Map(begin+end)
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		
		// DB에서 목록 가져오기 
		java.util.List<BbsDTO> bbsList = BbsMapper.selectAllBbsList(map);
		
		// 뷰로 보낼 데이터 
		model.addAttribute("bbsList", bbsList);
		model.addAttribute("paging", pageUtil.getPaging(request.getContextPath()+"/bbs/list"));
		model.addAttribute("beginNo", totalRecord - (page - 1) * pageUtil.getRecordPerPage());
		model.addAttribute("recordPerPage",recordPerPage);
	}

	@Override
	public int addBbs(HttpServletRequest request) {
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String ip = request.getRemoteAddr();
		
		BbsDTO bbsdto = new BbsDTO();
		bbsdto.setTitle(title);
		bbsdto.setWriter(writer);
		bbsdto.setIp(ip);
		
		int result = BbsMapper.insertBbs(bbsdto);
	
		return result;
	}

	@Override
	public int addReply(HttpServletRequest requset) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeBbs(int bbsNo) {
		return BbsMapper.deleteBbs(bbsNo);
	}

}
