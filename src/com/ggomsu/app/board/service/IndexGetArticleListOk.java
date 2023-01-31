package com.ggomsu.app.board.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.board.dao.BoardDAO;

	// 작성자 : 김지혜
public class IndexGetArticleListOk implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		
		ArticleDAO aDao = new ArticleDAO();
		BoardDAO bDao = new BoardDAO();
		
		req.setAttribute("articleLikeList", aDao.getBestLikeList());
		req.setAttribute("articleViewList", aDao.getBestViewList());
		
		HttpSession session = req.getSession();
		
		Cookie[] cookies = req.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(int i = 0; i <cookies.length; i++) {
				if(cookies[i].getName().equals("boardValue")) {
					String boardValue = cookies[i].getValue();
					//System.out.println("controller: "+boardValue);
					req.setAttribute("boardValue", boardValue);
					req.setAttribute("articleBestLikeOne", aDao.getBestLikeOne(boardValue));
					req.setAttribute("articleBestViewOne", aDao.getBestViewOne(boardValue));
				}
				else {String boardValue = null;
					//System.out.println("controller2: "+boardValue);
					req.setAttribute("boardValue", boardValue);}
			}
		}else {String boardValue = null;
			//System.out.println("controller3: "+boardValue);
			req.setAttribute("boardValue", boardValue);}
		
		req.setAttribute("articleBestLikeBoard", aDao.getBestLikeBoard());
		req.setAttribute("articleBestViewBoard", aDao.getBestViewBoard());
		
		forward.setForward(true);
		forward.setPath("/app/index.jsp");
		
		return forward;
	}

}
