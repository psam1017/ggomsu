package com.ggomsu.app.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.board.dao.BoardDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
	// 작성자 : 이성호
public class ArticleGetOrderListOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		ArticleDAO dao = new ArticleDAO();
		BoardDAO bDao = new BoardDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();

		String blockedString = "'',";
		try {
			List<String> blockedList = (List<String>)session.getAttribute("blockedList");
			for(String blockedMember : blockedList) {
				blockedString += ("'" + blockedMember + "',");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		blockedString = blockedString.substring(0, blockedString.length()-1);
		
		String boardValue = req.getParameter("boardValue");
		String temp = req.getParameter("page");
		int page = (temp == null) ? 1 : Integer.parseInt(temp);
		int pageSize = 10;
		int totalCount = dao.getTotal("%" + boardValue, blockedString);
		
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
		req.setAttribute("page", page);
		req.setAttribute("articleList", dao.getViewedOrderList((page - 1) * 10, "%" + boardValue, blockedString));
		req.setAttribute("prevPage", prevPage);
		req.setAttribute("nextPage", nextPage);
		req.setAttribute("boardValue", boardValue); 
		req.setAttribute("boardText", bDao.getBoardText(boardValue));
		req.setAttribute("sortBy","-viewed-order");
		
		forward.setForward(true);
		forward.setPath("/app/board/ArticleViewList.jsp");
		
		session.setAttribute("boardValue", boardValue);
		
		return forward;
	}
}
