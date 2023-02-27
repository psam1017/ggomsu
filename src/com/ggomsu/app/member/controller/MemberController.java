package com.ggomsu.app.member.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.member.service.*;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.mail.SendMail;

// 작성자 : 박성민, 이성호

@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ActionForward doControl(String command, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ActionForward forward = null;
		
		// 회원가입
		if(command.equals("/member/sign-up")) { forward = new SignUp().execute(req, resp); }
		else if(command.equals("/member/check/email")) { forward = new CheckEmail().execute(req, resp); }
		else if(command.equals("/member/check/nickname")) { forward = new CheckNickname().execute(req, resp); }
		else if(command.equals("/member/check/contact")) { forward = new CheckContact().execute(req, resp); }
		else if(command.equals("/member/check/code")) { forward = new SendMail().execute(req, resp); }
		else if(command.equals("/member/check/auth")) { forward = new CheckAuth().execute(req, resp); }
		else if(command.equals("/member/sign-up/confirm")) { forward = new SignUpConfirm().execute(req, resp); }
		else if(command.equals("/member/welcome")) { forward = new Welcome().execute(req, resp); }
		// 네이버 로그인 API 구현
		else if(command.equals("/member/naver/callback")) { forward = new NaverCallback().execute(req, resp); }
		else if(command.equals("/member/sns/form")) { forward = new SnsNickname().execute(req, resp); }
		else if(command.equals("/member/sns/confirm")) { forward = new SnsConfirm().execute(req, resp); }
		// 로그인, 로그아웃
		else if(command.equals("/member/sign-in")) { forward = new SignIn().execute(req, resp); }
		else if(command.equals("/member/sign-in/confirm")) { forward = new SignInConfirm().execute(req, resp); }
		else if(command.equals("/member/sign-out")) { forward = new SignOut().execute(req, resp); }
		else if(command.equals("/member/password/renew")) { forward = new PasswordRenew().execute(req, resp); }
		else if(command.equals("/member/term/expired")) { forward = new TermExpired().execute(req, resp); }
		else if(command.equals("/member/term/confirm")) { forward = new TermConfirm().execute(req, resp); }
		else if(command.equals("/member/term/cancel")) { forward = new TermCancel().execute(req, resp); }
		else { 
			forward = new ActionForward();
			forward.setAction404(req.getContextPath());
		}
		
		return forward;
	}
	
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
		
		// command 해석 및 예외처리
		try {
			forward = doControl(command, req, resp);
		} catch (SQLException e) {
			forward = new ActionForward();
			forward.setActionBySQLException(req.getContextPath());
		} catch (Exception e) {
			forward = new ActionForward();
			forward.setActionByException(req.getContextPath());
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
