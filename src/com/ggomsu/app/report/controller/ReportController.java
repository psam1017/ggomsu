package com.ggomsu.app.report.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.report.service.ArticleConfirm;
import com.ggomsu.app.report.service.CommentConfirm;
import com.ggomsu.app.report.service.WikiConfirm;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 박성민, 손하늘
@WebServlet("/ReportController")
public class ReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ActionForward doControl(String command, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = null;
		
		if(command.equals("/report/article/confirm")) { forward = new ArticleConfirm().execute(req, resp); }
		else if(command.equals("/report/comment/confirm")) { forward = new CommentConfirm().execute(req, resp); }
		else if(command.equals("/report/wiki/confirm")) { forward = new WikiConfirm().execute(req, resp); }
		else { 
			forward = new ActionForward();
			forward.setAction404(req.getContextPath());
		}
		
		return forward;
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
		} catch (Exception e) {
			forward = new ActionForward();
			forward.setActionByException(req.getContextPath());
		}

		// forward or redirect
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
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
