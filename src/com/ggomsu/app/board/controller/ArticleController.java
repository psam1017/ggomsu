package com.ggomsu.app.board.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.board.service.*;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 박성민, 이성호
@WebServlet("/ArticleController")
public class ArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ActionForward doControl(String command, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ActionForward forward = null;
		
		// 게시글 리스트 조회 -> 검색, 페이징, 기간, 정렬 통합
		if(command.equals("/article/list")) { forward = new ArticleList().execute(req, resp); }
		// 게시글 내용 CRUD
		else if(command.equals("/article/view")) { forward = new ArticleView().execute(req, resp); }
		else if(command.equals("/article/no-member")) { forward = new ArticleNoMember().execute(req, resp); }
		else if(command.equals("/article/deleted")) { forward = new ArticleDeleted().execute(req, resp); }
		else if(command.equals("/article/delete/confirm")) { forward = new ArticleDeleteConfirm().execute(req, resp); }
		else if(command.equals("/article/delete/success")) { forward = new ArticleDeleteSuccess().execute(req, resp); }
		else if(command.equals("/article/write")) { forward = new ArticleWrite().execute(req, resp); }
		else if(command.equals("/article/write/confirm")) { forward = new ArticleWriteConfirm().execute(req, resp); }
		else if(command.equals("/article/write/success")) { forward = new ArticleWriteSuccess().execute(req, resp); }
		else if(command.equals("/article/update")) { forward = new ArticleUpdate().execute(req, resp); }
		else if(command.equals("/article/update/confirm")) { forward = new ArticleUpdateConfirm().execute(req, resp); }
		else if(command.equals("/article/update/success")) { forward = new ArticleUpdateSuccess().execute(req, resp); }
		// 좋아요 체크
		else if(command.equals("/article/like")) { forward = new ArticleLike().execute(req, resp); }
		else { 
			forward = new ActionForward();
			forward.setAction404(req.getContextPath());
		}
		
		return forward;
	}
	
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
		
		// command 해석 및 예외처리
		try {
			forward = doControl(command, req, resp);
		} catch (SQLException e) {
			forward = new ActionForward();
			forward.setActionBySQLException(req.getContextPath());
			e.printStackTrace();
		} catch (Exception e) {
			forward = new ActionForward();
			forward.setActionByException(req.getContextPath());
			e.printStackTrace();
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
