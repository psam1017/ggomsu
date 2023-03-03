package com.ggomsu.app.report.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.report.dao.ReportDAO;
import com.ggomsu.app.report.vo.ArticleReportVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.board.BoardHelper;

// 작성자 : 손하늘
public class ArticleConfirm implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// 자바 객체 생성
		ActionForward forward = new ActionForward();
		ReportDAO dao = new ReportDAO();
		ArticleReportVO vo = new ArticleReportVO();
		HttpSession session = req.getSession();
		
		// 파라미터 저장
		String nickname = (String) session.getAttribute("nickname");
		int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
		String articleReportReason = req.getParameter("articleReportReason");
		
		// 게시글 신고 제출
		vo.setNickname(nickname);
		vo.setArticleIndex(articleIndex);
		vo.setArticleReportReason(articleReportReason);
		dao.replaceArticleReport(vo);
		
		new BoardHelper().setArticleSessionFromAttr(req, session);
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/report/article/success");
		
		return forward;
	}
}
