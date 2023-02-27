package com.ggomsu.app.alarm.vo;

public class ArticleAlarmVO {
	
	private int articleIndex; // 게시글 번호
	private int commentIndex;
	private String nickname; // 게시글 주인
	
	public ArticleAlarmVO() { ; }
	
	public int getArticleIndex() {
		return articleIndex;
	}
	public void setArticleIndex(int articleIndex) {
		this.articleIndex = articleIndex;
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
