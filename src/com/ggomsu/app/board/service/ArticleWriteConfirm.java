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

		String nickname = (String)session.getAttribute("nickname");
		String statusValue = (String)session.getAttribute("statusValue");
		String title = req.getParameter("title");
		String boardValue = req.getParameter("boardValue");
		String content = req.getParameter("content");
		String tags = req.getParameter("basic");
		
		// 멤버 상태 점검
		if(statusValue == null || !(statusValue.equals("MEM") || statusValue.equals("ADM") || statusValue.equals("SNS"))) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/member/sign-in?code=no-member");
			return forward;
		}
		
		// 공지사항은 관리자가 아니면 입력해서는 안 된다. 한 번 더 점검
		if(boardValue.equals("portfolio") && !statusValue.equals("ADM")) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/error");
			return forward;
		}
		
		// 백틱(`)은 허용하지 않음
		content.replace("`", "");
		
		// 게시글 저장
		article.setBoardValue(boardValue);
		article.setNickname(nickname);
		article.setTitle(title);
		article.setContent(content);
		int articleIndex = articleDAO.doInsertArticleProcedure(article);
		
		// 태그 저장
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
		forward.setPath(req.getContextPath() + "/article/list?boardValue=" + boardValue + "&page=1&code=write");
		return forward;
	}
}
