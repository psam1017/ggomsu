package com.ggomsu.app.upload.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.upload.service.UploadSaveBoardImage;
import com.ggomsu.app.upload.service.UploadSaveWikiImage;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 박성민
@WebServlet("/UploadFrontController")
public class UploadController extends HttpServlet {
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
		
		if(command.equals("/upload/board")) {
			try {
				forward = new UploadSaveBoardImage().execute(req, resp);
			} catch (Exception e) {
				System.out.println("위키 화면 조회 오류!" + e);
			}
		}
		else if(command.equals("/upload/wiki")) {
			try {
				forward = new UploadSaveWikiImage().execute(req, resp);
			} catch (Exception e) {
				System.out.println("위키 생성 오류!" + e);
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
