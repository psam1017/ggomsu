package com.ggomsu.system.board;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.board.vo.ArticleDTO;

// 작성자 : 박성민
public class BoardHelper {

	public void setBoardCookie(HttpServletRequest req, HttpServletResponse resp, String boardValue) {
		
		Cookie[] cookies = req.getCookies();
		boolean isBoardValueExist = false;
		
		// 쿠키 배열에 boardValue가 있고, 그 값이 현재 boardValue와 다르다면 cookie 추가
		if(cookies != null && cookies.length > 0) {
			for(Cookie c : cookies) {
				if(c.getName().equals("boardValue") && !c.getValue().equals(boardValue)) {
					Cookie cookie = new Cookie("boardValue", boardValue);
					cookie.setMaxAge(60 * 60 * 24 * 7);
					resp.addCookie(cookie);
					isBoardValueExist = true;
				}
			}
		}
		
		// 쿠키 배열에 boardValue가 없다면 cookie 추가
		if(!isBoardValueExist) {
			resp.addCookie(new Cookie("boardValue", boardValue));
		}
	}
	
	public boolean setArticleCookie(HttpServletRequest req, HttpServletResponse resp, int articleIndex) {
		
		Cookie[] cookies = req.getCookies();
		boolean isFirst = true;
		String articleCookie = "AI" + articleIndex;
		
		// 이미 봤다면 false
		if(cookies != null && cookies.length > 0) {
			for(Cookie c : cookies) {
				if(c.getName().equals(articleCookie)) {
					isFirst = false;
				}
			}
		}
		
		// 아직 안 봤다면 cookie 추가
		if(isFirst) {
			Cookie cookie = new Cookie(articleCookie, articleCookie);
			cookie.setMaxAge(60 * 60 * 24 * 1);
			resp.addCookie(cookie);
		}
		
		// set에 성공했다는 의미로 isFirst를 반환
		return isFirst;
	}
	
	public void setTagList(List<ArticleDTO> articleList) {
		
		// article에 tag가 있다면 배열로 저장
		for(ArticleDTO article : articleList) {
			String tagString = article.getTagString();
			if(tagString != null) {
				article.setTagArray(tagString.split(","));
			}
		}
	}
	
	public void setArticleAttrFromSession(HttpServletRequest req, HttpSession session) {
		req.setAttribute("articleIndex", session.getAttribute("articleIndex"));
		req.setAttribute("boardValue", session.getAttribute("boardValue"));
		req.setAttribute("page", session.getAttribute("page"));
		req.setAttribute("criteria", session.getAttribute("criteria"));
		req.setAttribute("category", session.getAttribute("category"));
		req.setAttribute("period", session.getAttribute("period"));
		req.setAttribute("search", session.getAttribute("search"));
		session.removeAttribute("articleIndex");
		session.removeAttribute("boardValue");
		session.removeAttribute("page");
		session.removeAttribute("criteria");
		session.removeAttribute("category");
		session.removeAttribute("period");
		session.removeAttribute("search");
	}
}
