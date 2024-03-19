package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	public void insert(GalleryVo galleryVo) {
		System.out.println("GalleryDao.insert()");
		
		sqlSession.insert("gallery.insert", galleryVo);
		
	}
	
	/*
	public List<GalleryVo> selectList() {
		System.out.println("GalleryDao.selectList()");
		
		List<GalleryVo> galleryList = sqlSession.selectList("gallery.selectGalleryList");
		
		return galleryList; 
	}
	*/
}
