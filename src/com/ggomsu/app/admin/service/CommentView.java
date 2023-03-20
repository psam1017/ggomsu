package com.ggomsu.app.admin.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.ggomsu.app.board.dao.CommentDAO;
import com.ggomsu.app.board.vo.CommentDTO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class CommentView implements Action{

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		CommentDAO commentDAO = new CommentDAO();
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		int commentIndex = Integer.parseInt(req.getParameter("commentIndex"));
		CommentDTO comment = commentDAO.getCommentOne(commentIndex);
		
		if(comment.getDeletedAt() == null) {
			json.put("status", "ok");
			json.put("commentIndex", commentIndex);
			json.put("articleIndex", comment.getArticleIndex());
			json.put("nickname", comment.getNickname());
			json.put("writtenAt", comment.getWrittenAt());
			json.put("content", comment.getContent());
		}
		else {
			json.put("status", "not-ok");
		}
		
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}
}
