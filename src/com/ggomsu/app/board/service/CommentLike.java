package com.ggomsu.app.board.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.ggomsu.app.board.dao.CommentDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class CommentLike implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		CommentDAO commentDAO = new CommentDAO();
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		String nickname = req.getParameter("nickname");
		int commentIndex = Integer.parseInt(req.getParameter("commentIndex"));
		
		if(commentDAO.checkLiked(nickname, commentIndex)) {
			commentDAO.cancelLike(nickname, commentIndex);
			json.put("like", "do");
		}
		else {
			commentDAO.doLike(nickname, commentIndex);
			json.put("like", "cancel");
		}
		
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}

}
