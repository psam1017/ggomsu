package com.ggomsu.app.board.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.board.dao.ArticleDAO;

public class ArticleLikeCheckOk implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String nickname = req.getParameter("nickname");
		int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
		
		ArticleDAO aDao = new ArticleDAO();
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		if(aDao.checkGood(nickname, articleIndex)) {
			json.put("goodStatus", "ok");
			aDao.DeleteArticleLike(nickname, articleIndex);
		}
		else {
			json.put("goodStatus", "not-ok");
			aDao.InsertArticleLike(nickname, articleIndex);
		}
		
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}

}
