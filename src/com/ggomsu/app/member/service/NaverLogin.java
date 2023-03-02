package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class NaverLogin implements Action{

	// 1번
	// 로그인 첫 화면 요청 메소드
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		ActionForward forward = new ActionForward();
		NaverLoginBO naverLoginBO = new NaverLoginBO();
		
		/* 네이버아이디로 인증 URL을 생성하기 위해 naverLoginBO 클래스 getAuthorizationUrl 메소드 호출*/
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session); // 네이버 로그인으로 연결되는 url을 받아와서 req에 담는다.
		
		req.setAttribute("url", naverAuthUrl); // 이 url을 네이버 로그인 버튼의 a.href에 담는다.
		
		forward.setForward(true);	
		forward.setPath("/app/member/MemberSignIn.jsp");
		
		return forward;
	}
	

}
