package com.ggomsu.app.report.vo;

// 작성자 : 박성민
public class WikiReportVO {
	
	private int wikiReportIndex;
	private String subject;
	private int rvs;
	private String nickname;
	private String ip;
	private String wikiReportReason;
	private String wikiDeleteReason;
	
	public WikiReportVO() { ; }
	
	public int getWikiReportIndex() {
		return wikiReportIndex;
	}
	public void setWikiReportIndex(int wikiReportIndex) {
		this.wikiReportIndex = wikiReportIndex;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getRvs() {
		return rvs;
	}
	public void setRvs(int rvs) {
		this.rvs = rvs;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getWikiReportReason() {
		return wikiReportReason;
	}
	public void setWikiReportReason(String wikiReportReason) {
		this.wikiReportReason = wikiReportReason;
	}
	public String getWikiDeleteReason() {
		return wikiDeleteReason;
	}
	public void setWikiDeleteReason(String wikiDeleteReason) {
		this.wikiDeleteReason = wikiDeleteReason;
	}
}
