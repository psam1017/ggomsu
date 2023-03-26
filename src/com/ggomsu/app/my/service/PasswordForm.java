package com.ggomsu.app.my.service;

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
		
		if(((String)session.getAttribute("myPasswordAuth")).equals("success")) {
			forward.setForward(true);
			forward.setPath("/views/my/PasswordForm.jsp");
		}
		else {
			forward.setForward(false);
			forward.setPath(req.getSession() + "/member/sign-in?code=no-member");
		}
		
		return forward;
	}

}
