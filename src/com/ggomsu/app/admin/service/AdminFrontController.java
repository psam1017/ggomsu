package com.ggomsu.app.admin.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.board.service.ArticleGetListOk;
import com.ggomsu.app.member.service.MemberWithdrawalOk;

// 작성자 : 이성호
public class AdminFrontController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	
	public AdminFrontController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		ActionForward forward = null;
	
		if(command.equals("/admin/admin-article-report-ok")) {
			try {
				forward = new AdminArticleReportOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("게시글 신고관리 처리 및 이동 실패 !!!" + e);
			}
		}else if(command.equals("/admin/admin-article-report")) {
			try {
				forward = new AdminArticleReport().execute(req, resp);
			} catch (Exception e) {
				System.out.println("게시글 신고 관리페이지 가져오기 실패 !!!" + e);
			}
		}else if(command.equals("/admin/admin-article-report-get-ok")) {
			try {
				forward = new AdminArticleReportGetOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("게시글 신고글 가져오기 실패 !!!" + e);
			}
		}else if(command.equals("/admin/admin-comment-report-ok")) {
			try {
				forward = new AdminCommentReportOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("댓글 신고관리 처리 및 이동 실패 !!!" + e);
			}
		}else if(command.equals("/admin/admin-comment-report")) {
			try {
				forward = new AdminCommentReport().execute(req, resp);
			} catch (Exception e) {
				System.out.println("댓글 신고 관리페이지 가져오기 실패 !!!" + e);
			}
		}else if(command.equals("/admin/admin-comment-report-get-ok")) {
			try {
				forward = new AdminCommentReportGetOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("댓글 신고글 가져오기 실패 !!!" + e);
			}
		}else if(command.equals("/admin/admin-member-block")) {
			try {
				forward = new AdminMemberBlockOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("관리자 멤버 목록 페이지 이동 실패!!!" + e);
			}
		}else if(command.equals("/admin/admin-member-status-update-ok")) {
			try {
				forward = new AdminMemberStatusUpdateOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println("관리자 멤버 상태 변경 실패 !" + e);
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
