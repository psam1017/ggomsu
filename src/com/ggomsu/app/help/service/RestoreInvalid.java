package com.ggomsu.app.help.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 박성민, 손하늘
public class RestoreInvalid implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// 객체
		ActionForward forward = new ActionForward();
		MemberDAO dao = new MemberDAO();
		HttpSession session = req.getSession();
		
		// 파라미터
		String memberKey = req.getParameter("memberKey");
		String authKey = (String)session.getAttribute("authKey");
		String invalidEmail = (String)session.getAttribute("invalidEmail");
		String statusValue = (String)session.getAttribute("statusValue");
		
		if(memberKey.equals(authKey) && !statusValue.equals("SUS")) {
			dao.restoreInvalid(invalidEmail);
			session.invalidate();
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/member/sign-in?code=restore");
		}
		else {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/help/invalid?code=fail");
		}
		
		return forward;
	}
}
