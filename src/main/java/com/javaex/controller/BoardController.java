package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	//수정
	@RequestMapping(value="board/modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo bVo, Model model) {
		System.out.println("BoardController.modify()");
		
		boardService.exeUpdate(bVo);
		
		//글 정보 가져오기
		BoardVo boardVo = boardService.exeSelectOne(bVo.getNo());
		model.addAttribute("boardVo", boardVo);
		
		return "board/read";
	}
	
	//수정 폼
	@RequestMapping(value="board/modifyform", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@RequestParam(value="no") int no, Model model) {
		System.out.println("BoardController.modifyForm()");
		
		//글 정보 가져오기
		BoardVo boardVo = boardService.exeSelectOne(no);
		model.addAttribute("boardVo", boardVo);
		
		return "board/modifyForm";
	}
	
	//글 읽기
	@RequestMapping(value="board/read", method= {RequestMethod.GET, RequestMethod.POST})
	public String read(@RequestParam(value="no") int no, Model model) {
		System.out.println("BoardController.read()");
		
		//글 조회수 증가시키기 왜안됨?
		boardService.exeAddHit(no);
		
		//글 정보 가져오기
		BoardVo boardVo = boardService.exeSelectOne(no);
		model.addAttribute("boardVo", boardVo);
		
		return "board/read";
	}
	
	//글 삭제
	@RequestMapping(value="board/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam(value="no") int no) {
		System.out.println("BoardController.delete()");
		
		boardService.exeDelete(no);
		
		return "redirect:/board/list";
	}
	
	//글 등록
	@RequestMapping(value="board/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo) {
		System.out.println("BoardController.write()");
		
		boardService.exeWrite(boardVo);
		
		return "redirect:/board/list";
	}
	
	//글쓰기폼
	@RequestMapping(value="board/writeform", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("BoardController.writeForm()");
		
		return "board/writeForm";
	}
	
	//게시판
	@RequestMapping(value="board/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String boardList(Model model) {
		System.out.println("BoardController.boardList()");
		
		List<BoardVo> bList = boardService.exeSelectList();
		
		model.addAttribute("bList", bList);
		
		return "board/list";
	}

}
