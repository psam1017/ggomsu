package com.ggomsu.app.board.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.board.dao.AttachmentDAO;
import com.ggomsu.app.board.dao.CommentDAO;

	// 작성자 : 이성호
public class ArticleViewDetailOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		ArticleDAO dao = new ArticleDAO();
		CommentDAO cDao = new CommentDAO();
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
		String boardValue = (String)session.getAttribute("boardValue");
		String nickname = (String)session.getAttribute("nickname");
		dao.updateArticleViewCount(articleIndex);

		req.setAttribute("article", dao.getArticle(articleIndex));
		req.setAttribute("articleIndex", articleIndex);
		req.setAttribute("attachment", atDao.getAttachment(articleIndex));
		req.setAttribute("commentList", cDao.getCommentList(articleIndex, blockedString));
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
		
		return forward;
	}
}
