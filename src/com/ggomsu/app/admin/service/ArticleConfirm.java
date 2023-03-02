package com.ggomsu.app.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.admin.dao.AdminDAO;
import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class ArticleConfirm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		ArticleDAO articleDAO = new ArticleDAO();
		AdminDAO adminDAO = new AdminDAO();
		MemberDAO memberDAO = new MemberDAO();
		ActionForward forward = new ActionForward();
		
		String isDelete = req.getParameter("isDelete");
		int articleIndex = 0;
		
		if(isDelete == null) { }
		else if(isDelete.contentEquals("on")) {
			articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
			String reportedNickname = req.getParameter("reportedNickname");
			String articleDeleteReason = req.getParameter("articleDeleteReason");
			
			// 신고 카운트 증가 및 5 이상이면 statusValue sus로 변경
			memberDAO.updateAbuseCount(reportedNickname);
			// 신고 리포트 삭제
			adminDAO.updateArticleReport(articleDeleteReason, articleIndex);
			// 게시글 삭제
			articleDAO.processReportArticle(articleIndex, articleDeleteReason);
			
		} else if (isDelete.contentEquals("off")) {
			// 신고 리포트 보존
			adminDAO.deleteArticleReport(articleIndex);	
		}
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/admin/article/success");
		return forward;
	}
}
