package com.ggomsu.app.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.board.service.ArticleDeleteOk;
import com.ggomsu.app.board.service.ArticleGetBestListOk;
import com.ggomsu.app.board.service.ArticleGetListOk;
import com.ggomsu.app.board.service.ArticleGetOrderListOk;
import com.ggomsu.app.board.service.ArticleGetSearchListOk;
import com.ggomsu.app.board.service.ArticleLikeCheckOk;
import com.ggomsu.app.board.service.ArticleViewDetailOk;
import com.ggomsu.app.board.service.ArticleWriteOk;
import com.ggomsu.app.my.service.LikeArticle;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 이성호
@WebServlet("/ArticleController")
public class ArticleController extends HttpServlet {
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
	
		if(command.equals("/board/article-get-list-ok")) {
			try {
				forward = new ArticleGetListOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("게시판 리스트 가져오기 실패!!!" + e);
			}
		}else if(command.equals("/board/article-get-best-list-ok")) {
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
		}else if(command.equals("/board/get-article-write")) {
			try {
				req.setAttribute("type", "boardWrite");
				forward = new ActionForward();
				forward.setForward(true);
				forward.setPath("/app/board/ArticleWriteTest.jsp");
			} catch (Exception e) {
				System.out.println("게시글 페이지 이동실패!!!" + e);
			}
		}else if(command.equals("/board/article-write-ok")) {
			try {
				forward = new ArticleWriteOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("게시글 작성 실패!!!" + e);
			}
		}else if(command.equals("/board/article-like-get-list-ok")) {
			try {
				forward = new LikeArticle().execute(req, resp);
			} catch (Exception e) {
				System.out.println("마에피이지 즐겨찾기리스트 가져오기 실패 !!!" + e);
			}
		}else if(command.equals("/board/article-like-check-ok")) {
			try {
				forward = new ArticleLikeCheckOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("게시글 좋아요(즐겨찾기) 상태확인 실패 !!!" + e);
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
	}
}
