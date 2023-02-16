package com.ggomsu.app.wiki.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.wiki.dao.WikiDAO;
import com.ggomsu.app.wiki.vo.WikiInfoVO;

public class WikiHomeOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		ActionForward forawrd = new ActionForward();
		WikiDAO dao = new WikiDAO();
		
		List<WikiInfoVO> vo = dao.getRecentSubject();
		
		req.setAttribute("recentList", vo);
		forawrd.setForward(true);
		forawrd.setPath("/app/wiki/WikiHome.jsp");
		
		return forawrd;
	}

}
