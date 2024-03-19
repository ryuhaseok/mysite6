package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	//이미지 업로드
	@RequestMapping(value="gallery/upload", method= {RequestMethod.POST})
	public String imageUpload(@RequestParam(value="file") MultipartFile file, @RequestParam(value="content") String content, HttpSession session, Model model) {
		System.out.println("GalleryController.imageUpload()");
		
		String saveName = galleryService.exeUpload(file, content, session);
		model.addAttribute("saveName", saveName);
		
		return "redirect:gallery/list";
	}
	
	//겔러리홈 + 리스트
	@RequestMapping(value="gallery/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("GalleryController.list()");
		
		//List<GalleryVo> galleryList = galleryService.exeSelectList();
		//model.addAttribute("galleryList", galleryList);
		
		return "gallery/list";
	}

}
