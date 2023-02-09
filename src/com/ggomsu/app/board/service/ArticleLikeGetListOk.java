package com.ggomsu.app.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.board.dao.BoardDAO;

public class ArticleLikeGetListOk implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		ArticleDAO dao = new ArticleDAO();
		BoardDAO bDao = new BoardDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		String nickname = (String)session.getAttribute("nickname");
		
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
		
		String boardValue = req.getParameter("boardValue"); // 게시판의 종류를 구별해주는 변수
		String temp = req.getParameter("page");
		int page = (temp == null) ? 1 : Integer.parseInt(temp);
		int pageSize = 10;
		int totalCount = dao.getLikeTotal(nickname, blockedString);
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
		req.setAttribute("articleLikeList", dao.getLikeList(nickname, blockedString,(page-1)*10));
		req.setAttribute("prevPage", prevPage);
		req.setAttribute("nextPage", nextPage);
		req.setAttribute("boardValue", boardValue); 
		req.setAttribute("boardText", bDao.getBoardText(boardValue));
		
		forward.setForward(true);
		forward.setPath("/app/board/ArticleLikeList.jsp");
		
		session.setAttribute("boardValue", boardValue);
		
		return forward;
	}

}
