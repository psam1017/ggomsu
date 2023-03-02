package com.ggomsu.app.member.service;

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
		boolean isSignInOk = false;
		
		forward.setForward(false);
		vo = dao.getMemberInfo(email);
		String nickname = vo.getEmail();
		String statusValue = vo.getStatusValue();
		
		isSignInOk = encryptionHelper.compare(password, vo.getPassword(), vo.getSalt());
		
		if(!isSignInOk) { // 로그인 실패
			forward.setPath(req.getContextPath() + "/member/sign-in?code=fail");
			return forward;
		}
		else {
			// 게시판 정보를 저장 및 세션에서 삭제
			String articleIndex = (String)session.getAttribute("articleIndex");
			String boardValue = (String)session.getAttribute("boardValue");
			String page = (String)session.getAttribute("page");
			
			session.setAttribute("blindList", dao.getBlindList(nickname));
			session.setAttribute("statusValue", statusValue);
			session.setAttribute("dakrModeFlag", vo.isDarkModeFlag());
			session.setAttribute("alarmFlag", vo.isAlarmFlag());
			
			//회원상태가 MEM, ADM일 때는 필요한 session을 발급
			if(statusValue.equals("MEM") || statusValue.equals("ADM")) {
				session.setAttribute("email", email);
				session.setAttribute("nickname", nickname);
				
				// 로그인 날짜 갱신 -> 휴면계정 조회용
				dao.updateSignAt(email);
				
				// 로그인 후 redirect 우선순위 : 약관 재동의 > 비밀번호 변경 > 이전 페이지
				// 약관을 재동의해야 하는가? 1년
				if(dao.checkTermExpired(email)) {
					session.setAttribute("disagreedEmail", email);
					session.setAttribute("disagreedNickname", nickname);
					session.removeAttribute("email");
					session.removeAttribute("nickname");
					forward.setPath(req.getContextPath() + "/member/term/expired");
				}
				// 비밀번호를 변경해야 하는가? -> 3개월
				else if(dao.checkPasswordRenew(email)) {
					forward.setPath(req.getContextPath() + "/member/password/renew");
				}
				// 이전에 보던 페이지가 있는가?
				else if(articleIndex != null || (boardValue != null && page != null)) {
					forward.setPath(req.getContextPath() + "/member/sign-in/board");
				}
				// TODO else index로 돌아가기
			}
			// 로그인할 수 없는 계정 상태
			else if(statusValue.equals("DEL") || statusValue.equals("SUS") || statusValue.equals("DOR")) {
				session.removeAttribute("email");
				session.removeAttribute("nickname");
				session.setAttribute("invalidEmail", email);
				session.setAttribute("invalidNickname", nickname);
				forward.setPath(req.getContextPath() + "/help/invalid");
			}
		}
		
		if(forward.getPath() == null) {
			forward.setPath(req.getContextPath() + "/error/error");
		}
		
		return forward;
	}
}
