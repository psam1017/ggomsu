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
		String email = (String)session.getAttribute("email");
		String articleIndex = (String)session.getAttribute("articleIndex");
		String boardValue = (String)session.getAttribute("boardValue");
		String page = (String)session.getAttribute("page");
		
		if(agreedTermAt.equals("renew")) {
			session.setAttribute("email", session.getAttribute("disagreedEmail"));
			session.setAttribute("nickname", session.getAttribute("disagreedNickname"));
			session.removeAttribute("disagreedEmail");
			session.removeAttribute("disagreedNickname");
			
			dao.updateAgreedTermAtByEmail(email);
			
			if(articleIndex != null) {
				forward.setPath(req.getContextPath() + "/article/view?articleIndex=" + articleIndex);
			}
			else if(boardValue != null && page != null){
				forward.setPath(req.getContextPath() + "/article/list?boardValue=" + boardValue + "&page=" + page);
			}
			else {
				forward.setPath(req.getContextPath() + "/");
			}
		}
		else {
			session.invalidate();
			forward.setPath(req.getContextPath() + "/member/term/cancel");
		}
		
		forward.setForward(false);
		return forward;
	}
}
