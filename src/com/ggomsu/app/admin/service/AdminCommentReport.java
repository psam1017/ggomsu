package com.ggomsu.app.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.admin.dao.ArticleReportDAO;
import com.ggomsu.app.admin.dao.CommentReportDAO;

public class AdminCommentReport implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
	
		CommentReportDAO crDao = new CommentReportDAO();
		ActionForward forward = new ActionForward();
		
		req.setAttribute("commentReportList", crDao.getCommentReportList());
		
		forward.setForward(true);
		forward.setPath("/app/admin/AdminCommentReport.jsp");
		return forward;
	}

}
