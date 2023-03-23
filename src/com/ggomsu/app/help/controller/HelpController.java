package com.ggomsu.app.help.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.help.service.*;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.mail.SendMail;

// 작성자 : 박성민, 손하늘
@WebServlet("/HelpController")
public class HelpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ActionForward doControl(String command, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ActionForward forward = null;
		
		// 이메일 찾기
		if(command.equals("/help/email/find")) { forward = new EmailFind().execute(req, resp); }
		else if(command.equals("/help/email/confirm")) { forward = new EmailConfirm().execute(req, resp); }
		else if(command.equals("/help/email/result")) { forward = new EmailResult().execute(req, resp); }
		// 비밀번호 찾기
		else if(command.equals("/help/password")) { forward = new Password().execute(req, resp); }
		else if(command.equals("/help/password/code")) { forward = new SendMail().execute(req, resp); }
		else if(command.equals("/help/password/auth")) { forward = new PasswordAuth().execute(req, resp); }
		else if(command.equals("/help/password/form")) { forward = new PasswordForm().execute(req, resp); }
		else if(command.equals("/help/password/confirm")) { forward = new PasswordConfirm().execute(req, resp); }
		// 휴면, 탈퇴, 정지 계정
		else if(command.equals("/help/invalid")) { forward = new Invalid().execute(req, resp); }
		else if(command.equals("/help/dormant/auth")) { forward = new SendMail().execute(req, resp); }
		else if(command.equals("/help/dormant/awaken")) { forward = new RestoreInvalid().execute(req, resp); }
		else if(command.equals("/help/withdraw/auth")) { forward = new SendMail().execute(req, resp); }
		else if(command.equals("/help/withdraw/cancel")) { forward = new RestoreInvalid().execute(req, resp); }
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
			e.printStackTrace();
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
