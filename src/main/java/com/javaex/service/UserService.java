package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	//수정 후 새로 가져오기
	public UserVo exeSelectUserByNo(int no) {
		System.out.println("UserService.exeSelectNoNameByNo()");
		
		UserVo authUser = userDao.selectUserByNo(no);
		
		return authUser;
	}
	
	//회원정보수정
	public int exeUpdateUser(UserVo userVo) {
		System.out.println("UserService.exeUpdateUser()");
		
		int count = userDao.updateUser(userVo);
		
		return count;
	}

	//회원정보수정폼 - 번호로 아이디 가져오기
	public UserVo exeSelectIdByNo(int no) {
		System.out.println("UserService.exeSelectIdByNo()");
		
		UserVo authUser = userDao.selectIdByNo(no);
		
		return authUser;
	}
	
	//로그인
	public UserVo exeLogin(UserVo userVo) {
		System.out.println("UserService.exeLogin()");
		
		UserVo authUser = userDao.selectUserByIdPw(userVo);
		
		return authUser;
	}
	
	//회원가입
	public int exeJoin(UserVo userVo) {
		System.out.println("UserService.exeJoin()");
		
		int count = userDao.insertUser(userVo);
		
		return count;
	}

}
