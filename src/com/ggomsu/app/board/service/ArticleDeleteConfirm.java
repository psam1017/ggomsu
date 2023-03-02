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
			
			// 삭제 성공을 안내하고 request 정보를 들고 있기 위한 임시 session 발급
			session.setAttribute("boardValue", req.getParameter("boardValue"));
			session.setAttribute("page", req.getParameter("page"));
			session.setAttribute("criteria", req.getParameter("criteria"));
			session.setAttribute("category", req.getParameter("category"));
			session.setAttribute("period", req.getParameter("period"));
			session.setAttribute("search", req.getParameter("search"));
			session.setAttribute("ArticleDeleteSuccess", true);
			
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
