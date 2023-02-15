package com.ggomsu.app.board.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.admin.service.AdminArticleReportOk;
import com.ggomsu.app.admin.service.AdminArticleReportGetOk;
	// 작성자 : 이성호(게시판), 김지혜(댓글)
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
				forward = new ArticleLikeGetListOk().execute(req, resp);
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
		//comment
		else if(command.equals("/board/comment-write-ok")) {
			try {
				forward = new CommentWriteOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("댓글 작성 실패!" + e);
			}
		}else if(command.equals("/board/ref-comment-write-ok")) {
			try {
				forward = new RefCommentWriteOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("대댓글 작성 실패!" + e);
			}
		}else if(command.equals("/board/comment-delete-ok")) {
			try {
				forward = new CommentDeleteOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("댓글 삭제 실패!" + e);
			}
		}
		
		//index Article
		else if(command.equals("/board/get-index-article-list-ok")) {
			try {
				forward = new IndexGetArticleListOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("index 게시판 가져오기 실패" + e);
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
