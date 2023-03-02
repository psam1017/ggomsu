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

public class ArticleWriteConfirm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		ActionForward forward = new ActionForward();
		TagDAO tagDAO = new TagDAO();
		ArticleDAO articleDAO = new ArticleDAO();
		BoardHelper boardHelper = new BoardHelper();
		ArticleDTO article = new ArticleDTO();
		HttpSession session = req.getSession();

		String tags = req.getParameter("tags");
		String boardValue = req.getParameter("boardValue");
		String nickname = (String)session.getAttribute("nickname");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String statusValue = (String)session.getAttribute("statusValue");
		
		// 공지사항은 관리자가 아니면 입력해서는 안 된다. 한 번 더 점검
		if(boardValue.equals("notice") && !statusValue.equals("ADM")) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/error");
			return forward;
		}
		
		// 게시글 저장
		article.setBoardValue(boardValue);
		article.setNickname(nickname);
		article.setTitle(title);
		article.setContent(content);
		int articleIndex = articleDAO.doInsertArticleProcedure(article);
		
		// 태그 저장
		JSONArray jsonArray = (JSONArray) new JSONParser().parse(tags.toString());
		for(Object tag : jsonArray) {
			JSONObject tagValue = (JSONObject) tag;
			tagDAO.insertTag(articleIndex, (String)tagValue.get("value"));
		}
		
		// boardValue 쿠키 저장
		boardHelper.setBoardCookie(req, resp, boardValue);
		session.setAttribute("ArticleWrite", "success");
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/article/write/success");
		return forward;
	}

}
