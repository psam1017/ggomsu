package com.ggomsu.app.admin.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.board.vo.ArticleDTO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.board.BoardHelper;

// 작성자 : 이성호, 박성민
public class ArticleView implements Action{

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		ArticleDAO articleDAO = new ArticleDAO();
		BoardHelper boardHelper = new BoardHelper();
		PrintWriter out = resp.getWriter();
		
		int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
		ArticleDTO article = articleDAO.findArticle(articleIndex);
		String[] tagArray = boardHelper.setTagListForOne(article);
		array.add(tagArray);
		
		if(article.getDeletedAt() == null) {
			json.put("status", "ok");
			json.put("articleIndex", articleIndex);
			json.put("nickname", article.getNickname());
			json.put("writtenAt", article.getWrittenAt());
			json.put("title", article.getTitle());
			json.put("content", article.getContent());
		}
		else {
			json.put("status", "not-ok");
		}
		
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}
}
