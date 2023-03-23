package com.ggomsu.app.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.admin.dao.AdminDAO;
import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 박성민
public class ArticleConfirm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		ArticleDAO articleDAO = new ArticleDAO();
		AdminDAO adminDAO = new AdminDAO();
		MemberDAO memberDAO = new MemberDAO();
		ActionForward forward = new ActionForward();
		
		int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
		String nickname = articleDAO.findArticle(articleIndex).getNickname(); // 신고 받은 게시글의 주인
		String articleDeleteReason = req.getParameter("articleDeleteReason"); // admin이 제출하는 삭제 처리 사유
		
		if (articleDeleteReason == null) {
			forward.setPath(req.getContextPath() + "/admin/article/report?code=error");
		}
		else if(articleDeleteReason.equals("off")) {
			// 신고 대상에 해당하지 않으므로 신고 건 자체를 삭제
			adminDAO.deleteArticleReport(articleIndex);	
			forward.setPath(req.getContextPath() + "/admin/article/report?code=keep");
		} 
		else{
			// 신고 카운트 증가 및 5 이상이면 statusValue SUS로 변경
			memberDAO.increaseAbuseCount(nickname);
			// 삭제 사유를 명시하고 처리 완료함
			adminDAO.confirmReportedArticle(articleIndex, articleDeleteReason);
			// 게시글을 삭제 처리함
			articleDAO.confirmArticleDelete(articleIndex, articleDeleteReason);
			
			forward.setPath(req.getContextPath() + "/admin/article/report?code=delete");
		}
		forward.setForward(false);
		
		return forward;
	}
}
