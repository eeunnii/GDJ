package com.gdu.app12.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.gdu.app12.domain.BbsDTO;
import com.gdu.app12.mapper.BbsMapper;
import com.gdu.app12.util.PageUtil;
import com.gdu.app12.util.SecurityUtil;

import lombok.AllArgsConstructor;



@AllArgsConstructor
@Service  // bin으로 등록함
public class BbsServiceImpl implements BbsService {
	
	// 필드가 2개 이상일 때 @Autowired를 해야하는 경우, @AllArgsConstructor(생성자를 이용)를 이용하면 모두 주입된다.
	
	private BbsMapper BbsMapper;
	private PageUtil pageUtil;
	private SecurityUtil SecurityUtil;
	
	//   < 이건 작다 표시임 &lt; 이거 
	

	@Override
	public void findAllBbsList(HttpServletRequest request, Model model) {
		
		//System.out.println(SecurityUtil.getAuthCode(4)); 확인용 -- 인증받을 때 쓰기
		//System.out.println(SecurityUtil.getAuthCode(4));
		
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
		model.addAttribute("paging", pageUtil.getPaging(request.getContextPath()+"/bbs/list"));   //깃허브 보기 
		model.addAttribute("beginNo", totalRecord - (page - 1) * pageUtil.getRecordPerPage());
		model.addAttribute("recordPerPage",recordPerPage);
	}

	@Override
	public int addBbs(HttpServletRequest request) {
		
		String writer = SecurityUtil.sha256(request.getParameter("writer"));
		String title = SecurityUtil.preventXSS(request.getParameter("title"));   // ㄱ깃허브 보기 
		String ip = request.getRemoteAddr();  
		
		BbsDTO bbsdto = new BbsDTO();
		bbsdto.setTitle(title);
		bbsdto.setWriter(writer);
		bbsdto.setIp(ip);
		
		int result = BbsMapper.insertBbs(bbsdto);
	
		return result;
	}

	/*
	 * 트랜잭션 처리방법 2가지... 8장대로 할 것인가 @붙여서 할 것인가 
	 * 8장 스타일 : 모든 메소드에 트랜잭션을 처리하겠다는 뜻. 장점 : 절대 트랜잭션 처리를 놓칠일이 없음 단점: 안해도 되는거 하니까 성능에 오버헤드가 걸림
	 * 
	 * 지금 스타일 : 개발자가 놓칠 수도 있음. 성능 오버헤드X 
	 * 
	 * @Transactional                
	 * 안녕 . 난 트랜잭션을 처리하는 애너테이션이야
	 * INSERT/UPDATE/DELETE 중 2개 이상이 호출되는 서비스에 추가하면 돼
	 */
	
	@Transactional
	@Override
	public int addReply(HttpServletRequest request) {
		// 작성자, 제목
		String writer = SecurityUtil.sha256(request.getParameter("writer"));
		String title = SecurityUtil.preventXSS(request.getParameter("title"));
		
		// IP
		String ip = request.getRemoteAddr();	
		
		
		// 첫번째 방법. 원글의 번호를 받아와서 DB로 간다, 나머지 정보를 가지고 온다.
		// 두 번째 방법. DEPT , , 3개를 jsp에서(view)에서 받아온다.
		// 원글의 정보는 list.jsp에 있다. 그래서 jsp에서 원글의 정보를 파라미터로 넘겨서 사용할거임
		
		// 원글의 DEPTH, GROUP_NO, GROUP_ORDER
		int depth = Integer.parseInt(request.getParameter("depth"));
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		int groupOrder = Integer.parseInt(request.getParameter("groupOrder"));
		
		// 원글 DTO 만들기 
		BbsDTO bbs = new BbsDTO();
		bbs.setDepth(depth);
		bbs.setGroupNo(groupNo);
		bbs.setGroupOrder(groupOrder);
		
		//updatePreviousRepl 싱행
		BbsMapper.updatePreviousReply(bbs);
		
		// 답글 DTO
		BbsDTO reply = new BbsDTO();
		reply.setWriter(writer);
		reply.setTitle(title);
		reply.setIp(ip);
		reply.setDepth(depth+1);	    // 답글 depth : 원글 depth + 1
		reply.setGroupNo(groupNo);  	// 답글 groupNo : 원글 groupNo
		reply.setGroupOrder(groupOrder+1);  // 답글의 groupOrder는 원글 groupOrder+1이다.
		
		// insertReply 실행
		return BbsMapper.insertReply(reply);
	}

	@Override
	public int removeBbs(int bbsNo) {
		return BbsMapper.deleteBbs(bbsNo);
	}

}
