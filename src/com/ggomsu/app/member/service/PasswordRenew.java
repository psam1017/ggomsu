package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 박성민
public class PasswordRenew implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		forward.setForward(true);
		forward.setPath("/views/member/PasswordRenew.jsp");
		return forward;
	}
}
