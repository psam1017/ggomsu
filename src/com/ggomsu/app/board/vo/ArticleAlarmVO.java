package com.ggomsu.app.board.vo;

public class ArticleAlarmVO {
	
	private String nickname;
	private int articleIndex;
	private int commentIndex;
	
	public ArticleAlarmVO() { ; }
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
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
	
}
