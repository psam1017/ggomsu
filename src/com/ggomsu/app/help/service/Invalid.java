package com.ggomsu.app.help.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 박성민
public class Invalid implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		if(session.getAttribute("invalid") == null) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/error");
		}
		else {
			session.removeAttribute("invalid");
			
			forward.setForward(true);
			forward.setPath("/views/help/Invalid.jsp");
		}
		return forward;
	}
}
