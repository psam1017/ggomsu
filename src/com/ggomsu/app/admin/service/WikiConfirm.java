package com.ggomsu.app.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.admin.dao.AdminDAO;
import com.ggomsu.app.wiki.dao.WikiDAO;
import com.ggomsu.app.wiki.vo.WikiInfoDTO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class WikiConfirm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		AdminDAO adminDAO = new AdminDAO();
		WikiDAO wikiDAO = new WikiDAO();
		ActionForward forward = new ActionForward();
		
		String subject = req.getParameter("subject");
		int rvs = Integer.parseInt(req.getParameter("rvs"));
		String wikiDeleteReason = req.getParameter("wikiDeleteReason");
		
		if(wikiDeleteReason == null) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/admin/wiki/report?code=error");
		}
		else if(wikiDeleteReason.equals("off")) {
			// 신고 대상에 해당하지 않으므로 신고 건 자체를 삭제
			adminDAO.deleteWikiReport(subject, rvs);	
			
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/admin/wiki/report?code=keep");
		} else {
			WikiInfoDTO info = wikiDAO.findWikiInfo(subject, rvs);
			System.out.println(info.getNickname());
			System.out.println(info.getIp());
			
			// abuse에 nickname 또는 ip를 저장
			wikiDAO.confirmWikiAbuse(info.getNickname(), info.getIp());
			// 삭제 사유를 명시하고 처리 완료함
			adminDAO.confirmReportedWiki(subject, rvs, wikiDeleteReason);
			// 위키를 삭제 처리함
			wikiDAO.confirmWikiDelete(subject, rvs);
			
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/admin/wiki/report?code=delete");
		}
		
		return forward;
	}
}
