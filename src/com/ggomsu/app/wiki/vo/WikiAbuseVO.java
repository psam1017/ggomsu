package com.ggomsu.app.wiki.vo;

public class WikiAbuseVO {
	
	private String ip;
	private String blockedAt;
	
	public WikiAbuseVO() { ; }
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getBlockedAt() {
		return blockedAt;
	}
	public void setBlockedAt(String blockedAt) {
		this.blockedAt = blockedAt;
	}
}
