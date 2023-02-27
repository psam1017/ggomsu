package com.ggomsu.app.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.admin.dao.AdminDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class WikiReport implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		AdminDAO adminDAO = new AdminDAO();
		ActionForward forward = new ActionForward();
		
		req.setAttribute("wikiReportList", adminDAO.findWikiReportList());
		
		forward.setForward(true);
		forward.setPath("/views/admin/WikiReport.jsp");
		return forward;
	}

}
