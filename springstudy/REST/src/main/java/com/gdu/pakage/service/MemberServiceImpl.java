package com.gdu.pakage.service;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.gdu.pakage.domain.MemberDTO;
import com.gdu.pakage.mapper.MemberMapper;
import com.gdu.pakage.util.PageUtil;



// java.sql.SQLIntegrityConstraintViolationException : not null컬럼에 데이터가 안들어가면 나오는 오류

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberMapper memberMapper; 
	
	@Autowired
	private PageUtil pageUtil;

	
	
	@Override
	public Map<String, Object> register(MemberDTO member, HttpServletResponse response){
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("insertResult", memberMapper.insertMember(member));
			return result;
		}catch(DuplicateKeyException e) {  // 아이디 중복

			
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				response.setStatus(501); // 응답 코드 501
				out.println("이미 사용중인 아이디입니다.");  // 응답 메세지
				out.close();
			}catch(Exception e2) {  // catch블럭에서 만든 블럭은 알아서 jsp-ajax(handle)의 error로 감
				e2.printStackTrace();
			}
			
			return null;
			
			
		}catch (DataIntegrityViolationException e) {
			
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				response.setStatus(501); // 응답 코드 501
				out.println("필수 정보가 누락되었습니다.");  // 응답 메세지
				out.close();
			}catch(Exception e2) {  // catch블럭에서 만든 블럭은 알아서 jsp-ajax(handle)의 error로 감
				e2.printStackTrace();
			}
			
			return null;
			
		}catch (Exception e) {
			
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				response.setStatus(501); // 응답 코드 501
				out.println("입력 정보를 확인하세요.");  // 응답 메세지
				out.close();
			}catch(Exception e2) {  // catch블럭에서 만든 블럭은 알아서 jsp-ajax(handle)의 error로 감
				e2.printStackTrace();
			}
			
			return null;
			
		}	
		

	}
	
	
	
	@Override
	public Map<String, Object> getMemberList(int page) {

		int totalRecord = memberMapper.selectMemberCount();
		pageUtil.setPageUtil(page, totalRecord);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageUtil.getBegin());
		map.put("begin", pageUtil.getEnd());
		
		Map<String, Object> result = new HashMap<String, Object>();
		map.put("memberList", memberMapper.selectMemberDTO(map));
		map.put("pageUtil", pageUtil);
		
		return result;
		
	}
	
	@Override
	public Map<String, Object> getMemberByNo(int memberNo) {
		
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("member", memberMapper.selectMemberDTO(memberNo));
		
		
		
		return null;
	}
	
	@Override
	public Map<String, Object> modifyMember(Map<String,Object> map, HttpServletResponse response) {
		
		try {
			Map<String,Object> result = new HashMap<String, Object>();
			result.put("updateResult", memberMapper.updateMember(result));
			return result;
		}catch(DataIntegrityViolationException e){
			try {
				response.setContentType("text.plain; charset=UTF-8");
				PrintWriter out = response.getWriter();
				response.setStatus(501);
				out.print("필 수 정 보 누락 됨"); //응답메세지
				out.close();
				
				// 한 줄이면 되는데 
				
			}catch(Exception e2) {
				e2.printStackTrace();
			}
			
			
		}
		
		
		
		return null;
	}
	
	
	@Override
	public Map<String, Object> removeMemberList(String memberNoList) {
		
		
		List<String> list = Arrays.asList(memberNoList.split(",")); /// 정규식임... 정규식에서 마침표는 모든 문자라는 뜻... \. 로 해야... 근데 , 는 정규식에서 아무 역할이 없음 찝찝하면 \\, 가능함
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("deleteResult", memberMapper.deleteMemberList(list));  // deleteResult실제로 삭제된 데이터의 갯수가 반환되서 2가 나옴?? 
		
		return null;
	}
	
	
	
}
