package com.ggomsu.app.report.vo;

// 작성자 : 이성호
public class CommentReportVO {

	private int commentReportIndex;
	private String nickname;
	private int commentIndex;
	private String commentDeleteReason;
	
	public CommentReportVO() { ; }
	
	public int getCommentReportIndex() {
		return commentReportIndex;
	}
	public void setCommentReportIndex(int commentReportIndex) {
		this.commentReportIndex = commentReportIndex;
	}
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
	public String getCommentDeleteReason() {
		return commentDeleteReason;
	}
	public void setCommentDeleteReason(String commentDeleteReason) {
		this.commentDeleteReason = commentDeleteReason;
	}
}
