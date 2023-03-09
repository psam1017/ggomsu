package com.ggomsu.app.home.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.home.service.*;
import com.ggomsu.app.home.service.Error;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 박성민
@WebServlet("/HomeController")
//@WebServlet(value = {"/", "/dark-mode", "/farewell", "/error/*", "/error"}) (feat. 오원택)
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ActionForward doControl(String command, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = null;
		
		if(command.equals("/main")) { forward = new Main().execute(req, resp); }
		else if(command.equals("/dark-mode")) { forward = new DarkMode().execute(req, resp); }
		else if(command.equals("/farewell")) { forward = new Farewell().execute(req, resp); }
		else if(command.equals("/error/sql")) { forward = new ErrorSQL().execute(req, resp); }
		else if(command.equals("/error/404")) { forward = new Error404().execute(req, resp); }
		else { forward = new Error().execute(req, resp); }
		
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
