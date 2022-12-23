package com.ggomsu.app.board.service;

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
		
		int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
		dao.updateArticleViewCount(articleIndex);
		
		req.setAttribute("article", dao.getArticle(articleIndex));
		req.setAttribute("articleIndex", articleIndex);
		req.setAttribute("attachment", atDao.getAttachment(articleIndex));
		req.setAttribute("commentList", cDao.getCommentList(articleIndex));
		
		// Session
		HttpSession session = req.getSession();
		String boardValue = (String)session.getAttribute("boardValue");
		String email = (String)session.getAttribute("email");
		
		if( email == null) {
			forward.setForward(false);
			forward.setPath(req.getContextPath()+"/");
		}else if(boardValue == null) {
			boardValue = dao.getArticle(articleIndex).getBoardValue();
			session.setAttribute("boardValue", boardValue);
			forward.setForward(true);
			forward.setPath("/app/board/ArticleViewDetailTest.jsp");
		}else {
			forward.setForward(true);
			forward.setPath("/app/board/ArticleViewDetailTest.jsp");
		}
		
		session.setAttribute("articleIndex", articleIndex);
		return forward;
	}
}
