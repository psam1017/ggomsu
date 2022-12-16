package com.ggomsu.app.board.vo;

// 작성자 : 박성민

public class CommentVO {
	
	private int commentIndex;
	private int refIndex;
	private int articleIndex;
	private String nickname;
	private String Content;
	private String writtenAt;
	private String deletedAt;
	private int commentLikeCount;
	
	public CommentVO() { ; }

	public int getCommentIndex() {
		return commentIndex;
	}
	public void setCommentIndex(int commentIndex) {
		this.commentIndex = commentIndex;
	}
	public int getRefIndex() {
		return refIndex;
	}
	public void setRefIndex(int refIndex) {
		this.refIndex = refIndex;
	}
	public int getArticleIndex() {
		return articleIndex;
	}
	public void setArticleIndex(int articleIndex) {
		this.articleIndex = articleIndex;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public int getCommentLikeCount() {
		return commentLikeCount;
	}
	public void setCommentLikeCount(int commentLikeCount) {
		this.commentLikeCount = commentLikeCount;
	}
}
