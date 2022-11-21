package com.ggomsu.app.board.vo;

// 작성자 : 박성민

public class FileVO {
	
	private String fileName;
	private int articleIndex;
	private String fileNameOrigin;
	
	public FileVO() { ; }
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getArticleIndex() {
		return articleIndex;
	}
	public void setArticleIndex(int articleIndex) {
		this.articleIndex = articleIndex;
	}
	public String getFileNameOrigin() {
		return fileNameOrigin;
	}
	public void setFileNameOrigin(String fileNameOrigin) {
		this.fileNameOrigin = fileNameOrigin;
	}
}
