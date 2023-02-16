package com.ggomsu.app.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.admin.dao.ArticleReportDAO;
import com.ggomsu.app.admin.dao.CommentReportDAO;
import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.member.dao.MemberDAO;

public class AdminCommentReportOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
	
		ArticleDAO aDao = new ArticleDAO();
		CommentReportDAO crDao = new CommentReportDAO();
		MemberDAO mDao = new MemberDAO();
		ActionForward forward = new ActionForward();
		
		String isDelete = req.getParameter("isDelete");
		int commentIndex = 0;
		
		if(isDelete == null) { }
		else if(isDelete.contentEquals("on")) {
			commentIndex = Integer.parseInt(req.getParameter("commentIndex"));
			String reportedNickname = req.getParameter("reportedNickname");
			String commentDeleteReason = req.getParameter("commentDeleteReason");
			
			// 신고 카운트 증가 및 5 이상이면 statusValue sus로 변경
			mDao.updateAbuseCount(reportedNickname);
			// 신고 리포트 삭제
			crDao.updateCommentReport(commentDeleteReason, 1, commentIndex);
			// 게시글 삭제
			aDao.processReportComment(commentIndex, commentDeleteReason);
			
			System.out.println(commentDeleteReason);
			// reportedNickname에게 신고알림이 가도록	
			
		} else if (isDelete.contentEquals("off")) {
			// 신고 리포트 보존
			crDao.deleteCommentReport(commentIndex);	
		}
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/admin/admin-comment-report");
		return forward;
	}

}
