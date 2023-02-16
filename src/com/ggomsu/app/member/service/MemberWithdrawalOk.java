package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = req.getSession();
		
		String email = (String)session.getAttribute("email");
		//세션이메일과 입력받은 이메일이 같을 때 회원탈퇴
		if(email.equals(req.getParameter("email"))) {
			vo.setEmail(req.getParameter("email"));
			dao.withdrawal(vo);
			session.invalidate();
			
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/app/index.jsp");
		}
		else {
			System.out.println("아이디가일치하지않습니다");
			forward.setPath(req.getContextPath() + "/member/member-withdrawal?email=emailFail");
		}
		
		return forward;
		
	}

}
