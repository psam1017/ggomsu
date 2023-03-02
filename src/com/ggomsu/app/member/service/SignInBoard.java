package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.board.BoardHelper;

//작성자 : 박성민

public class SignInBoard implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		ActionForward forward = new ActionForward();
		BoardHelper boardHelper = new BoardHelper();
		HttpSession session = req.getSession();
		
		boardHelper.setArticleAttrFromSession(req, session);
		
		forward.setPath(req.getContextPath() + "/member/sign-in/board");
		forward.setForward(true);
		return forward;
	}
}
