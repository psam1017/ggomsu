package com.ggomsu.app.board.vo;

// 작성자 : 박성민
public class CommentDTO {
	
	private int commentIndex;
	private int refIndex;
	private int articleIndex;
	private String nickname;
	private String content;
	private String writtenAt;
	private String deletedAt;
	private String commentDeleteReason;
	private int commentLikeCount;
	private String profileImageUrl;
	
	public CommentDTO() { ; }

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
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getCommentDeleteReason() {
		return commentDeleteReason;
	}
	public void setCommentDeleteReason(String commentDeleteReason) {
		this.commentDeleteReason = commentDeleteReason;
	}
	public int getCommentLikeCount() {
		return commentLikeCount;
	}
	public void setCommentLikeCount(int commentLikeCount) {
		this.commentLikeCount = commentLikeCount;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
}
