package com.ggomsu.app.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.admin.dao.AdminDAO;

public class AdminMemberStatusUpdateOk implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
	
		AdminDAO adDao = new AdminDAO();
		ActionForward forward = new ActionForward();
		
		String memberNickname = (String)req.getParameter("memberNickname");
		String memberStatus = (String)req.getParameter("memberStatus");
		
		adDao.updateMemberStatus(memberNickname, memberStatus);
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/admin/admin-member-block");
		return forward;
	}

}
