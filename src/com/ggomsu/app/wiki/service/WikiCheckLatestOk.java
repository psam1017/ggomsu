package com.ggomsu.app.wiki.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.wiki.dao.WikiDAO;
import com.ggomsu.app.wiki.vo.WikiInfoVO;

// 작성자 : 박성민
public class WikiCheckLatestOk implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		int rvs = Integer.parseInt(req.getParameter("rvs"));
		String subject = req.getParameter("subject");
		
		WikiDAO dao = new WikiDAO();
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		WikiInfoVO vo = dao.getWikiInfo(subject, rvs);
		
		if(vo.getLatestFlag() == 1) {
			json.put("reviseStatus", "ok");
		}
		else {
			json.put("reviseStatus", "not-ok");
		}
		
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}
}
