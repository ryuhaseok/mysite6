package com.javaex.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;


@Controller
public class ApiGuestbookController {
	
	@Autowired
	private GuestService guestService;
	
	@ResponseBody //return의 데이터를 를 json으로 변경해서 응답문서의 body에 붙여 보내기
	@RequestMapping(value="/api/guestbooks", method= {RequestMethod.GET})
	public List<GuestVo> list() {
		System.out.println("ApiGuestbookController.list()");
		
		List<GuestVo> guestbookList = guestService.exeSelectList();
		System.out.println(guestbookList);
		
		return guestbookList;
	}
	

}
