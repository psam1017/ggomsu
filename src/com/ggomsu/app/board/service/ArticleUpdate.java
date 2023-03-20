package com.ggomsu.app.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.board.vo.ArticleDTO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class ArticleUpdate implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		ActionForward forward = new ActionForward();
		ArticleDAO dao = new ArticleDAO();
		HttpSession session = req.getSession();
		
		String statusValue = (String) session.getAttribute("statusValue");
		String nickname = (String) session.getAttribute("nickname");
		
		if(statusValue == null || !(statusValue.equals("MEM") || statusValue.equals("ADM"))) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/error");
			return forward;
		}
		
		int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
		ArticleDTO article = dao.findArticle(articleIndex);
		
		if(!article.getNickname().equals(nickname)) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/error");
		}
		else {
			req.setAttribute("type", "boardUpdate");
			req.setAttribute("article", article);
			forward.setForward(true);
			forward.setPath("/views/board/ArticleWrite.jsp"); // ArticleWrite 양식과 ArticleUpdate 양식이 완전히 동일하다.
		}
		
		return forward;
	}
}
