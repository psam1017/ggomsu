package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;

	//작성자 : 손하늘
public class MemberWithdrawalOk implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		
		// getParameter의 안에 String 값은 jsp에서 만든 name의 값과 같아야 한다!
		vo.setEmail(req.getParameter("email"));
		vo.setStatusValue(req.getParameter("statusValue"));
		
		dao.withdrawal(vo);
		
		forward.setForward(false);
		System.out.println(req.getContextPath());
		forward.setPath(req.getContextPath() + "/app/index.jsp");
		
		return forward;
		
	}

}
