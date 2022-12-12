package com.ggomsu.app.member.service;

// 작성자 : 박성민
public class MemberEncryptInfo {

	// password와 salt를 DB에 저장할 수 있도록 정보를 담고 있는 객체.
	private String password = "";
	private String salt = "";
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(byte[] password) {
		
		for(byte b : password) {
			this.password += b;
		}
	}
	
	public String getSalt() {
		return salt;
	}
	
	public void setSalt(byte[] salt) {
		
		for(byte b : salt) {
			this.salt += b;
		}
	}
}
