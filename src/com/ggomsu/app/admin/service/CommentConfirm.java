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
		
		if(isDelete.contentEquals("on")) {
			commentIndex = Integer.parseInt(req.getParameter("commentIndex"));
			String nickname = req.getParameter("nickname");
			String commentDeleteReason = req.getParameter("commentDeleteReason");
			
			// 신고 카운트 증가 및 5 이상이면 statusValue SUS로 변경
			memberDAO.increaseAbuseCount(nickname);
			// 삭제 사유를 명시하고 처리 완료함
			adminDAO.confirmReportedComment(commentIndex, commentDeleteReason);
			// 댓글을 삭제 처리함
			commentDAO.confirmCommentDelete(commentIndex, commentDeleteReason);
			
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/admin/comment/report?code=success");
		} else if (isDelete.contentEquals("off")) {
			// 신고 대상에 해당하지 않으므로 신고 건 자체를 삭제
			adminDAO.deleteCommentReport(commentIndex);	
			
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/admin/comment/report?code=success");
		}
		else {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/admin/comment/report?code=fail");
		}
		
		return forward;
	}

}
