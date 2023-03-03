package com.ggomsu.app.report.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class WikiReason implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setAttribute("subject", req.getParameter("subject"));
		req.setAttribute("rvs", req.getParameter("rvs"));
		
		ActionForward forward = new ActionForward();
		forward.setForward(true);
		forward.setPath("/views/report/WikiReason.jsp");
		
		return forward;
	}
}
