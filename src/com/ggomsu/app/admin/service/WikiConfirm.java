package com.ggomsu.app.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.admin.dao.AdminDAO;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.wiki.dao.WikiDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class WikiConfirm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		AdminDAO adminDAO = new AdminDAO();
		WikiDAO wikiDAO = new WikiDAO();
		MemberDAO memberDAO = new MemberDAO();
		ActionForward forward = new ActionForward();
		
		String isDelete = req.getParameter("isDelete");
		String subject = null;
		int rvs = 0;
		
		if(isDelete.contentEquals("on")) {
			subject = req.getParameter("subject");
			rvs = Integer.parseInt(req.getParameter("rvs"));
			String wikiDeleteReason = req.getParameter("wikiDeleteReason");
			String nickname = req.getParameter("nickname");
			String ip = req.getParameter("ip");
			
			// abuse에 nickname 또는 ip를 저장
			wikiDAO.confirmWikiAbuse(nickname, ip);
			// 삭제 사유를 명시하고 처리 완료함
			adminDAO.confirmReportedWiki(subject, rvs, wikiDeleteReason);
			// 게시글을 삭제 처리함
			wikiDAO.confirmWikiDelete(subject, rvs);
			
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/admin/wiki/report?code=success");
		} else if (isDelete.contentEquals("off")) {
			// 신고 대상에 해당하지 않으므로 신고 건 자체를 삭제
			adminDAO.deleteWikiReport(subject, rvs);	
			
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/admin/wiki/report?code=success");
		}
		else {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/admin/wiki/report?code=fail");
		}
		return forward;
	}
}
