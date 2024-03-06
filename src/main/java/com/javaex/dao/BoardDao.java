package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//글 수정
	public void update(BoardVo boardVo) {
		System.out.println("BoardDao.update");
		
		sqlSession.update("board.update", boardVo);
	}
	
	//글 조회수 증가시키기
	public int updateAddHit(int no) {
		System.out.println("BoardDao.updateAddHit");
		
		int count = sqlSession.update("board.updateAddHit");
		
		return count;
	}
	
	//글 읽기
	public BoardVo selectBoardOne(int no) {
		System.out.println("BoardDao.selectBoardOne");
	
		BoardVo boardVo = sqlSession.selectOne("board.selectOne", no);
		
		return boardVo;
	}
	
	//글 삭제
	public int deleteBoard(int no) {
		System.out.println("BoardDao.deleteBoard");
		
		int count = sqlSession.delete("board.delete", no);
		
		return count;
	}
	
	//글 등록
	public int insertBoard(BoardVo boardVo) {
		System.out.println("BoardDao.insertBoard");
		
		int count = sqlSession.insert("board.insert", boardVo);
		
		return count;
	}
	
	//전체리스트 가져오기
	public List<BoardVo> selectList() {
		System.out.println("BoardDao.selectList");
		
		List<BoardVo> bList = sqlSession.selectList("board.selectList");
		
		return bList;
	}
}
