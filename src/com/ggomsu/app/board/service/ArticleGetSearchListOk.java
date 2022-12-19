package com.ggomsu.app.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.board.dao.BoardDAO;
	// 작성자 : 이성호	
public class ArticleGetSearchListOk implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		ArticleDAO dao = new ArticleDAO();
		BoardDAO bDao = new BoardDAO();
		ActionForward forward = new ActionForward();
		
		String boardValue = req.getParameter("boardValue");
		String search = req.getParameter("search");
		String temp = req.getParameter("page");
		int page = (temp == null) ? 1 : Integer.parseInt(temp);
		int pageSize = 10;
		int totalCount = dao.getSearchTotal("%" + search + "%");
		
		// int endRow = page * pageSize;
		// int startRow = endRow - (pageSize - 1);
		
		int startPage = ((page - 1) / pageSize) * pageSize + 1;
		int endPage = startPage + 9;
		int realEndPage = (int)Math.ceil((double)totalCount/pageSize);
		
		endPage = (endPage > realEndPage) ? realEndPage : endPage;
		
		int prevPage = (int)((page - 10) / 10) * 10 + 1;
		int nextPage = (int)((page + 9) / 10) * 10 + 1;
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("realEndPage", realEndPage);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("nowPage", page);
		req.setAttribute("articleList", dao.getSearchList("%" + search + "%",(page-1)*10));
		req.setAttribute("prevPage", prevPage);
		req.setAttribute("nextPage", nextPage);
		req.setAttribute("sortBy","-search");
		req.setAttribute("boardValue",boardValue);
		req.setAttribute("boardText", bDao.getBoardText(boardValue));
		req.setAttribute("search",search);
		
		forward.setForward(true);
		forward.setPath("/app/board/ArticleViewList.jsp");

		// Session
		HttpSession session = req.getSession();
		session.setAttribute("boardValue", boardValue);
		
		return forward;
	}

}
