package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;

//작성자 : 손하늘

public class MemberViewMyInfoOk implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("utf-8");
		
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		String email = (String)session.getAttribute("email");
		
		vo = dao.getMemberInfo(email);
		req.setAttribute("member", vo);
		
		String myPage = req.getParameter("myPage");
		
		if(myPage == null || myPage.equals("myProfile")) { //프로필 편집
			forward.setForward(true);
			forward.setPath("/app/member/MemberUpdateMyProfileOk.jsp");
		}
		else if(myPage.equals("myInfo")) { //회원정보
			forward.setForward(true);
			forward.setPath("/app/member/MemberUpdateMyInfoOk.jsp");
		}
		else if(myPage.equals("myPrivacy")) { //개인정보
			forward.setForward(true);
			forward.setPath("/app/member/MemberUpdateMyPrivacyOk.jsp");
		}
		
//		session.setAttribute(myPage, myPage);
//		forward.setForward(true);
//		forward.setPath("/app/member/MemberViewMyInfoOk.jsp");
		
		return forward;
	}
}
