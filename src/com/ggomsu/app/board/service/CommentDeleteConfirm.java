package com.ggomsu.app.board.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.ggomsu.app.board.dao.CommentDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
//작성자: 김지혜
public class CommentDeleteConfirm implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		CommentDAO dao = new CommentDAO();
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		
	    String nickname = (String) req.getSession().getAttribute("nickname");
		int articleIndex = (int) req.getSession().getAttribute("articleIndex");
		int commentIndex = Integer.parseInt(req.getParameter("commentIndex"));
	    
		if(dao.getCommentOne(commentIndex).getNickname().equals(nickname)) {
			dao.deleteCommentByCommentIndex(commentIndex);
		}
		else {
		}
		
	    return null;
	  }
}
