package com.ggomsu.app.admin.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.admin.service.*;
import com.ggomsu.app.my.service.*;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 이성호, 박성민
public class AdminController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private ActionForward doControl(String command, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = null;
		
		// admin은 관리자만 사용할 수 있다.
		String statusValue = (String) req.getSession().getAttribute("statusValue");
		if(!statusValue.equals("ADM")) {
			forward = new ActionForward();
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/error");
		}
		
		// depend on my page
		if(command.equals("/admin/profile")) { forward = new Profile().execute(req, resp); }
		else if(command.equals("/admin/profile/confirm")) { forward = new ProfileConfirm().execute(req, resp); }
		else if(command.equals("/admin/personal")) { forward = new Personal().execute(req, resp); }
		else if(command.equals("/admin/personal/confirm")) { forward = new PersonalConfirm().execute(req, resp); }
		else if(command.equals("/admin/password/check")) { forward = new PasswordCheck().execute(req, resp); }
		else if(command.equals("/admin/password/auth")) { forward = new PasswordAuth().execute(req, resp); }
		else if(command.equals("/admin/password/form")) { forward = new PasswordForm().execute(req, resp); }
		else if(command.equals("/admin/password/confirm")) { forward = new PasswordConfirm().execute(req, resp); }
		else if(command.equals("/admin/like/article")) { forward = new LikeArticle().execute(req, resp); }
		else if(command.equals("/admin/like/comment")) { forward = new LikeComment().execute(req, resp); }
		else if(command.equals("/admin/config")) { forward = new Config().execute(req, resp); }
		else if(command.equals("/admin/config/confirm")) { forward = new ConfigConfirm().execute(req, resp); }
		// article process
		else if(command.equals("/admin/article/report")) { forward = new ArticleReport().execute(req, resp); }
		else if(command.equals("/admin/article/confirm")) { forward = new ArticleConfirm().execute(req, resp); }
		else if(command.equals("/admin/article/success")) { forward = new ArticleSuccess().execute(req, resp); }
		else if(command.equals("/admin/article/view")) { forward = new ArticleView().execute(req, resp); }
		// comment porcess
		else if(command.equals("/admin/comment/report")) { forward = new CommentReport().execute(req, resp); }
		else if(command.equals("/admin/comment/confirm")) { forward = new CommentConfirm().execute(req, resp); }
		else if(command.equals("/admin/comment/success")) { forward = new CommentSuccess().execute(req, resp); }
		else if(command.equals("/admin/comment/view")) { forward = new CommentView().execute(req, resp); }
		// wiki process
		else if(command.equals("/admin/wiki/report")) { forward = new WikiReport().execute(req, resp); }
		else if(command.equals("/admin/wiki/confirm")) { forward = new WikiConfirm().execute(req, resp); }
		else if(command.equals("/admin/wiki/success")) { forward = new WikiSuccess().execute(req, resp); }
		else if(command.equals("/admin/wiki/view")) { forward = new WikiView().execute(req, resp); }
		// members process
		else if(command.equals("/admin/members")) { forward = new Members().execute(req, resp); }
		else if(command.equals("/admin/members/confirm")) { forward = new MembersConfirm().execute(req, resp); }
		// add ADM
		else if(command.equals("/admin/add")) { forward = new Add().execute(req, resp); }
		else if(command.equals("/admin/add/confirm")) { forward = new AddConfirm().execute(req, resp); }
		else { 
			forward = new ActionForward();
			forward.setAction404(req.getContextPath());
		}
		
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
}
