package com.ggomsu.app.alarm.vo;

public class CommentAlarmVO {
	
	private int refIndex; // 원댓글
	private int commentIndex;
	private String nickname; // 원댓글 주인
	
	public CommentAlarmVO() { ; }
	
	public int getRefIndex() {
		return refIndex;
	}
	public void setRefIndex(int refIndex) {
		this.refIndex = refIndex;
	}
	public int getCommentIndex() {
		return commentIndex;
	}
	public void setCommentIndex(int commentIndex) {
		this.commentIndex = commentIndex;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
