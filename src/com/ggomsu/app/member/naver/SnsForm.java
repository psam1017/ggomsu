package com.ggomsu.app.member.naver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class SnsForm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		forward.setForward(true);	
		forward.setPath("/views/member/SnsForm.jsp");
		String snsNickname = (String) req.getSession().getAttribute("snsNickname");
		req.setAttribute("snsNickname", snsNickname);
		return forward;
	}

}
