package com.ggomsu.app.report.vo;

// 작성자 : 이성호
public class ArticleReportVO {

	private int articleReportIndex;
	private String nickname;
	private int articleIndex;
	private String articleReportReason;
	private String articleDeleteReason;
	
	public ArticleReportVO() { ; }
	
	public int getArticleReportIndex() {
		return articleReportIndex;
	}
	public void setArticleReportIndex(int articleReportIndex) {
		this.articleReportIndex = articleReportIndex;
	}
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
}
