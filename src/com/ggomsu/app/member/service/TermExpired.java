package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 박성민
public class TermExpired implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		if(session.getAttribute("disagree") == null) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/error");
		}
		else {
			session.setAttribute("disagreedEmail", session.getAttribute("email"));
			session.setAttribute("disagreedNickname", session.getAttribute("nickname"));
			session.setAttribute("disagreedStatusValue", session.getAttribute("statusValue"));
			session.removeAttribute("email");
			session.removeAttribute("nickname");
			session.removeAttribute("statusValue");
			session.removeAttribute("disagree");
			
			forward.setForward(true);
			forward.setPath("/views/member/TermExpired.jsp");
		}
		
		return forward;
	}
}
