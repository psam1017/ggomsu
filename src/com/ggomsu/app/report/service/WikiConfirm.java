package com.ggomsu.app.report.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.report.dao.ReportDAO;
import com.ggomsu.app.report.vo.ArticleReportVO;
import com.ggomsu.app.report.vo.WikiReportVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 손하늘
public class WikiConfirm implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// 자바 객체 생성
		ActionForward forward = new ActionForward();
		ReportDAO dao = new ReportDAO();
		WikiReportVO vo = new WikiReportVO();
		HttpSession session = req.getSession();
		
		// 파라미터 저장
		String nickname = (String) session.getAttribute("nickname");
		String subject = (String) session.getAttribute("subject");
		int rvs = (int) session.getAttribute("rvs");
		String wikiReportReason = req.getParameter("wikiReportReason");
		
		if(subject == null) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/400");
		}
		else {
			// 게시글 신고 제출
			vo.setSubject(subject);
			vo.setRvs(rvs);
			vo.setNickname(nickname);
			vo.setIp(nickname.equals("noname") ? req.getRemoteAddr() : null);
			vo.setWikiReportReason(wikiReportReason);
			dao.replaceWikiReport(vo);
			
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/report/article/success");
		}
		
		return forward;
	}
}
