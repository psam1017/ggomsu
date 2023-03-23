package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 박성민

public class SignInBoard implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		String articleRedirect = (String) session.getAttribute("articleRedirect");
		String boardValue = (String) session.getAttribute("boardValue");
		String page = String.valueOf(session.getAttribute("page"));
		
		// '/article/list'에서부터 접근한 경로이기 때문에 boardValue와 page가 없을 수 없다.
		if(articleRedirect.equals("list")) {
			forward.setPath(req.getContextPath() + "/article/list?boardValue=" + boardValue + "&page=" + page);
		}
		else {
			forward.setPath(req.getContextPath() + "/error/error");
		}
		session.removeAttribute("articleRedirect");
		forward.setForward(false);
		
		return forward;
	}
}
