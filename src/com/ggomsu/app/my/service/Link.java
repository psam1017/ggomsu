package com.ggomsu.app.my.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.naver.LoginHelper;

//작성자 : 손하늘

public class Link implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		String email = (String) session.getAttribute("email");
		String type = "naver";
		
		req.setAttribute("url", new LoginHelper().getAuthorizationUrl(session));
		req.setAttribute("isSnsKeyExist", new MemberDAO().checkSnsByType(email, type));
		session.setAttribute("linkRedirect", "link");
		
		forward.setForward(true);
		forward.setPath("/views/my/Link.jsp");
		return forward;
	}
}
