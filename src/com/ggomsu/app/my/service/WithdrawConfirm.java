package com.ggomsu.app.my.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 박성민, 손하늘
public class WithdrawConfirm implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		if(!req.getMethod().equals("POST")) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/error");
		}
		
		String email = (String)req.getSession().getAttribute("email");
		dao.withdraw(email);
		session.invalidate();
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/farewell");
		
		return forward;
	}
}
