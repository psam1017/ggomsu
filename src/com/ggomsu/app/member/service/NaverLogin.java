package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;

public class NaverLogin implements Action{

	// 로그인 첫 화면 요청 메소드
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		ActionForward forward = new ActionForward();
		NaverLoginBO naverLoginBO = new NaverLoginBO();
		
		/* 네이버아이디로 인증 URL을 생성하기 위해 naverLoginBO 클래스 getAuthorizationUrl 메소드 호출*/
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		
		req.setAttribute("url", naverAuthUrl);
		
		forward.setForward(true);	
		forward.setPath("/app/member/MemberSignIn.jsp");
		
		return forward;
	}
	

}
