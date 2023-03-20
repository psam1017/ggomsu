package com.ggomsu.app.member.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.naver.LoginHelper;

//작성자 : 박성민
public class SignIn implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		session.removeAttribute("articleForward");
		
		// article 조회 중이었다면 session에 여부를 저장
		// 로그인을 완료하면 다시 조회화면으로 이동
		String referer = req.getHeader("referer");
		
		if(referer != null) {
			int articleURIIndex = referer.indexOf("/article");
			int paramIndex = referer.indexOf("?");
			
			// article에서 넘어온 것인지 판단하여 referrer의 servletPath 형태로 가공
			String command = null;
			if(articleURIIndex != -1 && paramIndex != -1) {
				command = referer.substring(articleURIIndex, paramIndex);
				
				if(command.equals("/article/list")) {
					session.setAttribute("articleForward", "list");
				}
			}
		}
		
		if(req.getSession().getAttribute("email") != null) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/error");
		}
		else {
			// 이메일 저장하기
			Cookie[] cookies = req.getCookies();
			if(cookies != null && cookies.length > 0) {
				
				boolean isRemember = false;
				for(Cookie c : cookies) {
					if(c.getName().equals("rememberEmail")) {
						req.setAttribute("rememberEmail", c.getValue());
						isRemember = true;
					}
				}
				if(isRemember) {
					for(Cookie c : cookies) {
						if(c.getName().equals("email")) {
							req.setAttribute("email", c.getValue());
						}
					}
				}
			}
			
			// 네이버 로그인으로 연결되는 url을 받아와서 req에 담는다.
			LoginHelper loginHelper = new LoginHelper();
			String naverAuthUrl = loginHelper.getAuthorizationUrl(session);
			req.setAttribute("url", naverAuthUrl);
			
			forward.setForward(true);
			forward.setPath("/views/member/SignIn.jsp");
		}
		
		return forward;
	}
}
