package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.AttachVo;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpSession;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao galleryDao;
	
	
	public String exeUpload(MultipartFile file, String content, HttpSession session) {
		System.out.println("GalleryService.exeUpload()");
		
		//파일저장 폴더
		String saveDir = "C:\\javaStudy\\upload";
		
		//0. 파일 정보수집
		//	오리지날 파일명
		String orgName = file.getOriginalFilename();
		System.out.println("orgName: "+orgName);
		
		//	확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("exName: " + exName);
		
		//	저장 파일명(겹치지 않게)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString()+exName;
		System.out.println("saveName: " + saveName);
		
		//	파일 사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize: "+fileSize);
		
		//	파일 전체 경로
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath: "+filePath);
		
		//세션에서 user no값 가져오기
		UserVo userVo = new UserVo();
		
		userVo = (UserVo)session.getAttribute("authUser");
		
		//1. 파일정보를 db에 저장
		//	vo로 묶어주고
		GalleryVo galleryVo = new GalleryVo(userVo.getNo(), content, orgName, saveName, filePath, fileSize);
		
		//	db에 저장
		galleryDao.insert(galleryVo);
		System.out.println("..........DB저장완료");
		
		//2. 파일정보를 하드디스크에 저장
		try {
			byte[] fileData = file.getBytes();
			
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return saveName;
	}//exeUpload
	
	/*
	public List<GalleryVo> exeSelectList() {
		System.out.println("GalleryService.exeSelectList()");
		
		List<GalleryVo> galleryList = galleryDao.selectList();
		
		return galleryList;
	}
	*/

}
