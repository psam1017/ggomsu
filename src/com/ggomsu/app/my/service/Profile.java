package com.ggomsu.app.my.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 손하늘

public class Profile implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// profile 목록 : nickname, profile image
		ActionForward forward = new ActionForward();
		forward.setForward(true);
		forward.setPath("/views/my/Profile.jsp");
		return forward;
	}
}
