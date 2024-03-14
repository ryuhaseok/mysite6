package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//ajax 등록
	public int insertSelectKey(GuestVo guestVo) {
		System.out.println("GuestDao.insertSelectKey()");
		
		int count = sqlSession.insert("guest.insertSelectKey", guestVo);
		
		return count;
	}
	
	//ajax 데이터 1개 가져오기(no로 데이터 가져옴)
	public GuestVo guestBookselectOne(int no) {
		System.out.println("GuestDao.guestBookselectOne()");
		
		GuestVo guestVo = sqlSession.selectOne("guest.selectOne", no);
		
		return guestVo;
	}
	
	
	//방명록 삭제
	public int deleteGuestbook(GuestVo guestVo) {
		System.out.println("GuestDao.deleteGuestbook()");
		
		int count = sqlSession.delete("guest.deleteGuestbook", guestVo);
		
		return count;
	}
	
	//방명록 등록
	public int insertGuestbook(GuestVo guestVo) {
		System.out.println("GuestDao.insertGuestbook()");
		
		int count = sqlSession.insert("guest.insertGuestbook", guestVo);
		
		return count;
	}
	
	//전체리스트 가져오기
	public List<GuestVo> selectList() {
		System.out.println("GuestDao.selectList()");
		
		List<GuestVo> gList = sqlSession.selectList("guest.selectGuestList");
		
		return gList;
	}

}
