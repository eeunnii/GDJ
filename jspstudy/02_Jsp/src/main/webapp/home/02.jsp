<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	// 요청 파라미터
	request.setCharacterEncoding("UTF-8");
	String created_date = request.getParameter("created_date");
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	
	// 작성자 IP
	String userip = request.getRemoteAddr();
	
	
	// 파일명
	// 작성자IP_작성자.txt
	
	// IPv4
	// 127.0.0.1
	// 127_0_0_1  replaceAll("\\.", "_")

	// IPv6
	// 0:0:0:0:0:0:0:1
	// 0_0_0_0_0_0_0_1  replaceAll(":", "_")
	
	String filename = userip.replaceAll("\\.", "_").replaceAll(":", "_")+"_"+writer+".txt";
	
	// 파일 경로
	String realPath = application.getRealPath("storage"+File.separator+created_date);
	File dir = new File(realPath);
	if(dir.exists() == false){
		dir.mkdirs();
	}
	
	File file = new File(dir, filename);
	
	BufferedWriter bw = new BufferedWriter(new FileWriter(file));
	bw.write("작성일자 :" + created_date+"\n");
	bw.write("작성자 : " + writer + "\n");
	bw.write("제목 : "+ title+"\n");
	bw.write("내용\n"+content+"\n");
	bw.close();
%>
	<script type="text/javascript">

	if(<%=file.exists()%>){
		if(isCreated){
			alert('<%=file.exists()%> 파일이 생성되었습니다.');
			
		}else{
			
		}
	}
		
</script>

