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
		
		String nickname = (String) req.getSession().getAttribute("nickname");
		String statusValue = (String) req.getSession().getAttribute("statusValue");
		int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
		
		if(statusValue == null || statusValue.equals("TMP")) {
			json.put("status", "tmp");
		}
		else if(statusValue.equals("MEM") || statusValue.equals("ADM") || statusValue.equals("SNS")) {
			if(articleDAO.checkLiked(nickname, articleIndex)) {
				articleDAO.cancelLike(nickname, articleIndex);
				json.put("status", "cancel");
				json.put("articleLikeCount", articleDAO.findArticle(articleIndex).getArticleLikeCount());
			}
			else {
				articleDAO.doLike(nickname, articleIndex);
				json.put("status", "do");
				json.put("articleLikeCount", articleDAO.findArticle(articleIndex).getArticleLikeCount());
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
