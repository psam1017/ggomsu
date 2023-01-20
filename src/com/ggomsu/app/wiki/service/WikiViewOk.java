package com.ggomsu.app.wiki.service;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.wiki.dao.WikiDAO;
import com.ggomsu.app.wiki.vo.WikiContentVO;
import com.ggomsu.app.wiki.vo.WikiInfoVO;

// 작성자 : 박성민
public class WikiViewOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		SimpleWiki wiki = new SimpleWiki();
		WikiDAO dao = new WikiDAO();
		WikiInfoVO infoVO = null;
		ActionForward forward = new ActionForward();
		
		String subject = req.getParameter("subject");
		int rvs = Integer.parseInt(req.getParameter("rvs"));
		
		// 작성정보 가져오기
		infoVO = dao.getWikiInfo(subject, rvs);
		
		// 콘텐츠 가져오기
		LinkedList<WikiContentVO> pastList = new LinkedList<>();
		pastList.addAll(dao.getContentPast(subject, rvs));
		List<WikiContentVO> currentList = dao.getContentOne(subject, rvs);
		wiki.setContentFromPast(currentList, pastList);
		
		req.setAttribute("wikiInfo", infoVO);
		req.setAttribute("wikiContents", currentList);
		
		forward.setForward(true);
		forward.setPath("/app/wiki/WikiViewOk.jsp");
		
		return forward;
	}
}
