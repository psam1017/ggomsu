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
		
		if(isDelete.contentEquals("on")) {
			articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
			String nickname = req.getParameter("nickname");
			String articleDeleteReason = req.getParameter("articleDeleteReason");
			
			// 신고 카운트 증가 및 5 이상이면 statusValue SUS로 변경
			memberDAO.increaseAbuseCount(nickname);
			// 삭제 사유를 명시하고 처리 완료함
			adminDAO.confirmReportedArticle(articleIndex, articleDeleteReason);
			// 게시글을 삭제 처리함
			articleDAO.confirmArticleDelete(articleIndex, articleDeleteReason);
			
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/admin/article/report?code=success");
		} else if (isDelete.contentEquals("off")) {
			// 신고 대상에 해당하지 않으므로 신고 건 자체를 삭제
			adminDAO.deleteArticleReport(articleIndex);	
			
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/admin/article/report?code=success");
		}
		else {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/admin/article/report?code=fail");
		}
		return forward;
	}
}
