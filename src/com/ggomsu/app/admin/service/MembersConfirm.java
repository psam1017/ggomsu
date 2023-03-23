package com.ggomsu.app.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.admin.dao.AdminDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class MembersConfirm implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		AdminDAO dao = new AdminDAO();
		ActionForward forward = new ActionForward();
		
		String nickname = (String)req.getParameter("nickname");
		String statusValue = (String)req.getParameter("statusValue");
		
		dao.updateMemberStatus(nickname, statusValue);
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/admin/members?code=success");
		return forward;
	}
}
