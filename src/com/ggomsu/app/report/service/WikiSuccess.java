package com.ggomsu.app.report.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class WikiSuccess implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		req.setAttribute("subject", session.getAttribute("subject"));
		req.setAttribute("rvs", session.getAttribute("rvs"));
		session.removeAttribute("subject");
		session.removeAttribute("rvs");
		
		forward.setForward(true);
		forward.setPath("/views/report/WikiSuccess.jsp");
		
		return forward;
	}
}
