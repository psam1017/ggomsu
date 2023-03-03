package com.ggomsu.app.report.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.board.BoardHelper;

public class ArticleSuccess implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		forward.setForward(true);
		forward.setPath("/views/report/ArticleSuccess.jsp");
		new BoardHelper().setArticleAttrFromSession(req, req.getSession());
		
		return forward;
	}
}
