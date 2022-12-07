package com.ggomsu.app.board.vo;

// 작성자 : 박성민

public class CommentVO {
	
	private int index;
	private int reIndex;
	private int articleIndex;
	private String userNickname;
	private String Content;
	private String writtenAt;
	private String deletedAt;
	private int commentLikeCount;
	
	public CommentVO() { ; }
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getReIndex() {
		return reIndex;
	}
	public void setReIndex(int reIndex) {
		this.reIndex = reIndex;
	}
	public int getArticleIndex() {
		return articleIndex;
	}
	public void setArticleIndex(int articleIndex) {
		this.articleIndex = articleIndex;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
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
	public int getCommentLike() {
		return commentLikeCount;
	}
	public void setCommentLike(int commentLikeCount) {
		this.commentLikeCount = commentLikeCount;
	}
}
