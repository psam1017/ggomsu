package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.board.vo.ArticleVO;
import com.ggomsu.app.member.dao.HistoryDAO;

//작성자 : 손하늘

public class MemberViewMyHistoryOk implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ArticleVO aVo = new ArticleVO();
		HistoryDAO dao = new HistoryDAO();
		ActionForward forward = new ActionForward(); 
		HttpSession session = req.getSession();
		
		String nickname = (String)session.getAttribute("nickname");
		req.setAttribute("articleHistory", dao.selectMyHistoryarticle(nickname));
		req.setAttribute("commentHistory", dao.selectMyHistorycomment(nickname));
		
		forward.setForward(true);
		forward.setPath("/app/member/MemberViewMyHistoryOk.jsp");
		return forward;
	}
}
