package com.ggomsu.app.my.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 손하늘

public class Profile implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// profile 목록 : email(조회만 가능), nickname, profile image
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		
		String email = (String)req.getSession().getAttribute("email");
		String profileImageUrl = dao.getMemberInfo(email).getProfileImageUrl();

		req.setAttribute("profileImageUrl", profileImageUrl);
		
		forward.setForward(true);
		forward.setPath("/views/my/Profile.jsp");
		
		return forward;
	}
}
