package com.ggomsu.app.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.admin.dao.ArticleReportDAO;
import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.member.dao.MemberDAO;

public class AdminArticleReportOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
	
		ArticleDAO aDao = new ArticleDAO();
		ArticleReportDAO arDao = new ArticleReportDAO();
		MemberDAO mDao = new MemberDAO();
		ActionForward forward = new ActionForward();
		
		String isDelete = req.getParameter("isDelete");
		int articleIndex = 0;
		
		if(isDelete == null) { }
		else if(isDelete.contentEquals("on")) {
			articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
			String reportedNickname = req.getParameter("reportedNickname");
			String articleDeleteReason = req.getParameter("articleDeleteReason");
			
			// 신고 카운트 증가 및 5 이상이면 statusValue sus로 변경
			mDao.updateAbuseCount(reportedNickname);
			// 신고 리포트 삭제
			arDao.updateArticleReport(articleDeleteReason, 1, articleIndex);
			// 게시글 삭제
			aDao.processReportArticle(articleIndex, articleDeleteReason);
			
			System.out.println(articleDeleteReason);
			// reportedNickname에게 신고알림이 가도록	
			
		} else if (isDelete.contentEquals("off")) {
			// 신고 리포트 보존
			arDao.deleteArticleReport(articleIndex);	
		}
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/admin/admin-article-report");
		return forward;
	}

}
