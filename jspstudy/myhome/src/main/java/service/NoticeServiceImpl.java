package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;
import domain.Notice;
import repository.NoticeDao;

public class NoticeServiceImpl implements NoticeService {


		@Override
		public ActionForward findAllNotices(HttpServletRequest request) {
			
			// 파라미터 page 확인
			// 파라미터 page가 없으면 page=1로 처리 
			Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
			int page = Integer.parseInt(opt.orElse("1"));
			
			// 전체 목록의 갯수 
			NoticeDao dao = NoticeDao.getInstance();
			int totalRecordCnt = dao.selectAllNoticesCnt();
			
			
			// 한 페이지에 표시할 목록의 개수 
			int recordPerPage = 10;
			
			// 특정 page에 표시할 목록의 시작번호와 끝번호
			//			begin   	end
			// page = 1 : 1			10
			// page = 2 : 11		20
			// page = 3 : 21		30
			// page = 4 : 31		35		(전체 목록이 35인 경우)
			
			// 여기서 begin과 end는 ROWNUM을 의미한다.
			// rownum 을 사용해서 게시글 번호를 맥여야함 notice_no으로 하면 게시글 삭제됐을 때 곤란하니까
			// 근데 역순은 어떻게 하는거임
			// 오라클..로 수업하는 이유.. 어려운거 배우기
			
			int begin = (page - 1) * recordPerPage + 1;
			int end = begin+recordPerPage-1;
			if(end>totalRecordCnt) { end=totalRecordCnt; }
			
			// begin+end를 Map으로 만들어서 NoticeDao에게 전달해야함
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("begin", begin);
			map.put("end", end);
			
			// begin에서 end사이 목록 가져오기
			List<Notice> notices = dao.selectAllNotices(map);
			
			// 목록을 forward하기 위해서 request에 저장
			request.setAttribute("notices", notices);
			
			// block개념 이해하기 
			// 1 block 당 3 page가 표시되는 상황  // 변수 선언해서 정해야함
			// 전체 7페이지가 있는 상황   // 계산해야함
			
			//           beginPage  endPage	  page
			// 1 block : 1          3          1  2  3 
			// 2 block : 4          6          4  5  6
			// 3 block : 7          7          7
			
			
			// beginPage페이지를 구하고 end페이지를 구한다.
			// 비긴과 엔드를 비교해서 7보다 더 작은 값을 엔드페이지로 쓴다 
			
			// 각 block의 beginPage와 endPage를 알아내기 위한 과정
			// 1) 전체 page의 개수를 구한다.  (전체페이지를 totalpage라고 할거임)
			// 2) 1 block 당 표시할 page의 개수를 임의로 정한다. (pagePerBlock)
			// 3) 현재 page와 전체 page 개수와 1 block당 표시할 page 개수로 beginPage를 구한다.
			// 4) beginPage를 이용해서 endPage를 구한다
			// 5) endPage와 전체 page 개수를 비교해서 작은 값을 endPage로 확정한다.
			int totalPgeCnt = totalRecordCnt/recordPerPage;  // totalRecordCnt=전체 //recordPerPage : 10 // 60나누기10인상태
			
			if(totalRecordCnt % recordPerPage >0) {
				totalPgeCnt++;
			}
			
			int pagePerBlock = 3;
			
			int beginPage=((page-1)/pagePerBlock)*pagePerBlock+1;
			
			int endPage = beginPage + pagePerBlock-1;
			
			if(endPage>totalPgeCnt) {
				endPage=totalPgeCnt;
			}
			
			
			// 페이징 처리에 필요한 정보를 list.jsp로 전달.
			request.setAttribute("page", page);
			request.setAttribute("beginPage", beginPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("totalPgeCnt", totalPgeCnt);
			request.setAttribute("pagePerBlock", pagePerBlock);
			
		
			
		
			// board 폴더의 list.jsp로 forward
			return new ActionForward("/notice/list.jsp", false);
		}
}

