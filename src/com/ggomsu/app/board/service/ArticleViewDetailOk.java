package com.ggomsu.app.board.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.board.dao.AttachmentDAO;
import com.ggomsu.app.board.dao.CommentDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

	// 작성자 : 이성호
public class ArticleViewDetailOk implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		ArticleDAO dao = new ArticleDAO();
		CommentDAO commentDAO = new CommentDAO();
		AttachmentDAO atDao = new AttachmentDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();

		String blockedString = "'',";
		List<String> blockedList = (List<String>)session.getAttribute("blockedList");
		if(blockedList != null) {
			for(String blockedMember : blockedList) {
				blockedString += ("'" + blockedMember + "',");
			}
		}
		
		blockedString = blockedString.substring(0, blockedString.length()-1);
		int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
//		boardValue가 null이거나 현재 article의 boardValue와 값이 다를 때 생성
		String boardValue = (String)session.getAttribute("boardValue");
		String nickname = (String)session.getAttribute("nickname");
		dao.updateArticleViewCount(articleIndex);
		
		// ### blindList by 박성민, blindList 이름을 맞추어야 함 ###
		List<String> blindList = (List<String>) session.getAttribute("blindList");

		req.setAttribute("article", dao.getArticle(articleIndex));
		req.setAttribute("articleIndex", articleIndex);
		req.setAttribute("attachment", atDao.getAttachment(articleIndex));
		req.setAttribute("commentList", commentDAO.getCommentList(articleIndex, blindList));
		req.setAttribute("isArticleLike", dao.checkGood(nickname, articleIndex));
		
		//Cookie
		Cookie cookie = new Cookie("boardValue", boardValue);
		resp.addCookie(cookie);
		
		
		if(boardValue == null) {
			boardValue = dao.getArticle(articleIndex).getBoardValue();
			session.setAttribute("boardValue", boardValue);
		}
		forward.setForward(true);
		forward.setPath("/app/board/ArticleViewDetailTest.jsp");
		
		
		if((Integer)articleIndex == null) {
			session.setAttribute("articleIndex", articleIndex);	
		}
		
		
		
//		이성호 작업 완료 후 추가해야 할 부분
//		AlarmService alarmService = new AlarmService();
//		alarmService.deleteArticleAlarm(nickname, articleIndex);
//		alarmService.deleteCommentAlarm(nickname, commentList);
		
		return forward;
	}
}
