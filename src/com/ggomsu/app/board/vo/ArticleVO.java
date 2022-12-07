package com.ggomsu.app.board.vo;

// 작성자 : 박성민

public class ArticleVO {
	
	private int index;
	private String boardValue;
	private String memberNickname;
	private String title;
	private String content;
	private int viewCount;
	private String writtenAt;
	private String deletedAt;
	private int articleLikeCount;

	public ArticleVO() { ; }
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getBoardValue() {
		return boardValue;
	}
	public void setBoardValue(String boardValue) {
		this.boardValue = boardValue;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public String getWrittenAt() {
		return writtenAt;
	}
	public void setWrittenAt(String writtenAt) {
		this.writtenAt = writtenAt;
	}
	public String getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(String deletedAt) {
		this.deletedAt = deletedAt;
	}
	public int getArticleLikeCount() {
		return articleLikeCount;
	}
	public void setArticleLikeCount(int articleLikeCount) {
		this.articleLikeCount = articleLikeCount;
	}
}
