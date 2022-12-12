package com.ggomsu.app.board.service;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.ActionForward;
	// 작성자 : 이성호
@SuppressWarnings("serial")
public class BoardFrontController extends HttpServlet {

    public BoardFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		ActionForward forward = null;
	
		if(command.equals("/board/article-get-list-ok")) {
			// 개별 컨트롤러 호출
			try {
				forward = new ArticleGetListOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("게시판 리스트 가져오기 실패!!!" + e);
			}
		}else if(command.equals("/board/article-get-best-list-ok")) {
			// 개별 컨트롤러 호출
			try {
				forward = new ArticleGetBestListOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("베스트 게시판 리스트 가져오기 실패!!!" + e);
			}
		}else if(command.equals("/board/article-get-search-list-ok")) {
			try {
				forward = new ArticleGetSearchListOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("검색 게시판 리스트 가져오기 실패!!!" + e);
			}
		}else if(command.equals("/board/article-view-detail-ok")) {
			try {
				forward = new ArticleViewDetailOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("게시글 상세보기 게시글 가져오기 실패!!!" + e);
			}
		}else if(command.equals("/board/article-get-viewed-order-list-ok")) {
			try {
				forward = new ArticleGetOrderListOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("조회순 게시판 리스트 가져오기 실패!!!" + e);
			}
		}else if(command.equals("/board/article-delete-ok")) {
			try {
				forward = new ArticleDeleteOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("게시글 삭제 실패!!!" + e);
			}
		}else if(command.equals("/board/article-write-ok")) {
			try {
				forward = new ArticleWriteOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("게시글 작성 실패!!!" + e);
			}
		}else if(command.equals("/board/article-write")) {
			try {
				forward = new ArticleWrite().execute(req, resp);
			} catch (Exception e) {
				System.out.println("게시글 작성 후 값 전달 실패!!!" + e);
			}
		}
		
		
		if(forward != null) {
			if(forward.isForward()) {
				RequestDispatcher dispatcher = req.getRequestDispatcher(forward.getPath());
				dispatcher.forward(req, resp);
			} else {
				resp.sendRedirect(forward.getPath());
			}
		}
		
		// jsp 만들어서 결과가 제대로 나오면 끝
		// go to getListFail.jsp
		
	}
}
