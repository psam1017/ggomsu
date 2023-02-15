package com.ggomsu.app.member.vo;

//작성자 : 손하늘
public class SettingVO {
	
	private String email;
	private int articleAlarmFlag;
	private int commentAlarmFlag;
	private int darkModeFlag;
	
	public SettingVO() { ; }

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public int getArticleAlarmFlag() {
		return articleAlarmFlag;
	}

	public void setArticleAlarmFlag(int articleAlarmFlag) {
		this.articleAlarmFlag = articleAlarmFlag;
	}

	public int getCommentAlarmFlag() {
		return commentAlarmFlag;
	}

	public void setCommentAlarmFlag(int commentAlarmFlag) {
		this.commentAlarmFlag = commentAlarmFlag;
	}

	public int getDarkModeFlag() {
		return darkModeFlag;
	}

	public void setDarkModeFlag(int darkModeFlag) {
		this.darkModeFlag = darkModeFlag;
	}
	
}
