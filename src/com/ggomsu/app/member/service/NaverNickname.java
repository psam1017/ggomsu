package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;

public class NaverNickname implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		session.setAttribute("memberSnsVo", req.getAttribute("memberSnsVo"));
		session.setAttribute("memberVo", req.getAttribute("memberVo"));
		
		forward.setForward(true);	
		forward.setPath("/app/member/NaverLoginNickname.jsp");
		return forward;
	}

}
