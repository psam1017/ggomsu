package com.ggomsu.app.member.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.member.dao.MemberDAO;

	//작성자 : 박성민
public class MemberCheckContactOk implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String contact = req.getParameter("contact");
		MemberDAO dao = new MemberDAO();
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		if(dao.checkContact(contact)) {
			json.put("contactStatus", "not-ok");
		}
		else {
			json.put("contactStatus", "ok");
		}
		
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}
}