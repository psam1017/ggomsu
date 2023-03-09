package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.board.BoardHelper;

//작성자 : 박성민

public class SignIn implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		// referrer 정보 저장
		String referer = req.getHeader("referer");
		int articleURIIndex = referer.indexOf("/article");
		int paramIndex = referer.indexOf("?");
		
		// article에서 넘어온 것인지 판단하여 referrer의 servletPath로 가공
		String command = null;
		if(articleURIIndex != -1 && paramIndex != -1) {
			command = referer.substring(articleURIIndex, paramIndex);
			
			// article 조회 중이었다면 session에 검색 파라미터를 저장
			// 로그인을 완료하면 검색 파라미터의 값을 가지고 다시 조회화면으로 이동
			if(command.equals("/article/list") || command.equals("/article/view")) {
				new BoardHelper().setArticleSessionFromAttr(req, req.getSession());
			}
		}
		
		ActionForward forward = new ActionForward();
		forward.setForward(true);
		forward.setPath("/views/member/SignIn.jsp");
		
		return forward;
	}
}
