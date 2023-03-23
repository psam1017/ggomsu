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
		
		String statusValue = (String) req.getSession().getAttribute("statusValue");
		if(statusValue == null || statusValue.equals("TMP")) {
			req.setAttribute("remoteAddr", req.getRemoteAddr());
		}
		
		return forward;
	}

}
