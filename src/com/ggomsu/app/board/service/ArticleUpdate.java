package com.ggomsu.app.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class ArticleUpdate implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		ActionForward forward = new ActionForward();
		ArticleDAO dao = new ArticleDAO();
		String statusValue = (String) req.getSession().getAttribute("statusValue");
		
		if(!(statusValue.equals("MEM") || statusValue.equals("ADM"))) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/error");
			return forward;
		}
		
		int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
		
		req.setAttribute("type", "boardUpdate");
		req.setAttribute("article", dao.findArticle(articleIndex));
		
		forward.setForward(true);
		forward.setPath("/views/board/ArticleUpdate.jsp");
		return forward;
	}
}
