package com.ggomsu.app.wiki.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.ActionForward;

// 작성자 : 박성민

@WebServlet("/WikiFrontController")
public class WikiFrontController extends HttpServlet {
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
		if(command.equals("/wiki/wiki-view-ok")) {
			try {
				forward = new WikiViewOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("위키 화면 조회 오류!" + e);
			}
		}
		else if(command.equals("/wiki/wiki-write-ok")) {
			try {
				forward = new WikiWriteOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("위키 생성 오류!" + e);
			}
		}
		else if(command.equals("/wiki/wiki-check-latest-ok")) {
			try {
				forward = new WikiCheckLatestOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("위키 최신 여부 확인 오류!" + e);
			}
		}
		else if(command.equals("/wiki/wiki-revise")) {
			try {
				forward = new WikiRevise().execute(req, resp);
			} catch (Exception e) {
				System.out.println("위키 개정 페이지 이동 오류!" + e);
			}
		}
		else if(command.equals("/wiki/wiki-revise-ok")) {
			try {
				forward = new WikiReviseOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("위키 개정 완료 오류!" + e);
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
