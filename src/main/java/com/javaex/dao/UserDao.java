package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//수정 후 새로 가져오기
	public UserVo selectUserByNo(int no) {
		System.out.println("UserDao.selectUserByNo");
		
		UserVo authUser = sqlSession.selectOne("user.selectUserByNo", no);
		
		return authUser;
	}
	
	//수정
	public int updateUser(UserVo userVo) {
		System.out.println("UserDao.updateUser");
		
		int count = sqlSession.update("user.updateUser", userVo);
		
		return count;
	}//
	
	//회원정보수정폼 - 번호로 아이디 가져오기
	public UserVo selectIdByNo(int no) {
		System.out.println("UserDao.selectIdByNo");
		
		UserVo authUser = sqlSession.selectOne("user.selectIdByNo", no);
		
		return authUser;
	}//
	
	//로그인
	public UserVo selectUserByIdPw(UserVo userVo) {
		System.out.println("UserDao.selectUserByIdPw");
		
		UserVo authUser = sqlSession.selectOne("user.selectByIdPw", userVo);
		
		return authUser;
	}//
	
	//회원가입
	public int insertUser(UserVo userVo) {
		System.out.println("UserDao.insertUser");
		
		System.out.println(userVo);
		int count = sqlSession.insert("user.insertUser", userVo);
		
		return count;
	}//

}
