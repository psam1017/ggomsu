package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 박성민
public class TermConfirm implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		String agreedTermAt = req.getParameter("agreedTermAt");
		String email = (String)session.getAttribute("disagreedEmail");
		String nickname = (String)session.getAttribute("disagreedNickname");
		
		if(agreedTermAt.equals("renew")) {
			session.setAttribute("email", email);
			session.setAttribute("nickname", nickname);
			session.removeAttribute("disagreedEmail");
			session.removeAttribute("disagreedNickname");
			
			dao.updateAgreedTermAtByEmail(email);
			forward.setPath(req.getContextPath() + "/main");
		}
		else {
			session.invalidate();
			forward.setPath(req.getContextPath() + "/member/term/cancel");
		}
		
		forward.setForward(false);
		return forward;
	}
}
