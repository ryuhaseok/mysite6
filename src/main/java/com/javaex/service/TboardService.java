package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.TboardDao;
import com.javaex.vo.TboardVo;

@Service
public class TboardService {

	@Autowired
	private TboardDao tboardDao;
	
	
	//리스트(검색O,페이징 O)
	public Map<String, Object> exeList3(int crtPage, String keyword){
		System.out.println("TboardService.exeList3()");
		System.out.println(crtPage);
		
		/////////////////////////////////
		//리스트가져오기
		/////////////////////////////////
		
		//한 페이지당 출력 글 갯수
		int listCnt = 10;
		
		//crtPage
		crtPage = (crtPage > 0)? crtPage : (crtPage = 1);
		/*
		if(crtPage > 0) {
			
		}else {
			crtPage = 1;
		}
		*/
		
		//시작row 번호
		//1page- 0,10(1번부터 10개) 2page- 10,10(10번부터 10개)
		//(1-1)10 = 0
		//(2-1)10 = 10
		//(3-1)10 = 20
		
		int startRowNo = (crtPage-1)*listCnt;
		
		//startRowNo, listCnt Map으로 묶는다
		Map<String, Object> limitMap = new HashMap<String, Object>();
		limitMap.put("startRowNo", startRowNo);
		limitMap.put("listCnt", listCnt);
		limitMap.put("keyword", keyword);
		
		//dao에 전달해서 현재 페이지의 리스트 10개를 받는다
		List<TboardVo> boardList = tboardDao.boardSelectList3(limitMap);
		
		////////////////////////////////////////////
		//페이징 계산
		////////////////////////////////////////////
		
		//페이지당 버튼 갯수
		int pageBtncount = 5;
		
		//전체 글 갯수
		int totalCnt = tboardDao.selectTotalCnt3(keyword);
		
		//마지막 버튼 번호
		// 1page- 1, 5
		// 2page- 1, 5 ...
		// 5page- 1, 5
		// 6page- 6, 10 ...
		// 10page-6, 10
		// 11page-11, 15 
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtncount)*pageBtncount;
		//(현재페이지/갯수)*갯수 = 올림(1/5)*5 = 5 -> 0.2올림(1)*5 = 5
		//(현재페이지/갯수)*갯수 = 올림(2/5)*5 = 5 -> 0.4올림(1)*5 = 5 ...
		//(현재페이지/갯수)*갯수 = 올림(6/5)*5 = 5 -> 1.2올림(2)*5 = 10
		
		//시작 버튼 번호
		int startPageBtnNo = (endPageBtnNo - pageBtncount) + 1;
		
		//다음 화살표 유무
		boolean next = false;
		if(listCnt * endPageBtnNo < totalCnt) { // 한 페이지당 글 갯수(10)*마지막버튼번호(5) < 전체글갯수(187)
			next = true;
		} else { //다음화살표가 false 일 때 마지막 번호 정확히 계산
			endPageBtnNo = (int)Math.ceil(totalCnt/(double)listCnt);
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		//5개 map으로 묶어서 controller로 보낸다
		Map<String, Object> pMap = new HashMap<String, Object>();
		
		pMap.put("boardList", boardList);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("prev", prev);
		pMap.put("next", next);
		
		return pMap;
		
	}
	
	
	//리스트(검색X,페이징 O)
	public Map<String, Object> exeList2(int crtPage){
		System.out.println("TboardService.exeList2()");
		System.out.println(crtPage);
		
		//한 페이지당 출력 글 갯수
		int listCnt = 10;
		
		//crtPage
		crtPage = (crtPage > 0)? crtPage : (crtPage = 1);
		/*
		if(crtPage > 0) {
			
		}else {
			crtPage = 1;
		}
		*/
		
		//시작row 번호
		//1page- 0,10(1번부터 10개) 2page- 10,10(10번부터 10개)
		//(1-1)10 = 0
		//(2-1)10 = 10
		//(3-1)10 = 20
		
		int startRowNo = (crtPage-1)*listCnt;
		
		//startRowNo, listCnt Map으로 묶는다
		Map<String, Integer> limitMap = new HashMap<String, Integer>();
		limitMap.put("startRowNo", startRowNo);
		limitMap.put("listCnt", listCnt);
		
		//dao에 전달해서 현재 페이지의 리스트 10개를 받는다
		List<TboardVo> boardList = tboardDao.boardSelectList2(limitMap);
		
		////////////////////////////////////////////
		//페이징 계산
		////////////////////////////////////////////
		
		//페이지당 버튼 갯수
		int pageBtncount = 5;
		
		//전체 글 갯수
		int totalCnt = tboardDao.selectTotalCnt();
		
		//마지막 버튼 번호
		// 1page- 1, 5
		// 2page- 1, 5 ...
		// 5page- 1, 5
		// 6page- 6, 10 ...
		// 10page-6, 10
		// 11page-11, 15 
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtncount)*pageBtncount;
		//(현재페이지/갯수)*갯수 = 올림(1/5)*5 = 5 -> 0.2올림(1)*5 = 5
		//(현재페이지/갯수)*갯수 = 올림(2/5)*5 = 5 -> 0.4올림(1)*5 = 5 ...
		//(현재페이지/갯수)*갯수 = 올림(6/5)*5 = 5 -> 1.2올림(2)*5 = 10
		
		//시작 버튼 번호
		int startPageBtnNo = (endPageBtnNo - pageBtncount) + 1;
		
		//다음 화살표 유무
		boolean next = false;
		if(listCnt * endPageBtnNo < totalCnt) { // 한 페이지당 글 갯수(10)*마지막버튼번호(5) < 전체글갯수(187)
			next = true;
		} else { //다음화살표가 false 일 때 마지막 번호 정확히 계산
			endPageBtnNo = (int)Math.ceil(totalCnt/(double)listCnt);
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		//5개 map으로 묶어서 controller로 보낸다
		Map<String, Object> pMap = new HashMap<String, Object>();
		
		pMap.put("boardList", boardList);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("prev", prev);
		pMap.put("next", next);
		
		return pMap;
		
	}
	
	//리스트(검색X,페이징 X)
	public List<TboardVo> exeList(){
		System.out.println("TboardService.exeList()");
		
		List<TboardVo> boardList = tboardDao.boardSelectList();
		
		return boardList;
		
	}
	
	
}
