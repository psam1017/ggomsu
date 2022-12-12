package com.ggomsu.app.board.service;

import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.board.dao.BoardDAO;

public class ArticleWrite implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		ArticleDAO dao = new ArticleDAO();
		BoardDAO bDao = new BoardDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		Cookie cookie = null;
		Cookie cookies [] = req.getCookies();
		
		String boardValue = null;
		
		boardValue = (String)session.getAttribute("boardValue");
//		if(cookies != null) {
//			cookie = cookies[0];
//			boardValue = URLDecoder.decode(cookie.getValue(), "utf-8");
//			req.setAttribute("boardValue", boardValue);
//			cookie.setMaxAge(0);
//		}
		String temp = null;
		int page = (temp == null) ? 1 : Integer.parseInt(temp);
		int pageSize = 10;
		int totalCount = dao.getTotal("%" + boardValue);
		int endRow = page * pageSize;
		int startRow = endRow - (pageSize - 1);
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
		req.setAttribute("articleList", dao.getList((page-1)*10,"%"+boardValue));
		req.setAttribute("prevPage", prevPage);
		req.setAttribute("nextPage", nextPage);
		req.setAttribute("boardText", bDao.getBoardText(boardValue));
				
		forward.setForward(true);
		forward.setPath("/app/board/ArticleViewList.jsp");
		
		return forward;
	}
}