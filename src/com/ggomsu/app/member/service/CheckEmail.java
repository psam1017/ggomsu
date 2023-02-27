package com.ggomsu.app.member.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

	//작성자 : 박성민
public class CheckEmail implements Action {
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String email = req.getParameter("email");
		MemberDAO dao = new MemberDAO();
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		if(dao.checkEmail(email)) {
			json.put("emailStatus", "not-ok");
		}
		else {
			json.put("emailStatus", "ok");
		}
		
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}
}
