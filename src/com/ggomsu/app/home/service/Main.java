package com.ggomsu.app.home.service;

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

//작성자 : 박성민
public class Main implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		ArticleDAO dao = new ArticleDAO();
		Cookie[] cookies = req.getCookies();
		HttpSession session = req.getSession();
		
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
		
		if(boardValue != null) {
			req.setAttribute("boardText", new BoardDAO().findBoardText(boardValue));
		}
		
		// 내가 최근에 조회한 게시판의 베스트 가져오기
		List<ArticleDTO> yearlyMostLikedList = dao.findYearlyMostLikedList(boardValue);
		List<ArticleDTO> yearlyMostViewedList = dao.findYearlyMostViewedList(boardValue);
		
		req.setAttribute("yearlyMostLikedList", yearlyMostLikedList);
		req.setAttribute("yearlyMostViewedList", yearlyMostViewedList);
		
		forward.setForward(true);
		forward.setPath("/views/Main.jsp");
		return forward;
	}
}
