package com.ggomsu.app.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.board.dao.TagDAO;
import com.ggomsu.app.board.vo.ArticleDTO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.board.BoardHelper;

public class ArticleUpdateConfirm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		TagDAO tagDAO = new TagDAO();
		ArticleDAO articleDAO = new ArticleDAO();
		BoardHelper boardHelper = new BoardHelper();
		ArticleDTO article = new ArticleDTO();
		HttpSession session = req.getSession();
		
		int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
		String tags = req.getParameter("basic");
		String boardValue = req.getParameter("boardValue");
		String nickname = (String)session.getAttribute("nickname");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		if(articleDAO.findArticle(articleIndex).getNickname().equals(nickname)) {
			// 게시글 수정
			article.setArticleIndex(articleIndex);
			article.setBoardValue(boardValue);
			article.setTitle(title);
			article.setContent(content);
			articleDAO.updateArticle(article);
			
			// 태그 수정 -> 모두 지웠다가 다시 삽입
			tagDAO.deleteTags(articleIndex);
			
			if(!tags.equals("")) {
				JSONArray jsonArray = (JSONArray) new JSONParser().parse(tags.toString());
				for(Object tag : jsonArray) {
					JSONObject tagValue = (JSONObject) tag;
					tagDAO.insertTag(articleIndex, (String)tagValue.get("value"));
				}
			}
			
			// boardValue 쿠키 저장
			boardHelper.setBoardCookie(req, resp, boardValue);
			
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/article/list?boardValue=" + boardValue + "&page=1&code=update");
			forward.setPath(req.getContextPath() + "/article/view?articleIndex=" + articleIndex + "&code=update");
		}
		else {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/error");
		}
		return forward;
	}
}
