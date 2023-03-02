package com.ggomsu.app.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 박성민
public class ArticleDeleted implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		if((boolean) session.getAttribute("ArticleDeleted")) {
			req.setAttribute("page", session.getAttribute("page"));
			req.setAttribute("boardValue", session.getAttribute("boardValue"));
			req.setAttribute("criteria", session.getAttribute("criteria"));
			req.setAttribute("category", session.getAttribute("category"));
			req.setAttribute("period", session.getAttribute("period"));
			req.setAttribute("search", session.getAttribute("search"));
			
			session.removeAttribute("ArticleDeleted");
			session.removeAttribute("page");
			session.removeAttribute("boardValue");
			session.removeAttribute("criteria");
			session.removeAttribute("category");
			session.removeAttribute("period");
			session.removeAttribute("search");
			
			forward.setForward(true);
			forward.setPath("/views/board/ArticleDeleted.jsp");
		}
		else {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/error");
		}
		
		return forward;
	}
}
