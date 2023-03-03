package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 박성민
public class PasswordRenew implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// password는 변경은 사용자 여부를 묻지 않고 갱신 후 3개월마다 안내
		new MemberDAO().updatePasswordAlertAtByEmail((String)req.getSession().getAttribute("email"));
		
		ActionForward forward = new ActionForward();
		forward.setForward(true);
		forward.setPath("/views/member/PasswordRenew.jsp");
		return forward;
	}
}
