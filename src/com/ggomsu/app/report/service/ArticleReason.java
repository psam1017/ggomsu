package com.ggomsu.app.report.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.board.vo.ArticleDTO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 손하늘
public class ArticleReason implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		ArticleDAO articleDAO = new ArticleDAO();
		
		int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
		ArticleDTO articleDTO = articleDAO.findArticle(articleIndex);
		
		req.setAttribute("article", articleDTO);
		req.setAttribute("page", req.getParameter("page"));
		req.setAttribute("boardValue", req.getParameter("boardValue"));
		req.setAttribute("criteria", req.getParameter("criteria"));
		req.setAttribute("category", req.getParameter("category"));
		req.setAttribute("period", req.getParameter("period"));
		req.setAttribute("search", req.getParameter("search"));
		
		forward.setForward(true);
		forward.setPath("/views/report/ArticleReason.jsp");
		
		return forward;
	}
}
