package com.ggomsu.app.my.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.my.service.*;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 박성민, 손하늘
@WebServlet("/MyController")
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ActionForward doControl(String command, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ActionForward forward = null;
		
		// my page는 정상회원만 사용할 수 있다.
		String statusValue = (String) req.getSession().getAttribute("statusValue");
		if(statusValue == null || !statusValue.equals("MEM")) {
			forward = new ActionForward();
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/error?code=no-member");
			return forward;
		}
		
		// 프로필
		if(command.equals("/my/profile")) { forward = new Profile().execute(req, resp); }
		else if(command.equals("/my/profile/confirm")) { forward = new ProfileConfirm().execute(req, resp); }
		// 개인정보
		else if(command.equals("/my/personal")) { forward = new Personal().execute(req, resp); }
		else if(command.equals("/my/personal/confirm")) { forward = new PersonalConfirm().execute(req, resp); }
		// 비밀번호 변경
		else if(command.equals("/my/password/check")) { forward = new PasswordCheck().execute(req, resp); }
		else if(command.equals("/my/password/auth")) { forward = new PasswordAuth().execute(req, resp); }
		else if(command.equals("/my/password/form")) { forward = new PasswordForm().execute(req, resp); }
		else if(command.equals("/my/password/confirm")) { forward = new PasswordConfirm().execute(req, resp); }
		// 최근 활동
		else if(command.equals("/my/like/article")) { forward = new LikeArticle().execute(req, resp); }
		else if(command.equals("/my/like/comment")) { forward = new LikeComment().execute(req, resp); }
		else if(command.equals("/my/history/article")) { forward = new HistoryArticle().execute(req, resp); }
		else if(command.equals("/my/history/comment")) { forward = new HistoryComment().execute(req, resp); }
		// 약관
		else if(command.equals("/my/term")) { forward = new Term().execute(req, resp); }
		else if(command.equals("/my/term/confirm")) { forward = new TermConfirm().execute(req, resp); }
		// 블라인드
		else if(command.equals("/my/blind")) { forward = new Blind().execute(req, resp); }
		else if(command.equals("/my/blind/confirm")) { forward = new BlindConfirm().execute(req, resp); }
		// 설정
		else if(command.equals("/my/config")) { forward = new Config().execute(req, resp); }
		else if(command.equals("/my/config/confirm")) { forward = new ConfigConfirm().execute(req, resp); }
		// 회원탈퇴
		else if(command.equals("/my/withdraw")) { forward = new Withdraw().execute(req, resp); }
		else if(command.equals("/my/withdraw/confirm")) { forward = new WithdrawConfirm().execute(req, resp); }
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
		
		// 요청 command 받기
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
			e.printStackTrace();
		} catch (Exception e) {
			forward = new ActionForward();
			forward.setActionByException(req.getContextPath());
			e.printStackTrace();
		}
		
		// forward or redirect
		if(forward != null) {
			if(forward.isForward()) {
				RequestDispatcher dispatcher = req.getRequestDispatcher(forward.getPath());
				dispatcher.forward(req, resp);
			} else {
				resp.sendRedirect(forward.getPath());
			}
		}
	}
}
