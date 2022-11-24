package com.gdu.app15.service;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app15.domain.BlogDTO;
import com.gdu.app15.mapper.BlogMapper;
import com.gdu.app15.util.MyFileUtil;
import com.gdu.app15.util.PageUtil;

@Service
public class BlogServiceImpl implements BlogService {

	private BlogMapper blogMapper;
	private PageUtil pageUtil;
	private MyFileUtil myFileUtil;
	
	@Autowired 
	public void set(BlogMapper blogMapper,PageUtil PageUtil, MyFileUtil myFileUtil) {
		this.blogMapper = blogMapper;
		this.pageUtil = PageUtil;
		this.myFileUtil = myFileUtil;
		
	}
	// @Autowired 가 적용되는 곳 : 매개변수
	// 컨트롤러에서 이런 패턴이 나옴 
	// 
	
	
	
	@Override
	public void getBlogList(Model model) {
		
		// 모델은 매개변수로 정의 안되어 있어도(?)보낼 수 있음
		
		// Model에 저장된 request 꺼내기
		Map<String, Object> modelMap = model.asMap();  // model을 map으로 변신
		HttpServletRequest request = (HttpServletRequest) modelMap.get("request");
		
		// page 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// 전체 블로그 개수
		int totalRecord = blogMapper.selectBlogListCount();
		
		// 페이징 처리에 필요한 변수 계산
		pageUtil.setPageUtil(page, totalRecord);
		
		// 조회 조건으로 사용할 Map 만들기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		
		// 뷰로 전달할 데이터를 model에 저장하기 
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("blogList", blogMapper.selectBlogListByMap(map));
		model.addAttribute("beginNo", totalRecord - (page - 1) * pageUtil.getRecordPerPage());
		model.addAttribute("paging", pageUtil.getPaging(request.getContextPath() + "/blog/list"));
		model.addAttribute("paging", pageUtil.getPaging(request.getContextPath()));
		
		
		
	}
	
	@Override
	public void saveBlog(HttpServletRequest request, HttpServletResponse response) {


		//파라미터 title, content
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		
		
		// 깃허브 참고~!
		
		// 작성자의 ip
		// 작성된 내용이 어딘가를 경유해서 도착하면 원래 ip가 X-Forwarded-For라는 요청헤더에 저장
		// 출발			경유 		도착
		// 1.1.1.1		2.2.2.2		2.2.2.2 : request.getRemoteAddr()
		//							1.1.1.1 : request.getHeader("X-Forwarded-For")
		Optional<String> opt = Optional.ofNullable(request.getHeader("X-Forwarded-For"));
		String ip = opt.orElse(request.getRemoteAddr());
		
		// DB로 보낼 BlogDTO
		BlogDTO blog = BlogDTO.builder()
					.title(title)
					.content(content)
					.ip(ip)
					.build();
					
					
		// DB에 저장
		int result = blogMapper.insertBlog(blog);
		
		// 응답
		try {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(result>0) {
				out.print("<script>");
				out.print("alert('삽입성공');");
				out.print("location.href='"+request.getContextPath()+"/blog/list';");
				out.print("</script>");
			}else {
				out.print("<script>");
				out.print("alert('삽입 실패');");
				out.print("history.back()");
				out.print("</script>");
			}
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
 		
		
	}
	
	
	@Override
	public Map<String, Object> saveSummernoteImage(MultipartHttpServletRequest multipartRequest) {
		
		// 파라미터 file
		MultipartFile multipartFile = multipartRequest.getFile("file");
		
		// 저장할 파일명
		String filesystem = myFileUtil.getFilename(multipartFile.getOriginalFilename());
		
		//저장경로
		// 윈도우는 시 드라이브, 디 드라이브.. 가 있는데 
		// 리눅스에서 최상단은 슬래시임. 리눅스의 격리기술. 하위폴더를  root로 설정하면 위에 파일들은 못 보고 root로 설정한 파일 하위목록만 볼 수 있다
		
		// 여기 윈도우 니까 드라이브 기준으로 저장경로 잡아줌
		
		//String path = "C:" + File.separator  // "C:" 는 윈도우에서만 쓸 수 있고, 리눅스에서는 못 씀. 리눅스는 드라이브라는 개념이 없음
		String path = "C:\\upload";
		
		// 저장 경로가 없으면 만들기 
		File dir = new File(path);
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		
		//저장할 File 객체 
		File file = new File(dir, filesystem); // dir말고 path도 가능함
		
		//HDD에 File객체 저장하기 
		try {
			multipartFile.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 저장된 파일을 확인할 수 있는 매핑을 반환 -- 경로라고안하고 매핑이라고 했음
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("src", multipartRequest.getContextPath()+"/load/image/"+filesystem);  // src라는 이름이 여기서 결정됨
		
		
		
		return map;
		
		// 저장된 파일이 aaa.jpg라고 가정하면
		//   src=${contextPath}/load/image/aaa.jpg 이다.
		
	}
	
	@Override
	public int increaseBlogHit(int blogNo) {
		return blogMapper.updateHit(blogNo);
		
		
	}
	
	@Override
	   public BlogDTO getBlogByNo(int blogNo) {
	      return blogMapper.selectBlogByNo(blogNo);
	   }
	
	
	@Override
		public void modifyBlog(HttpServletRequest request, HttpServletResponse response) {
			
			
			
		
		
		
			
		}
	
	
	@Override
	public void removeBlog(HttpServletRequest request, HttpServletResponse response) {
		
		
		
		
		
		
	}
	
	
	
}
