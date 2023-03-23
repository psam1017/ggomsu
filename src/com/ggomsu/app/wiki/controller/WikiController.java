package com.ggomsu.app.wiki.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.wiki.service.Abuse;
import com.ggomsu.app.wiki.service.CheckSubject;
import com.ggomsu.app.wiki.service.Home;
import com.ggomsu.app.wiki.service.NoSubject;
import com.ggomsu.app.wiki.service.NotAvail;
import com.ggomsu.app.wiki.service.Revise;
import com.ggomsu.app.wiki.service.ReviseConfirm;
import com.ggomsu.app.wiki.service.View;
import com.ggomsu.app.wiki.service.WikiList;
import com.ggomsu.app.wiki.service.Write;
import com.ggomsu.app.wiki.service.WriteConfirm;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 박성민
@WebServlet("/WikiController")
public class WikiController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ActionForward doControl(String command, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = null;
		
		if(command.equals("/wiki") || command.equals("/wiki/home")) { forward = new Home().execute(req, resp); }
		else if(command.equals("/wiki/view")) { forward = new View().execute(req, resp); }
		else if(command.equals("/wiki/not-avail")) { forward = new NotAvail().execute(req, resp); }
		else if(command.equals("/wiki/list")) { forward = new WikiList().execute(req, resp); }
		else if(command.equals("/wiki/write")) { forward = new Write().execute(req, resp); }
		else if(command.equals("/wiki/check/subject")) { forward = new CheckSubject().execute(req, resp); }
		else if(command.equals("/wiki/write/confirm")) { forward = new WriteConfirm().execute(req, resp); }
		else if(command.equals("/wiki/revise")) { forward = new Revise().execute(req, resp); }
		else if(command.equals("/wiki/revise/confirm")) { forward = new ReviseConfirm().execute(req, resp); }
		else if(command.equals("/wiki/abuse")) { forward = new Abuse().execute(req, resp); }
		else if(command.equals("/wiki/no-subject")) { forward = new NoSubject().execute(req, resp); }
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
			e.printStackTrace();
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
