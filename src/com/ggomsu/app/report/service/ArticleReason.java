package com.ggomsu.app.report.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.report.dao.ReportDAO;
import com.ggomsu.app.report.vo.ArticleReportVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 손하늘
public class ArticleReason implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		forward.setForward(true);
		forward.setPath("/views/report/ArticleReason.jsp");
		
		return forward;
	}
}
