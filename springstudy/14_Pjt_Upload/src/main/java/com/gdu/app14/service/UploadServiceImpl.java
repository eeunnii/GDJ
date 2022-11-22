package com.gdu.app14.service;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.net.http.HttpHeaders;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app14.domain.AttachDTO;
import com.gdu.app14.domain.UploadDTO;
import com.gdu.app14.mapper.UploadMapper;
import com.gdu.app14.util.MyFileUtil;

@Service
public class UploadServiceImpl implements UploadService {

	@Autowired
	private UploadMapper uploadMapper;
	
	@Autowired
	private MyFileUtil myFileUtil;
	
	@Override
	public List<UploadDTO> getUploadList() {
		return uploadMapper.selectUploadList();
		// 페이징처리하면 여기가 상당히 길어짐(?)
	}
	
	
	@Transactional
	@Override
	public void save(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
			
		/*  UPLOAD 테이블에 저장하기 */
		
		// 파라미터
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		
		// DB로 보낼 UploadDTO
		UploadDTO upload = UploadDTO.builder()
							.title(title)
							.content(content)
							.build();
		
		//System.out.println(upload);
		
		//DB에 UploadDTO에 저장
		int uploadResult = uploadMapper.insertUpload(upload);
		// 매퍼에서 생긴 uploadNo가 upload객체에 포함되어있음
		// </selectKey>에 의해서 인수 upload에 uploadNo값이 저장된다.
		
		/* ATTACH 테이블에 저장하기 */
		// 첨부된 파일 목록
		List<MultipartFile> files = multipartRequest.getFiles("files"); //<input type="file"
	
		

		
		// 1번 게시글에 첨부 3개 저장
		
		
		System.out.println(upload);
		
		
		
		
		// 첨부 결과 
		int attachResult = 0; 
		if(files.get(0).getSize() == 0) {  // 첨부가 없는 경우 (files 리스트에 [MultipartFile[field="files", filename=, contentType=application/octet-stream, size=0]] 이렇게 저장되어 있어서 files.size()가 1이다.
			attachResult = 1;
		} else {
			attachResult = 0;
		}
		
	
		// 첨부된 파일 목록 순회(하나씩 저장)
		for(MultipartFile multipartFile : files) {
			
			try {
				if(multipartFile != null && multipartFile.isEmpty() == false) {
					
					// file 리스트에서 파일 꺼낼 때 ??
					//  file[0]이 아닌 files.get(0)으로 꺼내야함.
					
					
					//원래 이름
					String origin = multipartFile.getOriginalFilename();
					origin  = origin.substring(origin.lastIndexOf("\\")+1); //  IE는 origin에 전체 경로가 붙어서 파일명만 사용해야 함
					// 마지막 경로 표기자 다음부터 끝까지 쓰겠다는 뜻??
					
					// 저장할 이름 
					String filesystem = myFileUtil.getFilename(origin);
					
					// 저장할 경로 
					String path = myFileUtil.getTodayPath();
					
					// 저장할 경로 만들기 
					File dir = new File(path);
					if(dir.exists()==false) {
						dir.mkdirs(); // s꼭 들어가야함
					}
					
					// 첨부할 File 객체
					File file = new File(dir, filesystem);
					
					// 첨부파일 서버에 저장(업로드진행)
					multipartFile.transferTo(file);
					
					// attachDTO생성
					AttachDTO attach = AttachDTO.builder()
					.path(path)
					.origin(origin)
					.filesystem(filesystem)
					.uploadNo(upload.getUploadNo())
					.build();
					
					// DB에 AttachDTO에 저장
					attachResult += uploadMapper.insertAttach(attach);
					// int = uploadMapper.insertAttach(attach);
					// 3개의 첨부파일의 삽입. 성공,실패,실패일 수도 있으니까 위에 코드는 잘 못 된거임
					// 첨부된 갯수하고 attachResult가 같은지 비교할거임
				}
				
			}catch(Exception e){
				
				
			}
		} // for 순회끝남
		
		
		
		// 응답만들기 
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();	
			
			if(uploadResult > 0 && attachResult == files.size()) {
				// 만약 첨부없이 글 올리면 attachResult=0, files.size()=1
				// 결론적으로 첨부없이 글 올리면 업로드실패했습니다 뜨고 첨부가 0인 상태로 테이블에 삽입됨.
				
				out.println("<script>");
				out.println("alert('업로드 되었습니다.');");
				out.println("location.href='"+multipartRequest.getContextPath()+"/upload/list'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('업로드 실패했습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
			out.close();
		
			 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	
		
		
		return uploadResult;
		
		
		// 업로드의 fk.
		// 게시글 번호가 들어갈 때 서비스는 알 수 없음
		// 서비스는 시퀀스가 얼마나 들어갔는지 알 수 없음
		// 게시글 pk를 서비스로 넘겨주는 방법 배울거임
		
	};
	
	
	@Override
	public void getUploadByNo(int uploadNo, Model model) {
		model.addAttribute("upload", uploadMapper.selectUploadByNo(uploadNo));
		model.addAttribute("attachList", uploadMapper.selectAttachList(uploadNo));
		
		// 조회수 증가 구현 ????
		
		
		
		
		
		
		
		
		
	}
	
	@Override
	public ResponseEntity<Resource> download(String userAgent, int attachNo) {
		
		// 다운로드 할 첨부 파일의 파일의 정보(경로, 이름)
		AttachDTO attach = uploadMapper.selectAttachByNo(attachNo);
		File file = new File(attach.getPath(), attach.getFilesystem()); // 원래 이름은 DB에만 있는거임
				
		// 반환할 Resource
		Resource resource = new FileSystemResource(file);
		
		// Resource가 없으면 종료(다운로드할 파일이 없음)
		if(resource.exists() == false) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		
		
		// ResponseEntity<Resource> 특징 : 페이지변화없이 값만 반환함 
		// ajax에서 필요한 ResponseEntity를 쓸 수 있다?
		
		
		//다운로드 횟수 증가 
		uploadMapper.updateDwonlaodCnt(attachNo);
		
		// 다운로드 되는 파일명(브라우저마다 다르게 세팅)
		String origin = attach.getOrigin();
		try {
			
			// IE (userAgent에 "Trident" 가 포함되어 있음) ??
			if(userAgent.contains("Trident")) {
				origin = URLEncoder.encode(origin, "UTF-8").replaceAll("\\+"," "); 
						//인코딩을 하게 되면 공백대신 플러스가 생김
						//replaceAll정규식사용
						// + 는 정규식에서 
						// 정규식에서 *는??
			}
			// Edge(userAgent에 "Edg"가 포함되어있음)
			else if(userAgent.contains("Edg")) {
				origin = URLEncoder.encode(origin, "UTF-8");
				
			}
			
			// 나머지
			else {
				origin = new String(origin.getBytes("UTF-8"), "ISO-8859-1");
			}
			

			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		// jsp 시간에 다운로드헤어더 만들기 배웠었음 
		
		// 다운로드 헤더 만들기 
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Disposition", "attachment; filename=" + origin);
		header.add("Content-Length" , file.length() + "");
		
		return ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}
	
	
	@Override
	public void removeAttachByAttachNo(int attachNo) {
		
		// 삭제할 Attach 정보 가져오기 
		AttachDTO attach = uploadMapper.selectAttachByNo(attachNo);  // 삭제 전에 정보를 가져온다.

		// DB에서 Attach 정보 삭제
		int result = uploadMapper.deleteAttachByAttachNo(attachNo);  // DB에서 지워짐
		
		// 첨부파일 삭제 
		if(result >0) {
			
			// 첨부 파일을 파일 객체로 만듦
			File file = new File(attach.getPath(), attach.getFilesystem());
			
			// 삭제 
			if(file.exists()) {
				file.delete();
			}
		
		}
		
		
	}
	
	
	
	
	
}
