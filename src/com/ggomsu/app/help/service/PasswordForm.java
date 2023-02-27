package com.ggomsu.app.help.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 박성민
public class PasswordForm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		if(((String)session.getAttribute("helpPasswordAuth")).equals("success")) {
			forward.setForward(true);
			forward.setPath("/views/help/PasswordForm.jsp");
		}
		else {
			forward.setForward(false);
			forward.setPath(req.getSession() + "/error/error");
		}
		
		return forward;
	}

}
