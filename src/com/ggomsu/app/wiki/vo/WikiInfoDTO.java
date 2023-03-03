package com.ggomsu.app.wiki.vo;

// 작성자 : 박성민
public class WikiInfoDTO {

	private String subject;
	private int rvs;
	private String nickname;
	private String ip;
	private String revisedAt;
	private String deletedAt;
	private String profileImageUrl;

	public WikiInfoDTO() { ; }

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
	public String getRevisedAt() {
		return revisedAt;
	}
	public void setRevisedAt(String revisedAt) {
		this.revisedAt = revisedAt;
	}
	public String getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(String deletedAt) {
		this.deletedAt = deletedAt;
	}
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
}
