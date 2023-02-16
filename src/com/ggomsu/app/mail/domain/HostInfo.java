package com.ggomsu.app.mail.domain;

public class HostInfo {
	
	private static String host;
	private static String email;
	private static String password;
	
	// 생성자는 servlet 초기화 시에 1회만 실행
	public HostInfo(String host, String email, String password) {
		HostInfo.host = host;
		HostInfo.email = email;
		HostInfo.password = password;
	}
	
	public static String getHost() {
		return host;
	}
	public static String getEmail() {
		return email;
	}
	public static String getPassword() {
		return password;
	}
}
