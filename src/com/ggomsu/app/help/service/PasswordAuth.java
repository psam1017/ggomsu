package com.ggomsu.app.help.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 박성민
public class PasswordAuth implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// 객체
		ActionForward forward = new ActionForward();
		MemberDAO dao = new MemberDAO();
		HttpSession session = req.getSession();
		
		// 파라미터
		String memberKey = req.getParameter("memberKey");
		String authKey = (String)session.getAttribute("authKey");
		String tempEmail = (String)session.getAttribute("tempEmail");

		if(memberKey.equals(authKey) && dao.checkEmail(tempEmail)) {
			session.setAttribute("helpPasswordAuth", "success");
			session.removeAttribute("authKey");
			forward.setPath(req.getContextPath() + "/help/password/form");
		}
		else {
			session.invalidate();
			forward.setPath(req.getContextPath() + "/help/password?code=fail");
		}
		forward.setForward(false);
		
		return forward;
	}
}
