package com.ggomsu.system.board;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.board.vo.ArticleDTO;

// 작성자 : 박성민
public class BoardHelper {

	public void setBoardCookie(HttpServletRequest req, HttpServletResponse resp, String boardValue) {
		
		Cookie[] cookies = req.getCookies();
		boolean isBoardValueExist = false;
		Cookie cookie = null;
		
		// 쿠키 배열에 boardValue가 있고, 그 값이 현재 boardValue와 다르다면 cookie 추가
		if(cookies != null && cookies.length > 0) {
			for(Cookie c : cookies) {
				if(c.getName().equals("boardValue")) {
					if(!c.getValue().equals(boardValue)) {
						cookie = new Cookie("boardValue", boardValue);
						cookie.setMaxAge(60 * 60 * 24 * 7);
						resp.addCookie(cookie);
						isBoardValueExist = true;
					}
					break;
				}
			}
		}
		
		// 쿠키 배열에 boardValue가 없다면 cookie 추가
		if(!isBoardValueExist) {
			cookie = new Cookie("boardValue", boardValue);
			cookie.setMaxAge(60 * 60 * 24 * 7);
			resp.addCookie(cookie);
		}
		
	}
	
	public boolean setArticleCookie(HttpServletRequest req, HttpServletResponse resp, int articleIndex) {
		
		Cookie[] cookies = req.getCookies();
		boolean isFirst = true;
		String articleValue = "AI" + articleIndex;
		
		// 이미 봤다면 false
		if(cookies != null && cookies.length > 0) {
			for(Cookie c : cookies) {
				if(c.getName().equals(articleValue)) {
					isFirst = false;
					break;
				}
			}
		}
		
		// 아직 안 봤다면 cookie 추가
		if(isFirst) {
			Cookie cookie = new Cookie(articleValue, articleValue);
			cookie.setMaxAge(60 * 60 * 24 * 1);
			resp.addCookie(cookie);
		}
		
		// set에 성공했다는 의미로 isFirst를 반환
		return isFirst;
	}
	
	public void setTagListForList(List<ArticleDTO> articleList) {
		
		// article에 tag가 있다면 배열로 저장
		for(ArticleDTO article : articleList) {
			String tagString = article.getTagString();
			if(tagString != null) {
				article.setTagArray(tagString.split(","));
			}
		}
	}
	
	public String[] setTagListForOne(ArticleDTO article){
		
		String[] tagList = null;
		if(article.getTagString() != null) {
			tagList = article.getTagString().split(",");
			article.setTagArray(tagList);
		}
		
		return tagList;
	}
	
	public String getValueFromCookie(HttpServletRequest req, String name) {
		
		Cookie[] cookies = req.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(Cookie c : cookies) {
				if(c.getName().equals(name)) {
					return c.getValue();
				}
			}
		}
		return null;
	}
}
