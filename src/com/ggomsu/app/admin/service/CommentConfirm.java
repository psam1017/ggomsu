package com.ggomsu.app.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.admin.dao.AdminDAO;
import com.ggomsu.app.board.dao.CommentDAO;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class CommentConfirm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		CommentDAO commentDAO = new CommentDAO();
		AdminDAO adminDAO = new AdminDAO();
		MemberDAO memberDAO = new MemberDAO();
		ActionForward forward = new ActionForward();
		
		String isDelete = req.getParameter("isDelete");
		int commentIndex = 0;
		
		if(isDelete == null) { }
		else if(isDelete.contentEquals("on")) {
			commentIndex = Integer.parseInt(req.getParameter("commentIndex"));
			String reportedNickname = req.getParameter("reportedNickname");
			String commentDeleteReason = req.getParameter("commentDeleteReason");
			
			// 신고 카운트 증가 및 5 이상이면 statusValue sus로 변경
			memberDAO.updateAbuseCount(reportedNickname);
			// 신고 리포트 삭제
			adminDAO.updateCommentReport(commentDeleteReason, commentIndex);
			// 게시글 삭제
			commentDAO.processReportComment(commentIndex, commentDeleteReason);
			
			System.out.println(commentDeleteReason);
			// reportedNickname에게 신고알림이 가도록	
			
		} else if (isDelete.contentEquals("off")) {
			// 신고 리포트 보존
			adminDAO.deleteCommentReport(commentIndex);	
		}
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/admin/comment/success");
		return forward;
	}

}
