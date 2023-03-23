package com.ggomsu.app.wiki.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.wiki.dao.WikiDAO;
import com.ggomsu.app.wiki.vo.WikiInfoDTO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class WikiList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		WikiDAO dao = new WikiDAO();
		String subject = req.getParameter("subject");
		
		if(dao.checkExistBySubject(subject)) {
			List<WikiInfoDTO> infoList = dao.getAllRvsBySubject(subject);
			req.setAttribute("infoList", infoList);
			req.setAttribute("subject", subject);
			
			forward.setForward(true);
			forward.setPath("/views/wiki/List.jsp");
		}
		else {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/wiki/not-avail");
		}
		
		return forward;
	}
}
