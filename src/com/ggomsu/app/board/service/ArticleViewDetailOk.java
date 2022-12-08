package com.ggomsu.app.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.board.dao.ArticleDAO;
	// 작성자 : 이성호
public class ArticleViewDetailOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		ArticleDAO dao = new ArticleDAO();
		ActionForward forward = new ActionForward();
		
		int index = Integer.parseInt(req.getParameter("index"));
		dao.updateArticleViewCount(index);
		req.setAttribute("article", dao.getArticle(index));
		forward.setForward(true);
		forward.setPath("/app/board/ArticleViewDetail.jsp");

		return forward;
	}
}
