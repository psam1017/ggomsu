package com.ggomsu.app.board.vo;

// 작성자 : 박성민

public class AttachmentVO {
	
	private String attachmentName;
	private int articleIndex;
	
	public AttachmentVO() { ; }
	
	public String getAttachmentName() {
		return attachmentName;
	}
	
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public int getArticleIndex() {
		return articleIndex;
	}

	public void setArticleIndex(int articleIndex) {
		this.articleIndex = articleIndex;
	}
}
