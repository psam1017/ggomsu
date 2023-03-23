package com.ggomsu.app.wiki.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.ggomsu.app.wiki.dao.WikiDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

	//작성자 : 박성민
public class CheckSubject implements Action {
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String subject = req.getParameter("subject");
		WikiDAO dao = new WikiDAO();
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		if(dao.checkExistBySubject(subject)) {
			json.put("status", "exist");
		}
		else {
			json.put("status", "not-exist");
		}
		
		out.print(json.toJSONString());
		out.close();
		return null;
	}
}
