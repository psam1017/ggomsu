package com.ggomsu.app.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.board.BoardHelper;

// 작성자 : 박성민
public class ArticleDeleted implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		forward.setForward(true);
		forward.setPath("/views/board/ArticleDeleted.jsp");
		
		req.setAttribute("boardValue", new BoardHelper().getValueFromCookie(req, "boardValue"));
		
		return forward;
	}
}
