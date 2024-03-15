package com.javaex.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;


@Controller
public class ApiGuestbookController {
	
	@Autowired
	private GuestService guestService;
	
	
	//삭제
	@ResponseBody
	@RequestMapping(value="/api/guestbooks/{no}", method= {RequestMethod.DELETE})
	public int delete(@PathVariable("no") int no, @ModelAttribute GuestVo guestVo) {
		System.out.println("ApiGuestbookController.delete()");
		System.out.println(no);
		System.out.println(guestVo);
		
		int count = guestService.exeDeleteGuestbook(guestVo);
		
		return count;
	}
	
	/*
	//삭제2
	@ResponseBody
	@RequestMapping(value="/api/guestbooks/delete", method= {RequestMethod.POST})
	public int delete2(@ModelAttribute GuestVo guestVo) {
		System.out.println("ApiGuestbookController.delete2()");
		int count = guestService.exeDeleteGuestbook(guestVo);
		
		return count;
	}
	*/
	
	//등록
	@ResponseBody
	@RequestMapping(value="/api/guestbooks", method= {RequestMethod.POST})
	public GuestVo add(@RequestBody GuestVo guestVo) {
		System.out.println("ApiGuestbookController.add()");
		
		System.out.println(guestVo);
		GuestVo gVo = guestService.exeAddandGuest(guestVo);
		System.out.println(gVo);
		
		return gVo;
	}
	
	//리스트
	@ResponseBody //return의 데이터를 를 json으로 변경해서 응답문서의 body에 붙여 보내기
	@RequestMapping(value="/api/guestbooks", method= {RequestMethod.GET})
	public List<GuestVo> list() {
		System.out.println("ApiGuestbookController.list()");
		
		List<GuestVo> guestbookList = guestService.exeSelectList();
		
		return guestbookList;
	}
	

}
