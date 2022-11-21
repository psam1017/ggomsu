package com.ggomsu.app.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.ActionForward;
import javax.servlet.RequestDispatcher;

@WebServlet("/UserFrontController")
public class UserFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserFrontController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doProcess(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doProcess(request, response);
    }

    // 작성자 : 박성민
    
    // Ok는 "연산 또는 완료"의 의미를 가진다. 단순 이동은 Ok가 아니다.
    @SuppressWarnings("unused")
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{	
    	String requestURI = req.getRequestURI();
    	String contextPath = req.getContextPath();
    	String command = requestURI.substring(contextPath.length());
    	
    	ActionForward forward = null;

//    	if(command.equals("/user/check-email-ok")) {
//    		try {
//				forward = new UserCheckEmailOk().execute(req, resp);
//			} catch (Exception e) {
//				System.out.println("아이디 중복검사 오류 : " + e);
//			}
//    	}
//		else if(command.equals("/user/user-sign-up")) {
//			forward = new ActionForward();
//			forward.setForward(true);
//			forward.setPath("/app/user/signUp.jsp");
//		}

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
