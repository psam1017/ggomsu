package com.ggomsu.app.my.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 손하늘

public class Personal implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		if(req.getSession().getAttribute("statusValue").equals("SNS")) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/member/permit?code=mem");
			return forward;
		}
		else {
			MemberDAO dao = new MemberDAO();
			
			String email = (String)req.getSession().getAttribute("email");
			req.setAttribute("member", dao.getMemberInfo(email));
			
			forward.setForward(true);
			forward.setPath("/views/my/Personal.jsp");
			return forward;
		}
	}
}
