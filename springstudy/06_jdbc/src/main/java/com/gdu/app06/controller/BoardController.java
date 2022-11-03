package com.gdu.app06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app06.domain.BoardDTO;
import com.gdu.app06.service.BoardService;

@Controller
public class BoardController {
	
	//컨트롤러는 service를 사용함
	
	@Autowired   // 컨테이너에 생성된 bean중에서 BoardService 타입의 bean을 가져오시오
	private BoardService boardService;
	
	
	
@GetMapping("/")
public String index() {
	return "index";
}

@GetMapping("brd/list")
public String list(Model model) {  // Model은 forward할 속성(attr)을 저장할 때 사용한다.  -- 목록 가지고 가야되니까 포워ㄷ 기본!!
	model.addAttribute("boards", boardService.findAllBoards());
	return "board/list"; // board폴더의 list.jsp로 forward
	
}

@GetMapping("brd/write")
public String write() {
	return "board/write"; // board폴더의 write.jsp로 forward
}

@PostMapping("brd/add")
public String add(BoardDTO board) {
	boardService.saveBoard(board); // saveBoard()로부터 0/1이 반환되지만 처리하지 않았음 // insert delete update는 리다이렉트
	return "redirect:/brd/list";
}

@GetMapping("brd/detail")  // 상세보기는 get으로 처리함
public String detail(@RequestParam(value="board_no", required=false, defaultValue="0") int board_no
						,Model model) {
	model.addAttribute("board", boardService.findBoardByNo(board_no));
	return "board/detail";
}	


@PostMapping("brd/edit")
public String edit(int board_no, Model model) {
	model.addAttribute("board", boardService.findBoardByNo(board_no));
	return "board/edit";  // board폴더의 edit, jsp로 forward
}

@PostMapping("brd/modify")
public String modify(BoardDTO board) {
	boardService.modifyBoard(board);
	return "redirect:/board/detail?board_no="+board.getBoard_no();
}

@PostMapping("brd/remove")
public String remove(int board_no) {
	boardService.removeBoard(board_no);  // removeBoard로 부터 반환된 0,1 은 처리하지 않음
	return "redirect:/brd/list";
}
	
	

}
