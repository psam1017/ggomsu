package com.ggomsu.app.board.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.board.dao.BoardDAO;
import com.ggomsu.app.board.vo.ArticleDTO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.board.BoardHelper;

// 작성자 : 박성민, 이성호
public class ArticleList implements Action{

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// 자바 객체 생성
		ArticleDAO articleDAO = new ArticleDAO();
		BoardDAO boardDAO = new BoardDAO();
		ActionForward forward = new ActionForward();
		BoardHelper boardHelper = new BoardHelper();
		List<ArticleDTO> articleList;
		HttpSession session = req.getSession();
		Cookie[] cookies = req.getCookies();
		
		// session, request 저장
		List<String> blindList = (List<String>)session.getAttribute("blindList");
		String temp = req.getParameter("page");
		String criteria = req.getParameter("criteria");
		String category = req.getParameter("category");
		String period = req.getParameter("period");
		String search = req.getParameter("search");
		String boardValue = null;
		
		if(req.getParameter("boardValue") != null) {
			boardValue = req.getParameter("boardValue");
		}
		else if(cookies != null && cookies.length > 0) {
			for(Cookie c : cookies) {
				if(c.getName().equals("boardValue")) {
					boardValue = c.getValue();
				}
			}
		}
		if(boardValue == null) {
			boardValue = "free";
		}
		
		// 페이징 처리 시작
		int page = (temp == null) ? 1 : Integer.parseInt(temp);
		int pageSize = 10;
		int totalCount = articleDAO.findTotal(boardValue, blindList, search, category, period);
		int startPage = ((page - 1) / pageSize) * pageSize + 1;
		int endPage = startPage + 9;
		int realEndPage = (int)Math.ceil((double)totalCount/pageSize);
		int prevPage = (int)((page - 10) / 10) * 10 + 1;
		int nextPage = (int)((page + 9) / 10) * 10 + 1;
		
		endPage = (endPage > realEndPage) ? realEndPage : endPage;
		articleList = articleDAO.findList(boardValue, blindList, search, category, period, criteria, (page - 1) * 10);
		
		// tag 배열화
		boardHelper.setTagListForList(articleList);
		
		// 페이징을 위한 속성
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("articleList", articleList);
		req.setAttribute("prevPage", prevPage);
		req.setAttribute("nextPage", nextPage);
		req.setAttribute("boardText", boardDAO.findBoardText(boardValue));
		// 검색 조건을 유지하기 위한 속성
		req.setAttribute("page", page);
		req.setAttribute("boardValue", boardValue);
		req.setAttribute("criteria", criteria);
		req.setAttribute("category", category);
		req.setAttribute("period", period);
		req.setAttribute("search", search);
		// 페이징 처리 끝
		
		// 쿠키에 boardValue가 없거나 다르다면 쿠키 추가
		boardHelper.setBoardCookie(req, resp, boardValue);
		
		forward.setForward(true);
		forward.setPath("/views/board/ArticleList.jsp");
		return forward;
	}
}
