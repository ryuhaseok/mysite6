package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	
	//글 수정
	public void exeUpdate(BoardVo boardVo) {
		System.out.println("BoardService.exeUpdate");
		
		boardDao.update(boardVo);
	}
	
	//글 조회수 증가시키기
	public int exeAddHit(int no) {
		System.out.println("BoardService.exeAddHit");
		
		int count = boardDao.updateAddHit(no);
		
		return count;
	}
	
	//글 읽기
	public BoardVo exeSelectOne(int no) {
		System.out.println("BoardService.exeSelectOne");
	
		BoardVo boardVo = boardDao.selectBoardOne(no);
		
		return boardVo;
	}
	
	//글 삭제
	public int exeDelete(int no) {
		System.out.println("BoardService.exeDelete");
		System.out.println(no);
		
		int count = boardDao.deleteBoard(no);
		
		return count;
	}
	
	//글 등록
	public int exeWrite(BoardVo bordVo) {
		System.out.println("BoardService.exeWrite");
		
		int count = boardDao.insertBoard(bordVo);
		
		return count;
	}
	
	//전체리스트 가져오기
	public List<BoardVo> exeSelectList() {
		System.out.println("BoardService.exeSelectList");
		
		List<BoardVo> bList = boardDao.selectList();
		
		return bList;
	}

}
