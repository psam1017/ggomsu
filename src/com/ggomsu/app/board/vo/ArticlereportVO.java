package com.ggomsu.app.board.vo;

//작성자 : 손하늘

public class ArticlereportVO {
	private int articleIndex;
	private String nickname;
	private String articleReportReason;
	private String articleDeleteReason;
	private int articleDeleteCheck;
	
	public ArticlereportVO() { ; }

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

	public String getArticleReportReason() {
		return articleReportReason;
	}

	public void setArticleReportReason(String articleReportReason) {
		this.articleReportReason = articleReportReason;
	}

	public String getArticleDeleteReason() {
		return articleDeleteReason;
	}

	public void setArticleDeleteReason(String articleDeleteReason) {
		this.articleDeleteReason = articleDeleteReason;
	}

	public int getArticleDeleteCheck() {
		return articleDeleteCheck;
	}

	public void setArticleDeleteCheck(int articleDeleteCheck) {
		this.articleDeleteCheck = articleDeleteCheck;
	}

}
