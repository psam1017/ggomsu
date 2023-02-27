package com.ggomsu.app.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.alarm.service.AlarmService;
import com.ggomsu.app.board.dao.CommentDAO;
import com.ggomsu.app.board.vo.CommentDTO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
//작성자: 김지혜, 박성민
public class CommentWrite implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// java 객체 생성
		ActionForward forward = new ActionForward();
		CommentDAO commentDAO = new CommentDAO();
		CommentDTO myComment = new CommentDTO();
		AlarmService alarmService = new AlarmService();
		HttpSession session = req.getSession();
		
		// parameter 저장
		int refIndex = Integer.parseInt(req.getParameter("refIndex"));
		int articleIndex = (int) session.getAttribute("articleIndex");
		String nickname = (String) session.getAttribute("nickname");
		String content = req.getParameter("content");
		
		// comment 저장
		myComment.setArticleIndex(articleIndex);
		myComment.setNickname(nickname);
		myComment.setContent(content);
		if(refIndex != 0) { // 대댓글
			myComment.setRefIndex(refIndex);
			myComment = commentDAO.doInsertRefCommentProcedure(myComment);
		}
		else { // 댓글
			myComment = commentDAO.doInsertCommentProcedure(myComment);
		}
		
		// alarm 저장
		alarmService.replaceAlarmWithMyComment(myComment);
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/article/view?articleIndex=" + articleIndex);
		return forward;
  }
}