package com.ggomsu.app.home.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.board.vo.ArticleDTO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 박성민
public class Index implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		ArticleDAO dao = new ArticleDAO();
		Cookie[] cookies = req.getCookies();
		HttpSession session = req.getSession();
		
		// boardValue : session > cookie > null
		String boardValue = null;
		
		if(session.getAttribute("boardValue") != null) {
			boardValue = (String)session.getAttribute("boardValue");
		}
		else if(cookies != null && cookies.length > 0) {
			for(Cookie c : cookies) {
				if(c.getName().equals("boardValue")) {
					boardValue = c.getValue();
				}
			}
		}
		
		// 내가 최근에 조회한 게시판의 베스트 가져오기
		List<ArticleDTO> mostViewedList = dao.findWeeklyMostViewedList(boardValue);
		List<ArticleDTO> mostLikedList = dao.findWeeklyMostLikedList(boardValue);
		ArticleDTO mostViewedArticle = mostViewedList.get(0);
		ArticleDTO mostLikedArticle = mostLikedList.get(0);
		
		req.setAttribute("mostViewedList", mostViewedList);
		req.setAttribute("mostLikedList", mostLikedList);
		req.setAttribute("mostViewedArticle", mostViewedArticle);
		req.setAttribute("mostLikedArticle", mostLikedArticle);
		
		forward.setForward(true);
		forward.setPath("/views/Index.jsp");
		return forward;
	}
}
