package com.ggomsu.app.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.board.dao.CommentDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
//작성자: 김지혜
public class CommentDelete implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		CommentDAO dao = new CommentDAO();
		ActionForward forward = new ActionForward();
	    
		int articleIndex = (int) req.getSession().getAttribute("articleIndex");
		int commentIndex = Integer.parseInt(req.getParameter("commentIndex"));
	    
	    dao.deleteCommentByCommentIndex(commentIndex);

	    forward.setForward(false);
		forward.setPath(req.getContextPath() + "/article/view?articleIndex=" + articleIndex);
		
	    return forward;
	  }
}
