package com.ggomsu.app.wiki.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class Write implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new CheckAbuse().execute(req, resp);
		if(forward.getPath() == null) {
			req.setAttribute("type", "wikiWrite");
			forward.setPath("/views/wiki/Write.jsp");
		}
		
		return forward;
	}

}
