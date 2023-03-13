package com.ggomsu.app.my.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 손하늘

public class TermConfirm implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		
		// 잘못된 접근일 때
		if(!req.getMethod().equals("POST")) {
			if(req.getSession().getAttribute("statusValue").equals("MEM")) {
				forward.setPath(req.getContextPath() + "/my/term?code=error");
			}
			else {
				forward.setPath(req.getContextPath() + "/error/error");
			}
			forward.setForward(false);
			
			return forward;
		}
		
		String agreedTermAt = req.getParameter("agreedTermAt");
		String agreedMarketingAt = req.getParameter("agreedMarketingAt");
		
		vo.setEmail((String)req.getSession().getAttribute("email"));
		vo.setAgreedTermAt(agreedTermAt);
		vo.setAgreedMarketingAt(agreedMarketingAt);
		
		dao.updateTerm(vo);
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/my/term?code=success");
		
		return forward;
	}
}
