package com.ggomsu.app.board.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class ArticleLike implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ArticleDAO articleDAO = new ArticleDAO();
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		String nickname = req.getParameter("nickname");
		int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
		
		if(articleDAO.checkLiked(nickname, articleIndex)) {
			articleDAO.cancelLike(nickname, articleIndex);
			json.put("like", "do");
		}
		else {
			articleDAO.doLike(nickname, articleIndex);
			json.put("like", "cancel");
		}
		
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}

}
