package com.javaex.vo;

public class BoardVo {
	
	private int no;
	private String title;
	private String writer;
	private String content;
	private int hit;
	private String regDate;
	
	
	public BoardVo() {
	}
	
	public BoardVo(int no, String writer) {
		this.no = no;
		this.writer = writer;
	}
	
	public BoardVo(int no, String title,String content) {
		this.no = no;
		this.title = title;
		this.content = content;
	}
	
	public BoardVo(String title, String writer, String content) {
		this.title = title;
		this.writer = writer;
		this.content = content;
	}
	
	public BoardVo(int no, String title, String writer, int hit, String regDate) {
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.hit = hit;
		this.regDate = regDate;
	}
	
	public BoardVo(int no, String title, String writer, String content, int hit, String regDate) {
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.hit = hit;
		this.regDate = regDate;
	}
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", writer=" + writer + ", content=" + content + ", hit=" + hit
				+ ", regDate=" + regDate + "]";
	}
	
}
