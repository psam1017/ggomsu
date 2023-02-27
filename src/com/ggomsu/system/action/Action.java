package com.ggomsu.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	// 작성자 : 박성민
	// 각 세부 컨트롤러는 Action의 인터페이스를 구현해야 한다.
	
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
