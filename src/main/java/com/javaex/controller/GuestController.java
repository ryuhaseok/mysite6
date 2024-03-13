package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;

@Controller
public class GuestController {
	
	@Autowired
	private GuestService guestService;

	
	//삭제
	@RequestMapping(value="guest/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestVo guestVo) {
		System.out.println("GuestController.delete");
		
		guestService.exeDeleteGuestbook(guestVo);
		
		return "redirect:addlist";
	}
	
	//삭제폼
	@RequestMapping(value="guest/deleteform", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm() {
		System.out.println("GuestController.deleteForm()");
		
		return "guestbook/deleteForm";
	}
	
	//방명록 등록
	@RequestMapping(value="guest/add", method= {RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestVo guestVo) {
		System.out.println("GuestController.add()");
		
		guestService.exeInsertGuestbook(guestVo);
	
		return "redirect:addlist";
	}
	
	//방명록폼 리스트
	@RequestMapping(value="guest/addlist", method= {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("GuestController.addList()");
		
		List<GuestVo> gList = guestService.exeSelectList();
		System.out.println(gList);
		
		model.addAttribute("gList", gList);
		
		return "guestbook/addList";
		
		
	}
	//////////////////////////////////////////////////////////////////////////////////////
	
	//ajax 방명록 메인
	@RequestMapping(value="guest/ajaxindex", method= {RequestMethod.GET, RequestMethod.POST})
	public String ajaxIndex() {
		System.out.println("GuestController.ajaxIndex()");
		
		return "guestbook/ajaxIndex";
	}
}
