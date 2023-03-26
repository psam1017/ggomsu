package com.ggomsu.app.board.service;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.board.dao.BoardDAO;
import com.ggomsu.app.board.vo.ArticleDTO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.board.BoardHelper;

// 작성자 : 박성민, 이성호
public class ArticleList implements Action{

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// 주요 이슈 3가지
		// boardValue를 제대로 입력했는가?
		// 검색을 했는가? 검색 조건을 유지해야 하는가?
		// 페이징 처리를 위한 연산
		
		// 자바 객체 생성
		ArticleDAO articleDAO = new ArticleDAO();
		BoardDAO boardDAO = new BoardDAO();
		ActionForward forward = new ActionForward();
		BoardHelper boardHelper = new BoardHelper();
		List<ArticleDTO> articleList;
		HttpSession session = req.getSession();
		
		// session, request 저장
		List<String> blindList = (List<String>)session.getAttribute("blindList");
		String temp = req.getParameter("page");
		String criteria = req.getParameter("criteria");
		String category = req.getParameter("category");
		String period = req.getParameter("period");
		String search = req.getParameter("search");
		String boardValue = null;
		boolean isSameBoard = false;
		
		// boardValue 확인
		if(req.getParameter("boardValue") != null) {
			boardValue = req.getParameter("boardValue");
			String boardCookieValue = boardHelper.getValueFromCookie(req, "boardValue");
			isSameBoard = boardValue.equals(boardCookieValue);
		}
		// boardValue를 명시하지 않으면 어느 게시판인지 알 수 없음
		else {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/error?code=no-board");
			return forward;
		}
		
		// 검색 조건 확인
		// page number가 없거나 boardValue가 이전과 다르다면 검색이 아닌 것으로 판단
		if(temp != null && isSameBoard) {
			// referrer 정보 저장.
			// 브라우저 창으로 입력하면 referer가 없음
			String referer = req.getHeader("referer");

			if(referer != null) {
				// article에서 넘어온 것인지 판단하여 referrer의 servletPath로 가공
				int URIIndex = referer.indexOf("/article") != -1 ? referer.indexOf("/article") : referer.indexOf("/member");
				int paramIndex = referer.indexOf("?");
				
				if(URIIndex != -1 && paramIndex != -1) {
					String command = referer.substring(URIIndex, paramIndex);
					
					if(command.equals("/article/list") || command.equals("/article/view") || command.equals("/member/sign-in")) {
						// page도 있고, 같은 주제의 게시판, 게시글 및 로그인에서 이동했는데, parameter가 비어있다면 검색조건을 유지한 이동
						if(criteria == null && category == null && period == null && search == null) {
							criteria = boardHelper.getValueFromCookie(req, "criteria");
							category = boardHelper.getValueFromCookie(req, "category");
							period = boardHelper.getValueFromCookie(req, "period");
							search = boardHelper.getValueFromCookie(req, "search");
							if(search != null) {
								search = URLDecoder.decode(search, "UTF-8");
							}
						}
						// 검색 조건이 하나라도 있다면 새롭게 검색한 것이므로 쿠키 저장
						else {
							Cookie[] cookies = {new Cookie("criteria", criteria), new Cookie("category", category), 
									new Cookie("period", period), new Cookie("search", URLEncoder.encode(search, "UTF-8"))};
							for(Cookie c : cookies) {
								c.setMaxAge(60 * 60 * 24);
								resp.addCookie(c);
							}
						}
						// 검색 조건을 유지하기 위한 속성
						req.setAttribute("criteria", criteria);
						req.setAttribute("category", category);
						req.setAttribute("period", period);
						req.setAttribute("search", search);
					}
				}
			}
		}
		// page가 없거나 boardValue가 다른 경우 -> 검색이 아니므로 검색 조건을 삭제해야 페이지를 이동할 때 이전 조건에 영향을 받지 않는다.
		else {
			Cookie[] cookies = {new Cookie("criteria", null), new Cookie("category", null), 
					new Cookie("period", null), new Cookie("search", null)};
			for(Cookie c : cookies) {
				c.setMaxAge(0);
				resp.addCookie(c);
			}
		}
		
		// 페이징 처리 시작
		int page = (temp == null) ? 1 : Integer.parseInt(temp);
		int pageSize = boardValue.equals("portfolio") ? 20 : 10; // 포트폴리오는 가급적 페이징되지 않도록
		int totalCount = articleDAO.findTotal(boardValue, blindList, search, category, period);
		int startPage = ((page - 1) / pageSize) * pageSize + 1;
		int endPage = startPage + 9;
		int realEndPage = (int)Math.ceil((double)totalCount/pageSize);
		int prevPage = (int)((page - 10) / 10) * 10 + 1;
		int nextPage = (int)((page + 9) / 10) * 10 + 1;
		
		endPage = (endPage > realEndPage) ? realEndPage : endPage;
		articleList = articleDAO.findList(boardValue, blindList, search, category, period, criteria, (page - 1) * 10);
		
		// tag 배열화
		boardHelper.setTagListForList(articleList);
		
		// 페이징을 위한 속성
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("startPage", startPage);
		req.setAttribute("page", page);
		req.setAttribute("realEndPage", realEndPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("prevPage", prevPage);
		req.setAttribute("nextPage", nextPage);
		req.setAttribute("boardValue", boardValue);
		req.setAttribute("boardText", boardDAO.findBoardText(boardValue));
		req.setAttribute("articleList", articleList);
		
		// 쿠키에 boardValue가 없거나 다르다면 쿠키 추가 -> 추천 게시판에서 활용
		boardHelper.setBoardCookie(req, resp, boardValue);
		
		// 로그인 후 다시 돌아오기 위한 속성
		session.setAttribute("boardValue", boardValue);
		session.setAttribute("page", page);
		
		forward.setForward(true);
		forward.setPath("/views/board/ArticleList.jsp");
		
		return forward;
	}
}
