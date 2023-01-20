package com.ggomsu.app.wiki.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.wiki.dao.WikiDAO;

// 작성자 : 박성민
public class WikiRevise implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		// java 객체 생성
		WikiDAO dao = new WikiDAO();
		ActionForward forward = new ActionForward();
		
		// parameter 저장
		String subject = req.getParameter("subject");
		int rvs = Integer.parseInt(req.getParameter("rvs"));
		
		// wiki info에서 최신 여부를 update시키고, 
		// wiki info와 content를 들고 오기
		// 브라우저를 닫으면 session은 어떻게 삭제되는가? 시간에 따라? 닫을 때 요청?
		// wiki 수정 제어를 어떻게 할 것인가?
		
		forward.setForward(true);
		forward.setPath("/app/wiki/WikiRevise.jsp");
		
		return forward;
	}
}
