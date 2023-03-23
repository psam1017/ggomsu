package com.ggomsu.app.wiki.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.wiki.dao.WikiDAO;
import com.ggomsu.app.wiki.vo.WikiContentVO;
import com.ggomsu.app.wiki.vo.WikiInfoDTO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.wiki.WikiHelper;

public class Revise implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new CheckAbuse().execute(req, resp);
		if(forward.getPath() == null) {
			req.setAttribute("type", "wikiRevise");
			forward.setPath("/views/wiki/Write.jsp");
		}
		
		WikiDAO wikiDAO = new WikiDAO();
		WikiHelper wikiHelper = new WikiHelper();
		String subject = req.getParameter("subject");
		String statusValue = (String) req.getSession().getAttribute("statusValue");
		
		if(!wikiDAO.checkExistBySubject(subject)) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/wiki/no-subject");
			return forward;
		}
		
		int rvs = wikiDAO.getLatestRvs(subject);
		WikiInfoDTO info = wikiDAO.findWikiInfo(subject, rvs);
		
		List<WikiContentVO> pastList = new LinkedList<>();
		pastList.addAll(wikiDAO.getContentPast(subject, rvs));
		List<WikiContentVO> currentList = (ArrayList<WikiContentVO>)wikiDAO.getContentOne(subject, rvs);
		wikiHelper.setContentFromPast(currentList, pastList);
		
		String fullContents = "";
		
		for(WikiContentVO line : currentList) {
			fullContents += line.getContent();
		}
		
		if(statusValue == null || statusValue.equals("TMP")) {
			req.setAttribute("remoteAddr", req.getRemoteAddr());
		}
		req.setAttribute("wikiInfo", info);
		req.setAttribute("fullContents", fullContents);
		
		return forward;
	}

}
