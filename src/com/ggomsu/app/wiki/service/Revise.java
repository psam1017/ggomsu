package com.ggomsu.app.wiki.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class Revise implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new CheckAbuse().execute(req, resp);
		if(forward.getPath() == null) {
			req.setAttribute("type", "wikiRevise");
			forward.setPath("/views/wiki/Revise.jsp");
		}
		
		return forward;
	}

}
