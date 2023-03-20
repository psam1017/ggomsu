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
		
		String nickname = (String) req.getSession().getAttribute("nickname");
		String statusValue = (String) req.getSession().getAttribute("statusValue");
		int commentIndex = Integer.parseInt(req.getParameter("commentIndex"));
		
		if(statusValue == null || statusValue.equals("TMP")) {
			json.put("status", "tmp");
		}
		else if(statusValue.equals("MEM") || statusValue.equals("ADM")) {
			if(commentDAO.checkLiked(nickname, commentIndex)) {
				commentDAO.cancelLike(nickname, commentIndex);
				json.put("status", "cancel");
			}
			else {
				commentDAO.doLike(nickname, commentIndex);
				json.put("status", "do");
			}
		}
		else {
			json.put("status", "not-ok");
		}
		
		
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}

}
