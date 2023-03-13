package com.ggomsu.app.help.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 박성민
public class Password implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.getSession().invalidate();
		
		ActionForward forward = new ActionForward();
		forward.setForward(true);
		forward.setPath("/views/help/Password.jsp");
		return forward;
	}
}
