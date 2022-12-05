package com.ggomsu.app.board.vo;

// 작성자 : 박성민

public class ArticleVO {
	
	private int index;
	private String boardValue;
	private String userNickname;
	private String title;
	private String content;
	private int viewCount;
	private String writtenAt;
	private String deletedAt;
	private int ArticleLikeCount;

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
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
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
		return ArticleLikeCount;
	}
	public void setArticleLikeCount(int articleLikeCount) {
		ArticleLikeCount = articleLikeCount;
	}
}
