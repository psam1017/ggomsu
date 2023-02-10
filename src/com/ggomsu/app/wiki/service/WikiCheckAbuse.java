package com.ggomsu.app.wiki.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.wiki.dao.WikiDAO;

// 작성자 : 박성민
public class WikiCheckAbuse implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		// java 객체 생성
		ActionForward forward = null;
		WikiDAO dao = new WikiDAO();
		
		// parameter 저장
		String ip = req.getRemoteAddr();
		String email = (String) req.getSession().getAttribute("email");
		
		// 차단된 ip인지 확인
		// 비회원은 아닌지 확인
		if(email == null) {
			// ★실제로는 로그인 페이지로 보내야 함. forward로 보내서 로그인하면 다시 이전 페이지로 돌아오도록 하기
			// ★article view detail ok도 마찬가지
			forward = new ActionForward();
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/member/member-sign-in");
		}
		else if(dao.checkBlockedIp(ip)) {
			forward = new ActionForward();
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/wiki/wiki-abuse");
		}
		else { // 만약 유효한 접근이라면 경로는 front controller에서 설정.
			forward = new ActionForward();
			forward.setForward(true);
			forward.setPath(null);
		}
		return forward;
	}
}
