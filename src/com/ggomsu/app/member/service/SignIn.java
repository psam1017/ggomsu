package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 박성민

public class SignIn implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		session.setAttribute("articleIndex", req.getParameter("articleIndex"));
		session.setAttribute("boardValue", req.getParameter("boardValue"));
		session.setAttribute("page", req.getParameter("page"));
		session.setAttribute("criteria", req.getParameter("criteria"));
		session.setAttribute("category", req.getParameter("category"));
		session.setAttribute("period", req.getParameter("period"));
		session.setAttribute("search", req.getParameter("search"));
		
		forward.setForward(true);
		forward.setPath("/views/member/SignIn.jsp");
		
		return forward;
	}
}
