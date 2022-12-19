package com.ggomsu.app.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
		dao.updateArticleViewCount(articleIndex);
		
	
		req.setAttribute("article", dao.getArticle(articleIndex));
		req.setAttribute("articleIndex", articleIndex);

		forward.setForward(true);
		forward.setPath("/app/board/ArticleViewDetailTest.jsp");
		
		// Session
		HttpSession session = req.getSession();
		String boardValue = (String)session.getAttribute("boardValue");
		if(boardValue == null) {
			boardValue = dao.getArticle(articleIndex).getBoardValue();
			session.setAttribute("boardValue", boardValue);
		}
		session.setAttribute("articleIndex", articleIndex);
		return forward;
	}
}
