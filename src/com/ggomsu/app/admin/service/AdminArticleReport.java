package com.ggomsu.app.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.admin.dao.ArticleReportDAO;

public class AdminArticleReport implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
	
		ArticleReportDAO arDao = new ArticleReportDAO();
		ActionForward forward = new ActionForward();
		
		req.setAttribute("articleReportList", arDao.getArticleReportList());
		
		forward.setForward(true);
		forward.setPath("/app/admin/AdminArticleReport.jsp");
		return forward;
	}

}
