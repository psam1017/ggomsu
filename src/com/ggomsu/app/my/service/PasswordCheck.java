package com.ggomsu.app.my.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class PasswordCheck implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		if(req.getSession().getAttribute("statusValue").equals("SNS")) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/member/permit?code=mem");
			return forward;
		}
		else {
			forward.setForward(true);
			forward.setPath(req.getContextPath() + "/views/my/PasswordCheck.jsp");
			return forward;
		}
	}

}
