package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.board.BoardHelper;

//작성자 : 박성민

public class SignIn implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		new BoardHelper().setArticleSessionFromAttr(req, req.getSession());
		
		ActionForward forward = new ActionForward();
		forward.setForward(true);
		forward.setPath("/views/member/SignIn.jsp");
		
		return forward;
	}
}
