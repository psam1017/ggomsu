package com.ggomsu.app.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class Add implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		ActionForward forward = new ActionForward();
		forward.setForward(true);
		forward.setPath("/views/admin/Add.jsp");
		
		// session에 발급되는 키의 구체적인 흐름은 SendMail, CheckAuth 참고
		req.getSession().removeAttribute("auth");
		req.getSession().removeAttribute("authKey");
		req.getSession().removeAttribute("tempEmail");
		req.getSession().removeAttribute("authFailCount");
		
		return forward;
	}
}
