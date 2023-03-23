package com.ggomsu.app.board.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.board.dao.BoardDAO;
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
		BoardDAO boardDAO = new BoardDAO();
		ArticleDAO articleDAO = new ArticleDAO();
		BoardHelper boardHelper = new BoardHelper();
		AlarmHelper alarmHelper = new AlarmHelper();
		HttpSession session = req.getSession();
		
		// session
		String statusValue = (String)session.getAttribute("statusValue");
		if(statusValue == null || !(statusValue.equals("MEM") || statusValue.equals("ADM") || statusValue.equals("TMP") || statusValue.equals("SNS"))) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/member/sign-in?code=no-member");
			return forward;
		}
		String nickname = (String)session.getAttribute("nickname");
		boolean alarmFlag = session.getAttribute("alarmFlag") != null ? (boolean) session.getAttribute("alarmFlag") : false;
		
		// request
		int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
		
		// 이전 페이지가 article list일 때만 page를 저장
		String page = null;
		boolean isFromList = false;
		
		String referer = req.getHeader("referer");
		if(referer != null) {
			// article에서 넘어온 것인지 판단하여 referrer의 servletPath로 가공
			int articleURIIndex = referer.indexOf("/article");
			int paramIndex = referer.indexOf("?");
			
			if(articleURIIndex != -1 && paramIndex != -1) {
				String command = referer.substring(articleURIIndex, paramIndex);
				if(command.equals("/article/list")) {
					page = String.valueOf(session.getAttribute("page"));
					isFromList = true;
				}
			}
		}
		
		if(!isFromList) {
			Cookie[] cookies = {new Cookie("criteria", null), new Cookie("category", null), 
					new Cookie("period", null), new Cookie("search", null)};
			for(Cookie c : cookies) {
				c.setMaxAge(0);
				resp.addCookie(c);
			}
		}
		
		ArticleDTO articleDTO = articleDAO.findArticle(articleIndex);
		boardHelper.setTagListForOne(articleDTO);
		String boardValue = articleDTO.getBoardValue();
		String boardText = boardDAO.findBoardText(boardValue);
		
		// 삭제된 게시글, 생성되지 않은 게시글이라면 볼 수 없음
		// 생성되지 않은 게시글 -> error로 보낼 수도 있지만 이번에는 portfolio로 보내기로...
		if(articleDTO.getDeletedAt() != null || articleDTO.getWrittenAt() == null) {
			if(articleDTO.getBoardValue() != null) {
				req.setAttribute("boardValue", articleDTO.getBoardValue());
			}
			else {
				req.setAttribute("boardValue", "portfolio");
			}
			
			forward.setPath(req.getContextPath() + "/article/deleted");
			forward.setForward(false);
		}
		else {
			// 게시글 정보 반환
			req.setAttribute("article", articleDTO);
			req.setAttribute("isArticleLike", articleDAO.checkLiked(nickname, articleIndex));
			
			// 검색 조건 유지
			req.setAttribute("page", page);
			req.setAttribute("boardValue", boardValue);
			req.setAttribute("boardText", boardText);
			
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
		return forward;
	}
}
