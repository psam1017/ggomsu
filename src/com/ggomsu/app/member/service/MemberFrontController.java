package com.ggomsu.app.member.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.ActionForward;

// 작성자 : 박성민, 손하늘

@WebServlet("/MemberFrontController")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		ActionForward forward = null;
		
		// 아래의 경로는 src 패키지 경로와는 무관하다. controller를 생략할 수 있다.
		// Ok는 "연산 또는 완료"의 의미를 가진다. 단순 이동이라면 Ok를 붙이지 않는다.
		if(command.equals("/member/member-check-email-ok")) {
			try {
				forward = new MemberCheckEmailOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("아이디 중복검사 오류!" + e);
			}
		}
		else if(command.equals("/member/member-check-nickname-ok")) {
			try {
				forward = new MemberCheckNicknameOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("닉네임 중복검사 오류!" + e);
			}
		}
		else if(command.equals("/member/member-check-contact-ok")) {
			try {
				forward = new MemberCheckContactOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("전화번호 중복검사 오류!" + e);
			}
		}
		else if(command.equals("/member/member-sign-up-ok")) {
			try {
				forward = new MemberSignUpOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("회원가입 오류!" + e);
			}
		}
		else if(command.equals("/member/member-sign-in")) {
			forward = new ActionForward();
			forward.setForward(true);
			forward.setPath("/app/member/MemberSignIn.jsp");
		}
		else if(command.equals("/member/member-sign-in-ok")) {
			try {
				forward = new MemberSignInOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("로그인 오류!" + e);
			}
		}
		else if(command.equals("/member/welcome")) {
			forward = new ActionForward();
			forward.setForward(true);
			forward.setPath("/app/member/MemberSignUpOk.jsp");
		}
		else if(command.equals("/member/member-sign-in-ok")) {
			try {
				forward = new MemberSingInOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("로그인 실패!" + e);
			}
		}
		
		else if(command.equals("/member/member-find-id-Ok")) {
			try {
				forward = new MemberFindIdOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("아이디 찾기 오류!" + e);
			}
		}
		
//		else if(command.equals("/member/member-check-password")) {
//			forward = new ActionForward();
//			forward.setForward(true);
//			forward.setPath("/app/member/MemberCheckPassword.jsp");
//		}
//		else if(command.equals("/member/member-check-password-ok")){
//			try {
//				forward = new MemberCheckPwOk().execute(req, resp);
//			} catch (Exception e) {
//				System.out.println("비밀번호확인 오류!" + e);
//			}
//		}
		
		else if(command.equals("/member/member-logout-ok")) {
			try {
				forward = new MemberLogoutOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("로그아웃 오류!" + e);
			}
		}
		

		else if(command.equals("/member/member-update-block-ok")) {
			try {
				forward = new MemberUpdateBlockOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("회원차단 오류!" + e);
			}
		}
		
		else if(command.equals("/member/member-get-block")) {
			forward = new ActionForward();
			forward.setForward(true);
			forward.setPath("/app/member/MemberBlock.jsp");
		}
		else if(command.equals("/member/member-get-block-ok")) {
			try {
				forward = new MemberGetBlockOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("회원 차단리스트 불러오기 오류!" + e);
			}
		}
		
		else if(command.equals("/member/member-delete-block-ok")) {
			try {
				forward = new MemberDeleteBlockOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("회원 차단해제 오류!" + e);
			}
		}
		
		else if(command.equals("/member/member-view-my-info-ok")) {
			try {
				forward = new MemberViewMyInfoOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("회원정보 불러오기 오류!" + e);
			}
		}
		
		else if(command.equals("/member/member-update-info-ok")) {
			try {
				forward = new MemberUpdataMyInfoOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("회원정보 수정 오류!" + e);
			}
		}
		
		else if(command.equals("/member/member-view-term-ok")) {
			try {
				forward = new MemberViewTermOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("약관정보 불러오기 오류!" + e);
			}
		}
		else if(command.equals("/member/member-update-term-ok")) {
			try {
				forward = new MemberUpdateTermOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("약관동의 업데이트 오류!" + e);
			}
		}
		
		else if(command.equals("/member/member-withdrawal")) {
			forward = new ActionForward();
			forward.setForward(true);
			forward.setPath("/app/member/MemberWithdrawal.jsp");
		}
		else if(command.equals("/member/member-withdrawal-ok")) {
			try {
				forward = new MemberWithdrawalOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("회원탈퇴 오류!" + e);
			}
		}
		if(forward != null) {
			if(forward.isForward()) {
				RequestDispatcher dispatcher = req.getRequestDispatcher(forward.getPath());
				dispatcher.forward(req, resp);
			}
			else {
				resp.sendRedirect(forward.getPath());
			}
		}
	}
}
