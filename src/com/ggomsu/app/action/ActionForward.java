package com.ggomsu.app.action;

public class ActionForward {
	
	// 작성자 : 박성민
	// path : 다음으로 갈 페이지의 경로
	// forward : 데이터를 다음 페이지에 전달할 게 있다.
	// redirect : 데이터를 다음 페이지에 전달하지 않아도 된다.
	
	private String path;
	private boolean isForward;
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public boolean isForward() {
		return isForward;
	}
	
	public void setForward(boolean isForward) {
		this.isForward = isForward;
	}
}
