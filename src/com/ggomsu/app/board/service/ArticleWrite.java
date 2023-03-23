package com.ggomsu.app.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class ArticleWrite implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		ActionForward forward = new ActionForward();
		String statusValue = (String) req.getSession().getAttribute("statusValue");
		
		if(statusValue == null || !(statusValue.equals("MEM") || statusValue.equals("ADM") || statusValue.equals("SNS"))) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/member/sign-in?code=no-member");
		}
		else {
			req.setAttribute("type", "boardWrite");
			forward.setForward(true);
			forward.setPath("/views/board/ArticleWrite.jsp");
		}
		return forward;
	}
}
