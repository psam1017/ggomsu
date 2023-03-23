package com.ggomsu.app.my.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 손하늘

public class LinkCancel implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		MemberDAO memberDAO = new MemberDAO();
		
		String email = (String) session.getAttribute("email");
		String type = "naver";
		String statusValue = (String) session.getAttribute("statusValue");
		String path = null;
		
		if(statusValue != null) {
			if(statusValue.equals("ADM")) {
				path = "admin";
			}
			else if(statusValue.equals("MEM")) {
				path = "my";
			}
			
			if(memberDAO.checkSnsByType(email, type)) {
				memberDAO.deleteSnsKeyByType(email, type);
				forward.setPath(req.getContextPath() + "/" + path + "/link?code=cancel");
			}
		}
		
		if(forward.getPath() == null) {
			forward.setPath(req.getContextPath() + "/error/error");
		}
		
		forward.setForward(false);
		return forward;
	}
}
