package com.ggomsu.app.board.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 박성민
public class ArticleUpdateSuccess implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		Cookie[] cookies = req.getCookies();
		
		if(cookies != null && cookies.length > 0) {
			for(Cookie c : cookies) {
				if(c.getName().equals("boardValue")) {
					req.setAttribute("boardValue", c.getValue());
					forward.setForward(true);
					forward.setPath("/views/board/ArticleUpdateSuccess.jsp");
				}
			}
		}
		else {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/error");
		}
		return forward;
	}
}
