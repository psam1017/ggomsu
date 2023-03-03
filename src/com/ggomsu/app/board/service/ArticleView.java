package com.ggomsu.app.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.board.vo.ArticleDTO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.alarm.AlarmHelper;
import com.ggomsu.system.board.BoardHelper;

// 작성자 : 박성민, 이성호
public class ArticleView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// 자바 객체
		ActionForward forward = new ActionForward();
		ArticleDAO articleDAO = new ArticleDAO();
		BoardHelper boardHelper = new BoardHelper();
		AlarmHelper alarmHelper = new AlarmHelper();
		HttpSession session = req.getSession();
		
		// session
		String nickname = (String)session.getAttribute("nickname");
		boolean alarmFlag = (boolean) session.getAttribute("alarmFlag");
		
		// request
		int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
		String page = (String)req.getParameter("page");
		String criteria = (String)req.getParameter("criteria");
		String category = (String)req.getParameter("category");
		String period = (String)req.getParameter("period");
		String search = (String)req.getParameter("search");
		
		ArticleDTO articleDTO = articleDAO.findArticle(articleIndex);
		boardHelper.setTagListForOne(articleDTO);
		String boardValue = articleDTO.getBoardValue();
		
		if(articleDTO.getDeletedAt() != null) {
			// 게시글 정보 반환
			req.setAttribute("article", articleDTO);
			req.setAttribute("isArticleLike", articleDAO.checkLiked(nickname, articleIndex));
			
			// 검색 조건 유지
			req.setAttribute("page", page);
			req.setAttribute("boardValue", boardValue);
			req.setAttribute("criteria", criteria);
			req.setAttribute("category", category);
			req.setAttribute("period", period);
			req.setAttribute("search", search);
			
			// 쿠키에 boardValue가 없거나 다르다면 저장
			boardHelper.setBoardCookie(req, resp, boardValue);
			
			// 처음 본 게시글이라면 
			// (1) 쿠키에 articleIndex 저장
			// (2) 조회수를 상승시킴
			if(boardHelper.setArticleCookie(req, resp, articleIndex)) {
				articleDAO.updateArticleViewCount(articleIndex);
			}
			
			// 알람 기능을 활성화한 상태이고, 알람에 존재하는 게시글이라면 삭제
			if(alarmFlag) {
				alarmHelper.deleteArticleAlarm(nickname, articleIndex);
			}
			
			forward.setForward(true);
			forward.setPath("/views/board/ArticleView.jsp");
		}
		// 삭제된 게시글이라면 볼 수 없음
		else {
			session.setAttribute("page", page);
			session.setAttribute("boardValue", boardValue);
			session.setAttribute("criteria", criteria);
			session.setAttribute("category", category);
			session.setAttribute("period", period);
			session.setAttribute("search", search);
			
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/article/deleted");
		}
		return forward;
	}
}
