package com.ggomsu.app.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.ActionForward;
import javax.servlet.RequestDispatcher;

@WebServlet("/UserFrontController")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardFrontController() {
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

//    	if(command.equals("/board/article-list-ok")) {
//    		try {
//				forward = new ArticleListOk().execute(req, resp);
//			} catch (Exception e) {
//				System.out.println("게시글 리스트 오류 : " + e);
//			}
//    	}
//		else if(command.equals("/board/article-write")) {
//			forward = new ActionForward();
//			forward.setForward(true);
//			forward.setPath("/app/board/article-write.jsp");
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
