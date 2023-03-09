package com.ggomsu.app.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 박성민, 이성호
public class ArticleDeleteConfirm implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ArticleDAO articleDAO = new ArticleDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		String nickname = (String)session.getAttribute("nickname");
		int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
		
		// 작성자가 본인이 맞다면 삭제
		if(articleDAO.findArticle(articleIndex).getNickname().equals(nickname)) {
			articleDAO.deleteArticle(articleIndex);
			
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/article/delete/success");
		}
		else {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/error");
		}
		
		return forward;
	}

}
