package com.ggomsu.app.my.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 손하늘

public class Config implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// 조회할 설정 정보 : alarmFlag. darkModeFlag -> 이 두 가지는 세션에 항상 저장
		ActionForward forward = new ActionForward();
		forward.setForward(true);
		forward.setPath("/views/my/Config.jsp");
		
		return forward;
	}
}
