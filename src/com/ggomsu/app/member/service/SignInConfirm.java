package com.ggomsu.app.member.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.encrypt.EncryptionHelper;

// 작성자 : 박성민, 손하늘
public class SignInConfirm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		MemberVO vo;
		MemberDAO dao = new MemberDAO();
		EncryptionHelper encryptionHelper = new EncryptionHelper();
		HttpSession session = req.getSession();
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String rememberEmail = req.getParameter("rememberEmail");
		boolean isSignInOk = false;
		boolean isArticleRedirect = false;
		
		if(rememberEmail.equals("on")) {
			Cookie emailCookie = new Cookie("email", email);
			Cookie rememberEmailCookie = new Cookie("rememberEmail", "on");
			emailCookie.setMaxAge(60 * 60 * 24 * 30);
			rememberEmailCookie.setMaxAge(60 * 60 * 24 * 30);
			resp.addCookie(emailCookie);
			resp.addCookie(rememberEmailCookie);
		}
		else {
			Cookie emailCookie = new Cookie("email", email);
			Cookie rememberEmailCookie = new Cookie("rememberEmail", "on");
			emailCookie.setMaxAge(0);
			rememberEmailCookie.setMaxAge(0);
			resp.addCookie(emailCookie);
			resp.addCookie(rememberEmailCookie);
		}
		
		if(!dao.checkEmail(email)) {
			forward.setPath(req.getContextPath() + "/member/sign-in?code=fail");
			return forward;
		}
		
		vo = dao.getMemberInfo(email);
		String nickname = vo.getNickname();
		String statusValue = vo.getStatusValue();
		
		isSignInOk = encryptionHelper.compare(password, vo.getPassword(), vo.getSalt());
		
		if(!isSignInOk) {
			forward.setPath(req.getContextPath() + "/member/sign-in?code=fail");
			return forward;
		}
		else {
			String articleRedirect = (String) session.getAttribute("articleRedirect");
			session.setAttribute("blindList", dao.getBlindList(nickname));
			session.setAttribute("statusValue", statusValue);
			session.setAttribute("darkModeFlag", vo.isDarkModeFlag());
			session.setAttribute("alarmFlag", vo.isAlarmFlag());
			session.setAttribute("profileImageUrl", vo.getProfileImageUrl());
			
			//회원상태가 MEM, ADM일 때는 필요한 session을 발급
			if(statusValue.equals("MEM") || statusValue.equals("ADM")) {
				session.setAttribute("email", email);
				session.setAttribute("nickname", nickname);
				
				// 로그인 날짜 갱신 -> 휴면계정 조회용
				dao.updateSignAt(email);
				
				// 로그인 후 redirect 우선순위 : 약관 재동의 > 비밀번호 변경 > 이전 페이지
				// 약관을 재동의해야 하는가? 1년
				if(dao.checkTermExpired(email)) {
					session.setAttribute("disagree", "true");
					forward.setPath(req.getContextPath() + "/member/term/expired");
				}
				// 비밀번호를 변경해야 하는가? -> 3개월
				else if(dao.checkPasswordRenew(email)) {
					forward.setPath(req.getContextPath() + "/member/password/renew");
				}
				// 이전에 보던 페이지가 있는가?
				else if(articleRedirect != null) {
					isArticleRedirect = true;
					forward.setPath(req.getContextPath() + "/member/sign-in/board");
				}
				else {
					forward.setPath(req.getContextPath() + "/main");
				}
			}
			// 로그인할 수 없는 계정 상태
			else if(statusValue.equals("DEL") || statusValue.equals("SUS") || statusValue.equals("DOR")) {
				session.setAttribute("invalidEmail", email);
				session.setAttribute("invalidNickname", nickname);
				session.setAttribute("originalStatusValue", "MEM"); // ADM은 이곳에 도달할 수 없다.
				session.setAttribute("invalid", "true");
				forward.setPath(req.getContextPath() + "/help/invalid");
			}
		}
		
		if(forward.getPath() == null) {
			forward.setPath(req.getContextPath() + "/error/error");
		}
		if(!isArticleRedirect) {
			session.removeAttribute("articleRedirect");
		}
		forward.setForward(false);
		
		return forward;
	}
}
