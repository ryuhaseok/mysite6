package com.javaex.vo;

public class GalleryVo {
	
	//
	private int no;
	private int userNo;
	private String content;
	private String orgName;
	private String saveName;
	private String filePath;
	private long fileSize;
	
	//
	public GalleryVo() {
	}

	public GalleryVo(int userNo, String content, String orgName, String saveName, String filePath, long fileSize) {
		this.userNo = userNo;
		this.content = content;
		this.orgName = orgName;
		this.saveName = saveName;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}

	public GalleryVo(int no, int userNo, String content, String orgName, String saveName, String filePath,
			long fileSize) {
		this.no = no;
		this.userNo = userNo;
		this.content = content;
		this.orgName = orgName;
		this.saveName = saveName;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}

	//
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	//
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", userNo=" + userNo + ", content=" + content + ", orgName=" + orgName
				+ ", saveName=" + saveName + ", filePath=" + filePath + ", fileSize=" + fileSize + "]";
	}
	
	

}
