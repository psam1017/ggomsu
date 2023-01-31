package com.ggomsu.app.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.board.dao.CommentDAO;
import com.ggomsu.app.board.vo.CommentVO;
//작성자: 김지혜
public class CommentDeleteOk implements Action {
	  @Override
		public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	  
	    CommentDAO cDao = new CommentDAO();
	    CommentVO cVo = new CommentVO();
	    ActionForward forward = new ActionForward();
	    
	    int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
	    int commentIndex = Integer.parseInt(req.getParameter("commentIndex"));
	    int refIndex = Integer.parseInt(req.getParameter("refIndex"));
	    
	    cVo.setArticleIndex(articleIndex);
	    cVo.setCommentIndex(commentIndex);
	    cVo.setRefIndex(refIndex);

	    cDao.deleteComment(cVo);

	    req.setCharacterEncoding("utf-8");
	    resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");

	    forward.setForward(false);
		forward.setPath(req.getContextPath() + "/board/article-view-detail-ok?articleIndex=" + articleIndex);
	    return forward;
	    
	  }
}