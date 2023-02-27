package com.ggomsu.app.wiki.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.wiki.dao.WikiDAO;
import com.ggomsu.app.wiki.vo.WikiAbuseVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 박성민
public class CheckAbuse implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// java 객체 생성
		ActionForward forward = null;
		WikiDAO dao = new WikiDAO();
		WikiAbuseVO abuse = null;
		boolean isBlockedUser = false;
		
		// parameter 저장
		String ip = req.getRemoteAddr();
		String nickname = (String) req.getSession().getAttribute("nickname");
		
		// 비회원은 아닌지 확인
		if(nickname == null) {
			forward = new ActionForward();
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/wiki/no-member");
		}
		else if(nickname.equals("noname")) { // 익명이라면 차단된 ip인지 확인
			isBlockedUser = dao.checkBlockedIp(ip);
		}
		else { // 회원이라면 차단된 닉네임인지 확인
			isBlockedUser = dao.checkBlockedNickname(nickname);
		}
		
		// 차단된 사용자라면 사용할 수 없다.
		if(isBlockedUser) {
			abuse = dao.getAbuseInfo(nickname, ip);
			req.setAttribute("wikiAbuse", abuse);
			forward = new ActionForward();
			forward.setForward(true);
			forward.setPath("/wiki/abuse");
		}
		else { // 만약 유효한 접근이라면 경로는 client에서 설정.
			forward = new ActionForward();
			forward.setForward(true);
			forward.setPath(null);
		}
		return forward;
	}
}
