package com.ggomsu.app.board.vo;

public class CommentAlarmVO {
	
	private String nickname;
	private int commentIndex;
	private int refIndex;
	
	public CommentAlarmVO() { ; }
	
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
	public int getRefIndex() {
		return refIndex;
	}
	public void setRefIndex(int refIndex) {
		this.refIndex = refIndex;
	}
	
}
