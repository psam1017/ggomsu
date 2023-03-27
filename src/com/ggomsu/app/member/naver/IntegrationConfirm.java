package com.ggomsu.app.member.naver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class IntegrationConfirm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		MemberDAO memberDAO = new MemberDAO();
		
		String email = (String) session.getAttribute("snsEmail");
		String snsKey = (String) session.getAttribute("snsKey");
		
		if(email != null) {
			String type= "naver";
			memberDAO.insertSnsKey(email, snsKey, type);
			session.invalidate();
			forward.setPath(req.getContextPath() + "/member/sign-in?code=sns");
		}
		else {
			forward.setPath(req.getContextPath() + "/error/error");
		}
		forward.setForward(false);
		
		return forward;
	}
}
