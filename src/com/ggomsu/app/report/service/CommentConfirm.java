package com.ggomsu.app.report.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.report.dao.ReportDAO;
import com.ggomsu.app.report.vo.CommentReportVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 손하늘
public class CommentConfirm implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// ajax로 변경 필요
		
		// java 객체 생성
		ReportDAO dao = new ReportDAO();
		CommentReportVO vo = new CommentReportVO();
		HttpSession session = req.getSession();
		
		// 파라미터 저장
		String nickname = (String) session.getAttribute("nickname");
		int commentIndex = Integer.parseInt(req.getParameter("commentIndex"));
		
		// 댓글 신고 제출
		vo.setNickname(nickname);
		vo.setCommentIndex(commentIndex);
		dao.replaceCommentReport(vo);
		
		return null;
	}

}
