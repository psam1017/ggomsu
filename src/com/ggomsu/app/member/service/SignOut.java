package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 손하늘
public class SignOut implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.getSession().invalidate();
		
		ActionForward forward = new ActionForward();
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/");
		return forward;
	}
}
