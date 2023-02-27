package com.ggomsu.app.my.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 박성민
public class LikeArticle implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// java 객체
		ArticleDAO articleDAO = new ArticleDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		// 파라미터 저장
		String nickname = (String)session.getAttribute("nickname");
		String temp = (String)session.getAttribute("page");
		
		// 변수 및 초기화
		int page = (temp == null) ? 1 : Integer.parseInt(temp);
		int pageSize = 10;
		int totalCount = articleDAO.findArticleLikeTotalByNickname(nickname);
		int startPage = ((page - 1) / pageSize) * pageSize + 1;
		int endPage = startPage + 9;
		int realEndPage = (int)Math.ceil((double)totalCount / pageSize);
		
		endPage = (endPage > realEndPage) ? realEndPage : endPage;
		int prevPage = (int)((page - 10) / 10) * 10 + 1;
		int nextPage = (int)((page + 9) / 10) * 10 + 1;
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("realEndPage", realEndPage);
		req.setAttribute("page", page);
		req.setAttribute("articleLikeList", articleDAO.getArticleLikeList(nickname, (page - 1) * 10));
		req.setAttribute("prevPage", prevPage);
		req.setAttribute("nextPage", nextPage);
		session.setAttribute("page", page);
		
		forward.setForward(true);
		forward.setPath("/views/my/LikeArticle.jsp");
		
		return forward;
	}
}

