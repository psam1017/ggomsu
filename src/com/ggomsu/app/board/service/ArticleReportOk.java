package com.ggomsu.app.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.board.dao.ArticleReportDAO;
import com.ggomsu.app.board.vo.ArticleReportVO;
import com.sun.javafx.fxml.ParseTraceElement;

// 작성자 : 손하늘
public class ArticleReportOk implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		ArticleReportDAO dao = new ArticleReportDAO();
		ArticleReportVO aVo = new ArticleReportVO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
		
		aVo.setNickname(req.getParameter("articleNickname"));
		aVo.setArticleIndex(articleIndex);
		aVo.setArticleReportReason(req.getParameter("declaration"));
		
		dao.insertArticleReport(aVo);
			
		forward.setForward(true);
		forward.setPath("/board/article-get-list-ok?boardValue=" + session.getAttribute("boardValue"));
		
		return forward;
	}

}
