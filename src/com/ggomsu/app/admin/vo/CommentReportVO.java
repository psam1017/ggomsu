package com.ggomsu.app.admin.vo;

public class CommentReportVO {

	private String nickname;
	private int commentIndex;
	private String commentReportReason;
	private String commentDeleteReason;
	private int commentDeleteCheck;
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getCommentIndex() {
		return commentIndex;
	}
	public void setCommentIndex(int commentIndex) {
		this.commentIndex = commentIndex;
	}
	public String getCommentReportReason() {
		return commentReportReason;
	}
	public void setCommentReportReason(String commentReportReason) {
		this.commentReportReason = commentReportReason;
	}
	public String getCommentDeleteReason() {
		return commentDeleteReason;
	}
	public void setCommentDeleteReason(String commentDeleteReason) {
		this.commentDeleteReason = commentDeleteReason;
	}
	public int getCommentDeleteCheck() {
		return commentDeleteCheck;
	}
	public void setCommentDeleteCheck(int commentDeleteCheck) {
		this.commentDeleteCheck = commentDeleteCheck;
	}
}
