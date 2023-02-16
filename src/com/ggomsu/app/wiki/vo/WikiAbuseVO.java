package com.ggomsu.app.wiki.vo;

//작성자 : 박성민
//위키는 members 중에서 noname을 참조하고 있음. noname은 다수의 익명이 공동으로 사용할 수 있는 TMP 권한의 계정임.
//따라서 위키와 게시판은 별개로 운영되어야 함.
public class WikiAbuseVO {
	
	private String nickname;
	private String ip;
	private String blockedAt;
	
	public WikiAbuseVO() { ; }
	
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
	public String getBlockedAt() {
		return blockedAt;
	}
	public void setBlockedAt(String blockedAt) {
		this.blockedAt = blockedAt;
	}
}
